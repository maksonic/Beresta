package ru.maksonic.beresta.feature.ui.edit_note.core

import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.loadedSuccess
import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox

/**
 * @Author maksonic on 26.04.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class EditNoteSandbox(program: EditNoteProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchFolders),
    subscriptions = listOf(program)
) {
    companion object {
        private const val MAX_NOTE_LENGTH = 35000
    }

    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Inner.UpdatedEntryPointValue -> updatedEntryPointValue(model, msg)
        is Msg.Inner.FetchedPassedNote -> fetchedPassedNote(model)
        is Msg.Inner.FetchedPassedNoteResult -> fetchedPassedNoteResult(model, msg)
        is Msg.Inner.FetchedFoldersSuccess -> fetchedFoldersSuccess(model, msg)
        is Msg.Inner.FetchedCurrentFolderId -> fetchedCurrentFolderId(model, msg)
        is Msg.Inner.ShowedKeyboardForExpandedFab -> showedKeyboardWithFocus(model, msg)
        is Msg.Ui.OnSaveNoteClicked -> onSaveNoteClicked(model, msg)
        is Msg.Ui.OnSelectNoteFolderClicked -> onSelectNoteFolderClicked(model, msg)
        is Msg.Inner.UpdatedCurrentNoteTitle -> updatedNoteTitle(model, msg)
        is Msg.Inner.UpdatedCurrentNoteMessage -> updatedNoteMessage(model, msg)
        //modal sheet
        is Msg.Ui.OnHideModalBottomSheet -> onHideModalBottomSheet(model)
        is Msg.Inner.HiddenModalBottomSheet -> hiddenModalBottomSheet(model)
        //BottomBar actions
        is Msg.Ui.OnAddCameraSnapshotClicked -> ElmUpdate(model)
        is Msg.Ui.OnAddImagesClicked -> ElmUpdate(model)
        is Msg.Ui.OnStartRecordVoiceClicked -> ElmUpdate(model)
        // TopBar actions
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnPinClicked -> onPinClicked(model)
        is Msg.Ui.OnAddNewFolderClicked -> onAddNewChipClicked(model)
        is Msg.Ui.OnCloseAddFolderDialogClicked -> onCloseAddChipDialogClicked(model)
        is Msg.Ui.OnChipFolderClicked -> onChipFolderClicked(model)
        is Msg.Ui.OnSelectColorMarkerClicked -> onSelectColorMarkerClicked(model)
        is Msg.Ui.HiddenMarkerColorPickerDialog -> hiddenMarkerColorPickerDialog(model)
        is Msg.Inner.UpdatedCurrentNoteMarkerColor -> updatedCurrentNoteMarkerColor(model, msg)
        is Msg.Inner.UpdatedCurrentNoteTags -> updatedCurrentNoteTags(model, msg)
        is Msg.Inner.UpdatedTagPickerSheetState -> updatedTagPickerSheetState(model, msg)
        is Msg.Ui.UpdatedWallpaperPickerSheetVisibility -> {
            updatedWallpaperPickerSheetVisibility(model, msg)
        }

        is Msg.Inner.UpdatedNoteWallpaper -> updatedNoteWallpaper(model, msg)
        is Msg.Inner.FetchedNoteWallpaperResult -> fetchedNoteWallpaperResult(model, msg)
    }

    private fun updatedEntryPointValue(
        model: Model,
        msg: Msg.Inner.UpdatedEntryPointValue
    ): Update =
        ElmUpdate(model.copy(isEntryPoint = msg.isEntryPoint))

    private fun fetchedPassedNote(model: Model): Update =
        ElmUpdate(model, commands = setOf(Cmd.FetchNote))

    private fun fetchedPassedNoteResult(
        model: Model,
        msg: Msg.Inner.FetchedPassedNoteResult
    ): Update = ElmUpdate(
        model.copy(
            base = model.base.loadedSuccess,
            isHiddenNote = msg.isHidden,
            isFetchedNote = true,
            editableNote = msg.note,
            isPinNoteSelected = msg.note.style.isPinned,
            markerState = msg.markerState,
            currentWallpaper = msg.wallpaper
        )
    )

    private fun fetchedFoldersSuccess(
        model: Model,
        msg: Msg.Inner.FetchedFoldersSuccess
    ): Update =
        ElmUpdate(model.copy(folders = model.folders.copy(msg.folders), isFetchedFolders = true))

    private fun fetchedCurrentFolderId(
        model: Model,
        msg: Msg.Inner.FetchedCurrentFolderId
    ): Update {
        val entryPointFolder = model.folders.data.find { it.id == model.editableNote.folderId }
        val newNoteFolder = model.folders.data.find { it.id == if (msg.id == 1L) 2L else msg.id }
        val isNotSelected = if (model.isEntryPoint) entryPointFolder else newNoteFolder
        val folder = with(model) { if (isUserSelectedNoteFolder) selectedFolder else isNotSelected }

        return ElmUpdate(model.copy(selectedFolder = folder))
    }

    private fun showedKeyboardWithFocus(
        model: Model,
        msg: Msg.Inner.ShowedKeyboardForExpandedFab
    ): Update {
        val isCorrect = !model.isEntryPoint && !msg.isVisibleWallpaperPickerSheet

        return ElmUpdate(
            model = model,
            effects = if (isCorrect) setOf(Eff.ShowKeyboardForExpandedFab) else emptySet()
        )
    }

    private fun onSaveNoteClicked(model: Model, msg: Msg.Ui.OnSaveNoteClicked): Update {
        val folderId = if (model.selectedFolder?.id == null) 2L else model.selectedFolder.id

        val note = model.editableNote.copy(
            folderId = folderId,
            isHidden = msg.isHiddenNote,
            style = model.editableNote.style.copy(
                isPinned = model.isPinNoteSelected,
                markerColorId = model.markerState.currentSelectedColorId
            )
        )

        val markerColorId = if (model.isEntryPoint) model.markerState.currentSelectedColorId else 0L
        val wallpaper = if (!model.isEntryPoint) BaseWallpaper.empty() else model.currentWallpaper

        val effect = if (!model.isEntryPoint)
            setOf(Eff.CollapseFab)
        else setOf(Eff.ShowNoteUpdateSnackBar)


        return ElmUpdate(
            model = model.copy(
                editableNote = if (model.isEntryPoint) note else NoteUi.Default,
                isUserSelectedNoteFolder = false,
                currentWallpaper = wallpaper,
                markerState = model.markerState.copy(currentSelectedColorId = markerColorId)
            ),
            commands = setOf(Cmd.SaveNote(note, model.currentWallpaper)),
            effects = effect
        )
    }

    private fun onSelectNoteFolderClicked(
        model: Model,
        msg: Msg.Ui.OnSelectNoteFolderClicked
    ): Update = ElmUpdate(model.copy(isUserSelectedNoteFolder = true, selectedFolder = msg.folder))

    private fun updatedNoteTitle(
        model: Model,
        msg: Msg.Inner.UpdatedCurrentNoteTitle
    ): Update {
        val updatedField = msg.text.take(MAX_NOTE_LENGTH)
        val maybeWarningEffect = if (msg.text.length > MAX_NOTE_LENGTH)
            setOf(Eff.ShowToastMaxLengthNoteExceed)
        else
            emptySet()

        return ElmUpdate(
            model = model.copy(editableNote = model.editableNote.copy(title = updatedField)),
            effects = maybeWarningEffect
        )
    }

    private fun updatedNoteMessage(
        model: Model,
        msg: Msg.Inner.UpdatedCurrentNoteMessage
    ): Update {
        val updatedField = msg.text.take(MAX_NOTE_LENGTH)
        val maybeWarningEffect = if (msg.text.length > MAX_NOTE_LENGTH)
            setOf(Eff.ShowToastMaxLengthNoteExceed)
        else emptySet()

        return ElmUpdate(
            model = model.copy(editableNote = model.editableNote.copy(message = updatedField)),
            effects = maybeWarningEffect
        )
    }

    private fun onHideModalBottomSheet(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.HideModalSheet))

    private fun hiddenModalBottomSheet(model: Model): Update = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = false, content = CurrentSheetContent.NOTHING
            )
        )
    )

    private fun onTopBarBackPressed(model: Model): Update {
        val effect = setOf(if (model.isEntryPoint) Eff.NavigateBack else Eff.CollapseFab)
        return ElmUpdate(model = model, effects = effect)
    }

    private fun onPinClicked(model: Model): Update =
        ElmUpdate(model = model.copy(isPinNoteSelected = !model.isPinNoteSelected))

    private fun onAddNewChipClicked(model: Model): Update = ElmUpdate(
        model = model.copy(isVisibleAddFolderDialog = true),
        effects = setOf(Eff.ShowKeyboardForExpandedFab, Eff.ShowAddNewChipDialog)
    )

    private fun onCloseAddChipDialogClicked(model: Model): Update =
        ElmUpdate(model = model.copy(isVisibleAddFolderDialog = false))

    private fun onChipFolderClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.HideKeyboard))

    private fun onSelectColorMarkerClicked(model: Model): Update = ElmUpdate(
        model = model.copy(markerState = model.markerState.copy(isVisibleDialog = true)),
        effects = setOf(Eff.HideKeyboard)
    )

    private fun hiddenMarkerColorPickerDialog(model: Model): Update =
        ElmUpdate(model = model.copy(markerState = model.markerState.copy(isVisibleDialog = false)))

    private fun updatedCurrentNoteMarkerColor(
        model: Model,
        msg: Msg.Inner.UpdatedCurrentNoteMarkerColor
    ): Update = ElmUpdate(
        model = model.copy(
            markerState = model.markerState.copy(currentSelectedColorId = msg.colorId)
        )
    )

    private fun updatedCurrentNoteTags(
        model: Model,
        msg: Msg.Inner.UpdatedCurrentNoteTags
    ): Update = ElmUpdate(
        model.copy(
            editableNote = model.editableNote.copy(tags = model.editableNote.tags.copy(msg.tags))
        )
    )

    private fun updatedTagPickerSheetState(
        model: Model,
        msg: Msg.Inner.UpdatedTagPickerSheetState
    ): Update = ElmUpdate(
        model = model.copy(isVisibleTagPickerSheet = msg.isVisible),
        effects = setOf(Eff.HideKeyboard)
    )

    private fun updatedWallpaperPickerSheetVisibility(
        model: Model,
        msg: Msg.Ui.UpdatedWallpaperPickerSheetVisibility
    ): Update = ElmUpdate(
        model.copy(isVisibleWallpaperPickerSheet = msg.isVisible), effects = setOf(Eff.HideKeyboard)
    )

    private fun updatedNoteWallpaper(
        model: Model,
        msg: Msg.Inner.UpdatedNoteWallpaper
    ): Update = ElmUpdate(model, commands = setOf(Cmd.FetchWallpaper(msg.wallpaper)))

    private fun fetchedNoteWallpaperResult(
        model: Model,
        msg: Msg.Inner.FetchedNoteWallpaperResult
    ): Update = ElmUpdate(model.copy(currentWallpaper = msg.value))
}
package ru.maksonic.beresta.feature.edit_note.ui.core

import ru.maksonic.beresta.elm.core.ElmBaseModel.Companion.loadedSuccess
import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.isDefaultId

/**
 * @Author maksonic on 26.04.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class EditNoteSandbox(program: EditNoteProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchNote, Cmd.FetchFolders),
    subscriptions = listOf(program)
) {
    companion object {
        private const val MAX_NOTE_LENGTH = 35000
    }

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.UpdatedEntryPointValue -> updatedEntryPointValue(model, msg)
        is Msg.Inner.FetchedPassedNote -> fetchedPassedNote(model)
        is Msg.Inner.FetchedPassedNoteResult -> fetchedPassedNoteResult(model, msg)
        is Msg.Inner.FetchedFoldersResult -> fetchedFoldersResult(model, msg)
        is Msg.Inner.FetchedCurrentFolderId -> fetchedCurrentFolderId(model, msg)
        is Msg.Inner.ShowedKeyboardForExpandedFab -> showedKeyboardWithFocus(model)
        is Msg.Ui.OnSaveNoteClicked -> onSaveNoteClicked(model, msg)
        is Msg.Ui.OnSelectNoteFolderClicked -> onSelectNoteFolderClicked(model, msg)
        is Msg.Inner.UpdatedCurrentNoteTitle -> updatedNoteTitle(model, msg)
        is Msg.Inner.UpdatedCurrentNoteMessage -> updatedNoteMessage(model, msg)
        //BottomBar actions
        is Msg.Ui.OnAddCameraSnapshotClicked -> ElmUpdate(model)
        is Msg.Ui.OnAddImagesClicked -> ElmUpdate(model)
        is Msg.Ui.OnSetNoteWallpaperClicked -> ElmUpdate(model)
        is Msg.Ui.OnStartRecordVoiceClicked -> ElmUpdate(model)
        // TopBar actions
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnPinClicked -> onPinClicked(model)
        is Msg.Ui.OnAddNewChipClicked -> onAddNewChipClicked(model)
        is Msg.Ui.OnChipFolderClicked -> onChipFolderClicked(model)
        is Msg.Ui.OnChangeColorMarkerClicked -> onChangeNoteColorMarkerClicked(model)
        is Msg.Inner.UpdatedCurrentNoteMarkerColor -> updatedCurrentNoteMarkerColor(model, msg)
    }

    private fun updatedEntryPointValue(
        model: Model,
        msg: Msg.Inner.UpdatedEntryPointValue
    ): UpdateResult =
        ElmUpdate(model.copy(isEntryPoint = msg.isEntryPoint))

    private fun fetchedPassedNote(model: Model): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.FetchNote))

    private fun fetchedPassedNoteResult(
        model: Model,
        msg: Msg.Inner.FetchedPassedNoteResult
    ): UpdateResult = ElmUpdate(
        model.copy(
            base = model.base.loadedSuccess,
            isHiddenNote = msg.isHidden,
            isFetchedNote = true,
            editableNote = msg.note
        )
    )

    private fun fetchedFoldersResult(
        model: Model,
        msg: Msg.Inner.FetchedFoldersResult
    ): UpdateResult = ElmUpdate(model.copy(folders = model.folders.copy(msg.folders)))

    private fun fetchedCurrentFolderId(
        model: Model,
        msg: Msg.Inner.FetchedCurrentFolderId
    ): UpdateResult {
        val entryPointFolder = model.folders.data.find { it.id == model.editableNote.folderId }
        val newNoteFolder = model.folders.data.find { it.id == if (msg.id == 1L) 2L else msg.id }
        val isNotSelected = if (model.isEntryPoint) entryPointFolder else newNoteFolder
        val folder = with(model) { if (isUserSelectedNoteFolder) selectedFolder else isNotSelected }

        return ElmUpdate(model.copy(selectedFolder = folder))
    }

    private fun showedKeyboardWithFocus(model: Model): UpdateResult = ElmUpdate(
        model = model,
        effects = if (!model.isEntryPoint) setOf(Eff.ShowKeyboardForExpandedFab) else emptySet()
    )

    private fun onSaveNoteClicked(model: Model, msg: Msg.Ui.OnSaveNoteClicked): UpdateResult {
        val folderId = if (model.selectedFolder?.id == null) 2L else model.selectedFolder.id
        val note = model.editableNote.copy(folderId = folderId, isHidden = msg.isHiddenNote)
        val effect = if (note.isDefaultId())
            setOf(Eff.CollapseFab, Eff.UpdateCurrentFolder(note.folderId))
        else setOf(Eff.ShowNoteUpdateSnackBar)

        return ElmUpdate(
            model = model.copy(
                editableNote = if (model.isEntryPoint) note else NoteUi.Default,
                isUserSelectedNoteFolder = false
            ),
            commands = setOf(Cmd.SaveNote(note)),
            effects = effect
        )
    }

    private fun onSelectNoteFolderClicked(
        model: Model,
        msg: Msg.Ui.OnSelectNoteFolderClicked
    ): UpdateResult {
        return ElmUpdate(model.copy(isUserSelectedNoteFolder = true, selectedFolder = msg.folder))
    }

    private fun updatedNoteTitle(
        model: Model,
        msg: Msg.Inner.UpdatedCurrentNoteTitle
    ): UpdateResult {
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
    ): UpdateResult {
        val updatedField = msg.text.take(MAX_NOTE_LENGTH)
        val maybeWarningEffect = if (msg.text.length > MAX_NOTE_LENGTH)
            setOf(Eff.ShowToastMaxLengthNoteExceed)
        else emptySet()

        return ElmUpdate(
            model = model.copy(editableNote = model.editableNote.copy(message = updatedField)),
            effects = maybeWarningEffect
        )
    }

    private fun onTopBarBackPressed(model: Model): UpdateResult {
        val effect = setOf(if (model.isEntryPoint) Eff.NavigateBack else Eff.CollapseFab)
        return ElmUpdate(model = model, effects = effect)
    }

    private fun onPinClicked(model: Model): UpdateResult {
        val updatedNote = model.editableNote.copy(isPinned = !model.editableNote.isPinned)

        return ElmUpdate(
            model = model.copy(editableNote = updatedNote),
            commands = setOf(Cmd.UpdatePinnedNoteInCache(updatedNote))
        )
    }

    private fun onAddNewChipClicked(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.ShowAddNewChipDialog))

    private fun onChipFolderClicked(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.HideKeyboard))

    private fun onChangeNoteColorMarkerClicked(model: Model): UpdateResult = ElmUpdate(
        model = model,
        effects = setOf(
            Eff.HideKeyboard,
            Eff.ShowMarkerColorPickerDialog(model.editableNote.markerColorId)
        )
    )

    private fun updatedCurrentNoteMarkerColor(
        model: Model,
        msg: Msg.Inner.UpdatedCurrentNoteMarkerColor
    ): UpdateResult = ElmUpdate(
        model = model.copy(editableNote = model.editableNote.copy(markerColorId = msg.colorId))
    )
}
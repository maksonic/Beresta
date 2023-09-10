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
        is Msg.Inner.CheckedEntryPoint -> checkedEntryPoint(model, msg)
        is Msg.Inner.ShowedKeyboardForExpandedFab -> showedKeyboardWithFocus(model)
        is Msg.Inner.FetchedPassedNoteResult -> fetchedPassedNoteResult(model, msg)
        is Msg.Inner.FetchedFoldersResult -> fetchedFoldersResult(model, msg)
        is Msg.Ui.OnSaveNoteClicked -> onSaveNoteClicked(model, msg)
        is Msg.Ui.OnSelectNoteFolderClicked -> onSelectNoteFolderClicked(model, msg)
        is Msg.Inner.UpdatedCurrentNoteTitle -> updatedNoteTitle(model, msg)
        is Msg.Inner.UpdatedCurrentNoteMessage -> updatedNoteMessage(model, msg)
        is Msg.Inner.FetchedCurrentFolderId -> fetchedCurrentFolderId(model, msg)
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
    }

    private fun checkedEntryPoint(model: Model, msg: Msg.Inner.CheckedEntryPoint): UpdateResult =
        ElmUpdate(model.copy(isEntryPoint = msg.value))

    private fun showedKeyboardWithFocus(model: Model): UpdateResult = ElmUpdate(
        model = model,
        effects = if (!model.isEntryPoint) setOf(Eff.ShowKeyboardForExpandedFab) else emptySet()
    )

    private fun fetchedPassedNoteResult(
        model: Model,
        msg: Msg.Inner.FetchedPassedNoteResult
    ): UpdateResult = ElmUpdate(
        model.copy(
            base = model.base.loadedSuccess,
            isHiddenNote = msg.isHidden,
            currentNote = msg.note,
            selectedFolder = msg.folder
        )
    )

    private fun fetchedFoldersResult(
        model: Model,
        msg: Msg.Inner.FetchedFoldersResult
    ): UpdateResult = ElmUpdate(model.copy(folders = model.folders.copy(msg.folders)))

    private fun onSaveNoteClicked(model: Model, msg: Msg.Ui.OnSaveNoteClicked): UpdateResult {
        val currentFolderId = if (model.currentFolderId == 1L) 2L else model.currentFolderId
        val folderId = if (model.selectedFolder == null) {
            currentFolderId
        } else {
            model.selectedFolder.id
        }

        val note = model.currentNote.copy(folderId = folderId, isHidden = msg.isHiddenNote)
        val effect = if (note.isDefaultId()) setOf(
            Eff.CollapseFab,
            Eff.UpdateCurrentFolder(folderId)
        ) else setOf(Eff.ShowNoteUpdateSnackBar)

        return ElmUpdate(
            model = model.copy(
                currentNote = if (model.isEntryPoint) note else NoteUi.Default,
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
        val selectedFolder = model.folders.data.find { it.id == msg.folderId }

        return ElmUpdate(
            model.copy(
                selectedFolder = selectedFolder,
                isUserSelectedNoteFolder = true
            )
        )
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
            model = model.copy(currentNote = model.currentNote.copy(title = updatedField)),
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
            model = model.copy(currentNote = model.currentNote.copy(message = updatedField)),
            effects = maybeWarningEffect
        )
    }

    private fun fetchedCurrentFolderId(
        model: Model,
        msg: Msg.Inner.FetchedCurrentFolderId
    ): UpdateResult {
        val selectedFolder = if (model.isUserSelectedNoteFolder) {
            model.selectedFolder
        } else {
            model.folders.data.find { it.id == if (msg.id == 1L) 2L else msg.id }
        }

        return ElmUpdate(
            model = model.copy(
                currentFolderId = msg.id,
                selectedFolder = selectedFolder
            )
        )
    }

    private fun onTopBarBackPressed(model: Model): UpdateResult {
        val effect = setOf(if (model.isEntryPoint) Eff.NavigateBack else Eff.CollapseFab)
        return ElmUpdate(model = model, effects = effect)
    }

    private fun onPinClicked(model: Model): UpdateResult {
        val updatedNote = model.currentNote.copy(isPinned = !model.currentNote.isPinned)

        return ElmUpdate(
            model = model.copy(currentNote = updatedNote),
            commands = setOf(Cmd.UpdatePinnedNoteInCache(updatedNote))
        )
    }

    private fun onAddNewChipClicked(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.ShowAddNewChipDialog))

    private fun onChipFolderClicked(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.HideKeyboard))

    private fun onChangeNoteColorMarkerClicked(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.HideKeyboard, Eff.ShowMarkerColorPickerDialog))

}
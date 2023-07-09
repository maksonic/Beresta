package ru.maksonic.beresta.feature.edit_note.ui

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
    initialCmd = setOf(Cmd.FetchNote),
    subscriptions = listOf(program)
) {
    companion object {
        private const val MAX_NOTE_LENGTH = 15000
    }

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.CheckedEntryPoint -> checkedEntryPoint(model, msg)
        is Msg.Inner.ShowedKeyboardForExpandedFab -> showedKeyboardWithFocus(model)
        is Msg.Inner.FetchedPassedNoteResult -> fetchedPassedNoteResult(model, msg)
        is Msg.Ui.OnSaveNoteClicked -> onSaveNoteClicked(model)
        is Msg.Inner.UpdatedCurrentNoteTitle -> updatedNoteTitle(model, msg)
        is Msg.Inner.UpdatedCurrentNoteMessage -> updatedNoteMessage(model, msg)
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnExpandFabClicked -> onExpandFabClicked(model)
        //BottomBar actions
        is Msg.Ui.OnAddCameraSnapshotClicked -> ElmUpdate(model)
        is Msg.Ui.OnAddImagesClicked -> ElmUpdate(model)
        is Msg.Ui.OnSetNoteWallpaperClicked -> ElmUpdate(model)
        is Msg.Ui.OnStartRecordVoiceClicked -> ElmUpdate(model)
        is Msg.Inner.UpdatedCurrentSelectedFolderId -> fetchedPassedCurrentFolderId(model, msg)
    }

    private fun checkedEntryPoint(model: Model, msg: Msg.Inner.CheckedEntryPoint): UpdateResult =
        ElmUpdate(model.copy(isEntryPoint = msg.value))

    private fun showedKeyboardWithFocus(model: Model): UpdateResult =
        ElmUpdate(
            model = model,
            effects = if (model.isExpandedFab) setOf(Eff.ShowKeyboardForExpandedFab) else emptySet()
        )

    private fun fetchedPassedNoteResult(
        model: Model,
        msg: Msg.Inner.FetchedPassedNoteResult
    ): UpdateResult =
        ElmUpdate(
            model.copy(
                base = model.base.loadedSuccess,
                currentNote = msg.note,
            )
        )

    private fun onSaveNoteClicked(model: Model): UpdateResult {
        //Set default id when passed id equals sticky end folder id.
        val folderId =
            if (model.currentSelectedFolderId == 1L) 2L else model.currentSelectedFolderId

        val note = model.currentNote.copy(folderId = folderId)
        val showSnackIfIsNotNewNote = if (model.currentNote.isDefaultId()) emptySet()
        else setOf(Eff.ShowNoteUpdateSnackBar)

        return ElmUpdate(
            model.copy(
                isExpandedFab = model.isEntryPoint,
                currentNote = if (model.isEntryPoint) note else NoteUi.Default
            ),
            commands = setOf(Cmd.SaveNote(note)),
            effects = showSnackIfIsNotNewNote
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
        else
            emptySet()

        return ElmUpdate(
            model = model.copy(currentNote = model.currentNote.copy(message = updatedField)),
            effects = maybeWarningEffect
        )
    }

    private fun onTopBarBackPressed(model: Model): UpdateResult {
        val maybeNavBackEff = if (model.isEntryPoint) setOf(Eff.NavigateBack) else emptySet()
        return ElmUpdate(model = model.copy(isExpandedFab = false), effects = maybeNavBackEff)
    }

    private fun onExpandFabClicked(model: Model): UpdateResult =
        ElmUpdate(model = model.copy(isEntryPoint = false, isExpandedFab = true))

    private fun fetchedPassedCurrentFolderId(
        model: Model,
        msg: Msg.Inner.UpdatedCurrentSelectedFolderId
    ): UpdateResult =
        ElmUpdate(model = model.copy(currentSelectedFolderId = msg.id))
}
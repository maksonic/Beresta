package ru.maksonic.beresta.feature.edit_note.core.screen.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.ui.isEmpty
import java.time.LocalDateTime

/**
 * @Author maksonic on 04.03.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class EditNoteSandbox(program: EditNoteProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model(),
    initialCmd = setOf(Cmd.FetchNote),
    subscriptions = listOf(program)
) {
    companion object {
        private const val MAX_NOTE_LENGTH = 15000
    }

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {

        is Msg.Ui.OnSaveNoteClicked -> onSaveNoteClicked(model)
        is Msg.Ui.OnTopBarBackPressed -> topBarBackPressed(model)
        is Msg.Ui.OnChangeNoteWallpaperClicked -> onChangeNoteWallpaperPanelClicked(model)
        is Msg.Ui.OnHideModalSheetClicked -> collapsedBottomSheet(model)
        is Msg.Inner.FetchedPassedNoteResult -> afterFetchPassedNote(model, msg)
        is Msg.Inner.FetchedPassedFabStateResult -> afterFetchPassedFabState(model, msg)
        is Msg.Inner.UpdatedInputTitle -> updatedNoteTitle(model, msg)
        is Msg.Inner.UpdatedInputMessage -> updatedNoteMessage(model, msg)
        is Msg.Inner.UpdatedNoteWallpaper -> updateNoteWallpaper(model, msg)
        is Msg.Inner.ShowKeyboardWithFocusOnTitle -> onEditFabExpanded(model)
        is Msg.Inner.ShowedMaxLengthNoteInputWarning -> showedToastMaxLengthNoteExceed(model)
        is Msg.Inner.ResetBottomSheetContent -> resetBottomSheetContent(model)
    }

    private fun afterFetchPassedNote(
        model: Model,
        msg: Msg.Inner.FetchedPassedNoteResult
    ): UpdateResult =
        UpdatedModel(model.copy(currentNote = msg.note, isNewNote = msg.note.id == 0L))

    private fun onSaveNoteClicked(model: Model): UpdateResult {
        val resetModel = NoteUi.Default
        val newNote = model.currentNote.copy(dateCreationRaw = LocalDateTime.now())
        val cmd = if (model.currentNote.id == 0L)
            Cmd.SaveNote(newNote) else Cmd.UpdateCurrentNote(model.currentNote)
        val eff = if (model.isNewNote) Eff.CollapseFab else Eff.ResetFabDraftIcon

        return UpdatedModel(
            model.copy(currentNote = resetModel),
            commands = setOf(cmd),
            effects = setOf(eff, Eff.ResetFabDraftIcon, Eff.ShowNoteUpdateSnackBar)
        )
    }

    private fun topBarBackPressed(model: Model): UpdateResult {
        val backPressedEffect = if (model.isNewNote) Eff.CollapseFab else Eff.NavigateBack
        val isNewNotEmptyNote = model.isNewNote && !model.currentNote.isEmpty()
        val isShowDraftFabIcon =
            if (isNewNotEmptyNote) Eff.ShowFabDraftIcon else Eff.ResetFabDraftIcon

        return UpdatedModel(
            model = model,
            effects = setOf(backPressedEffect, isShowDraftFabIcon, Eff.SetNothingSheetContent)
        )
    }

    private fun onChangeNoteWallpaperPanelClicked(model: Model): UpdateResult =
        UpdatedModel(
            model = model,
            effects = setOf(
                Eff.HideSystemKeyboard,
                Eff.ExpandBottomSheet,
                Eff.SetWallpaperSheetContent
            )
        )

    private fun collapsedBottomSheet(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.CollapseBottomSheet))

    private fun onEditFabExpanded(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.ShowKeyboardWithTitleFocus))

    private fun afterFetchPassedFabState(
        model: Model,
        msg: Msg.Inner.FetchedPassedFabStateResult
    ): UpdateResult =
        UpdatedModel(model.copy(isNewNote = msg.isExpanded))

    private fun updatedNoteTitle(model: Model, msg: Msg.Inner.UpdatedInputTitle): UpdateResult {
        val croppedInput = msg.text.take(MAX_NOTE_LENGTH)
        val isShowWarning = if (msg.text.length > MAX_NOTE_LENGTH)
            setOf(Eff.ShowToastMaxLengthNoteExceed)
        else
            emptySet()

        return UpdatedModel(
            model = model.copy(currentNote = model.currentNote.copy(title = croppedInput)),
            effects = isShowWarning
        )
    }

    private fun updatedNoteMessage(model: Model, msg: Msg.Inner.UpdatedInputMessage): UpdateResult {
        val croppedInput = msg.text.take(MAX_NOTE_LENGTH)
        val isShowWarning = if (msg.text.length > MAX_NOTE_LENGTH)
            setOf(Eff.ShowToastMaxLengthNoteExceed)
        else
            emptySet()

        return UpdatedModel(
            model = model.copy(currentNote = model.currentNote.copy(message = croppedInput)),
            effects = isShowWarning
        )
    }

    private fun updateNoteWallpaper(
        model: Model,
        msg: Msg.Inner.UpdatedNoteWallpaper
    ): UpdateResult =
        UpdatedModel(model.copy(currentNote = model.currentNote.copy(backgroundId = msg.id)))

    private fun showedToastMaxLengthNoteExceed(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.ShowToastMaxLengthNoteExceed))

    private fun resetBottomSheetContent(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.SetNothingSheetContent))
}


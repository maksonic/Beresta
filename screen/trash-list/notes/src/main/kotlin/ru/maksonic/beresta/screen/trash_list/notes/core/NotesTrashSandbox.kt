package ru.maksonic.beresta.screen.trash_list.notes.core

import androidx.compose.material3.ExperimentalMaterial3Api
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 30.05.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

@OptIn(ExperimentalMaterial3Api::class)
class NotesTrashSandbox(program: NotesTrashProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchRemovedData),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedRemovedNotesResult -> fetchedDataResult(model, msg)
        is Msg.Inner.FetchedError -> UpdatedModel(model)
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnTrashedFoldersBtnClicked -> onTrashedFoldersBtnClicked(model)
        is Msg.Ui.OnNoteClicked -> onNoteClicked(model, msg)
        is Msg.Ui.OnNoteLongClicked -> onNoteLongClicked(model, msg)
        is Msg.Ui.OnSelectAllNotesClicked -> onSelectAllNotesClicked(model)
        is Msg.Ui.CancelSelectionState -> onCancelSelectionClicked(model)
        is Msg.Ui.HideModalBottomSheet -> hideModalBottomSheet(model)
        is Msg.Ui.OnRestoreFromTrashNoteClicked -> onRestoreNoteClicked(model)
        is Msg.Ui.OnDeleteNoteClicked -> onDeleteNoteClicked(model)
        is Msg.Ui.OnCancelDeleteDialogClicked -> onCancelDeleteDialogClicked(model)
        is Msg.Inner.UpdatedModalSheetState -> updatedModalSheetState(model, msg)
    }

    private fun fetchedDataResult(
        model: Model,
        msg: Msg.Inner.FetchedRemovedNotesResult
    ): UpdateResult = UpdatedModel(
        model.copy(
            base = model.base.copy(
                isLoading = false,
                isSuccessLoading = true
            ),
            notes = model.notes.copy(msg.notes)
        )
    )

    private fun onTopBarBackPressed(model: Model): UpdateResult {
        val onClickEffect =
            if (model.modalBottomSheetState.isVisible) Eff.HideModalSheet else Eff.NavigateBack
        return UpdatedModel(model, effects = setOf(onClickEffect))
    }

    private fun onTrashedFoldersBtnClicked(model: Model): UpdateResult {
        val eff = if (model.isVisibleModalSheet) emptySet() else setOf(Eff.NavigateToTrashedFoldersList)
        return UpdatedModel(
            model = model.copy(
                isSelectionState = false,
                isVisibleModalSheet = false,
                selectedNotes = emptySet()
            ),
            effects = eff
        )
    }

    private fun onNoteClicked(model: Model, msg: Msg.Ui.OnNoteClicked): UpdateResult =
        if (model.isSelectionState)
            baseOnNoteAction(model, msg.id)
        else
            UpdatedModel(
                model = model.copy(isVisibleModalSheet = !model.isVisibleModalSheet, currentClickedNoteId = msg.id),
              //  effects = setOf(Eff.ShowModalSheet)
            )


    private fun onNoteLongClicked(model: Model, msg: Msg.Ui.OnNoteLongClicked): UpdateResult =
        baseOnNoteAction(model, msg.id)

    private fun baseOnNoteAction(model: Model, noteId: Long): UpdateResult {
        val selected = model.selectedNotes.toMutableSet().also { list ->
            model.notes.data.forEach { note ->
                if (noteId == note.id) {
                    if (list.contains(note)) list.remove(note) else list.add(note)
                }
            }
        }.toSet()

        return UpdatedModel(
            model.copy(
                selectedNotes = selected,
                isSelectionState = true,
            )
        )
    }

    private fun onSelectAllNotesClicked(model: Model): UpdateResult {
        val selected = model.selectedNotes.toMutableSet().also { list ->
            if (list.containsAll(model.notes.data)) list.clear()
            else list.addAll(model.notes.data)
        }.toSet()

        return UpdatedModel(model.copy(selectedNotes = selected))
    }

    private fun onCancelSelectionClicked(model: Model): UpdateResult =
        UpdatedModel(model.copy(selectedNotes = emptySet(), isSelectionState = false))

    private fun hideModalBottomSheet(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.HideModalSheet))

    private fun onRestoreNoteClicked(model: Model): UpdateResult =
        UpdatedModel(
            model.copy(isVisibleAcceptDeleteNotesDialog = true),
            effects = setOf(Eff.HideModalSheet)
        )

    private fun onDeleteNoteClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(isVisibleAcceptDeleteNotesDialog = true),
        effects = setOf(Eff.HideModalSheet)
    )

    private fun onCancelDeleteDialogClicked(model: Model): UpdateResult =
        UpdatedModel(model.copy(isVisibleAcceptDeleteNotesDialog = false))

    private fun updatedModalSheetState(
        model: Model,
        msg: Msg.Inner.UpdatedModalSheetState
    ): UpdateResult =
        UpdatedModel(model.copy(isVisibleModalSheet = msg.isVisible))
}
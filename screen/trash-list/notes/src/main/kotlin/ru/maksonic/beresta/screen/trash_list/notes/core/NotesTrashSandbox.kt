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
        is Msg.Inner.UpdatedModalSheetState -> updatedModalSheetState(model, msg)
        is Msg.Ui.HideModalBottomSheet -> hideModalBottomSheet(model)
        is Msg.Ui.OnModalSheetRestoreClicked -> onModalSheetRestoreNoteClicked(model)
        is Msg.Ui.OnModalSheetDeleteClicked -> showedAcceptDeleteWarningDialog(model)
        is Msg.Ui.OnBottomBarDeleteSelectedNotesClicked -> showedAcceptDeleteWarningDialog(model)
        is Msg.Ui.OnBottomBarRestoreSelectedNotesClicked -> onBottomBarRestoreSelectedClicked(model)
        is Msg.Ui.OnCancelDeleteWarningDialogClicked -> onCancelDeleteWarningDialogClicked(model)
        is Msg.Ui.OnAcceptDeleteWarningDialogClicked -> onAcceptDeleteWarningDialogClicked(model)

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
        val eff =
            if (model.isVisibleModalSheet) emptySet() else setOf(Eff.NavigateToTrashedFoldersList)
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
                model = model.copy(
                    isVisibleModalSheet = !model.isVisibleModalSheet,
                    currentClickedNoteId = msg.id
                ),
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

    private fun updatedModalSheetState(
        model: Model,
        msg: Msg.Inner.UpdatedModalSheetState
    ): UpdateResult =
        UpdatedModel(model.copy(isVisibleModalSheet = msg.isVisible))

    private fun hideModalBottomSheet(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.HideModalSheet))

    private fun onModalSheetRestoreNoteClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(isVisibleAcceptDeleteNotesDialog = false),
        commands = setOf(
            Cmd.DeleteOrRestoreNotes(
                isRestore = true,
                notes = model.notes.data.filter { it.id == model.currentClickedNoteId }
            )),
        effects = setOf(Eff.HideModalSheet)
    )

    private fun showedAcceptDeleteWarningDialog(model: Model): UpdateResult =
        UpdatedModel(
            model = model.copy(
                isVisibleAcceptDeleteNotesDialog = true,
                isSingleItemAction = model.selectedNotes.count() <= 1
            ),
            effects = setOf(Eff.HideModalSheet)
        )

    private fun onBottomBarRestoreSelectedClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(selectedNotes = emptySet(), isSelectionState = false),
        commands = setOf(Cmd.DeleteOrRestoreNotes(isRestore = true, model.selectedNotes.toList()))
    )

    private fun onCancelDeleteWarningDialogClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(
            isVisibleAcceptDeleteNotesDialog = false,
            currentClickedNoteId = null,
        )
    )

    private fun onAcceptDeleteWarningDialogClicked(model: Model): UpdateResult {
        val currentClickedNote = model.notes.data.filter { it.id == model.currentClickedNoteId }
        val delete = if (model.selectedNotes.isEmpty() && model.currentClickedNoteId != null)
            currentClickedNote else model.selectedNotes.toList()

        return UpdatedModel(
            model = model.copy(
                isVisibleAcceptDeleteNotesDialog = false,
                currentClickedNoteId = null,
                isSelectionState = false,
                selectedNotes = emptySet()
            ),
            commands = setOf(Cmd.DeleteOrRestoreNotes(isRestore = false, delete))
        )
    }
}
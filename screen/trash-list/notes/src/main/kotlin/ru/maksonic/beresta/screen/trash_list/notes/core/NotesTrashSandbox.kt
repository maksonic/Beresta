package ru.maksonic.beresta.screen.trash_list.notes.core

import androidx.compose.material3.ExperimentalMaterial3Api
import ru.maksonic.beresta.elm.core.ElmBaseModel.Companion.loadedSuccess
import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox

/**
 * @Author maksonic on 30.05.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

@OptIn(ExperimentalMaterial3Api::class)
class NotesTrashSandbox(program: NotesTrashProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchRemovedData),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedRemovedNotesResult -> fetchedDataResult(model, msg)
        is Msg.Inner.FetchedError -> ElmUpdate(model)
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
    ): UpdateResult =
        ElmUpdate(model.copy(base = model.base.loadedSuccess, notes = model.notes.copy(msg.notes)))

    private fun onTopBarBackPressed(model: Model): UpdateResult {
        val onClickEffect =
            if (model.modalBottomSheetState.isVisible) Eff.HideModalSheet else Eff.NavigateBack
        return ElmUpdate(model, effects = setOf(onClickEffect))
    }

    private fun onTrashedFoldersBtnClicked(model: Model): UpdateResult {
        val eff =
            if (model.isVisibleModalSheet) emptySet() else setOf(Eff.NavigateToTrashedFoldersList)
        return ElmUpdate(
            model = model.copy(
                isSelectionState = false,
                isVisibleModalSheet = false,
                selectedList = emptySet()
            ),
            effects = eff
        )
    }

    private fun onNoteClicked(model: Model, msg: Msg.Ui.OnNoteClicked): UpdateResult =
        if (model.isSelectionState)
            baseOnNoteAction(model, msg.id)
        else
            ElmUpdate(
                model = model.copy(
                    isVisibleModalSheet = !model.isVisibleModalSheet,
                    currentClickedNoteId = msg.id
                ),
            )


    private fun onNoteLongClicked(model: Model, msg: Msg.Ui.OnNoteLongClicked): UpdateResult =
        baseOnNoteAction(model, msg.id)

    private fun baseOnNoteAction(model: Model, noteId: Long): UpdateResult {
        val selected = model.selectedList.toMutableSet().also { list ->
            model.notes.data.forEach { note ->
                if (noteId == note.id) {
                    if (list.contains(note)) list.remove(note) else list.add(note)
                }
            }
        }.toSet()

        return ElmUpdate(
            model.copy(
                selectedList = selected,
                isSelectionState = true,
            )
        )
    }

    private fun onSelectAllNotesClicked(model: Model): UpdateResult {
        val selected = model.selectedList.toMutableSet().also { list ->
            if (list.containsAll(model.notes.data)) list.clear()
            else list.addAll(model.notes.data)
        }.toSet()

        return ElmUpdate(model.copy(selectedList = selected))
    }

    private fun onCancelSelectionClicked(model: Model): UpdateResult =
        ElmUpdate(model.copy(selectedList = emptySet(), isSelectionState = false))

    private fun updatedModalSheetState(
        model: Model,
        msg: Msg.Inner.UpdatedModalSheetState
    ): UpdateResult =
        ElmUpdate(model.copy(isVisibleModalSheet = msg.isVisible))

    private fun hideModalBottomSheet(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.HideModalSheet))

    private fun onModalSheetRestoreNoteClicked(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(isVisibleAcceptDeleteNotesDialog = false),
        commands = setOf(
            Cmd.DeleteOrRestoreNotes(
                isRestore = true,
                notes = model.notes.data.filter { it.id == model.currentClickedNoteId }
            )),
        effects = setOf(Eff.HideModalSheet)
    )

    private fun showedAcceptDeleteWarningDialog(model: Model): UpdateResult =
        ElmUpdate(
            model = model.copy(
                isVisibleAcceptDeleteNotesDialog = true,
                isSingleItemAction = model.selectedList.count() <= 1
            ),
            effects = setOf(Eff.HideModalSheet)
        )

    private fun onBottomBarRestoreSelectedClicked(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(selectedList = emptySet(), isSelectionState = false),
        commands = setOf(Cmd.DeleteOrRestoreNotes(isRestore = true, model.selectedList.toList()))
    )

    private fun onCancelDeleteWarningDialogClicked(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(
            isVisibleAcceptDeleteNotesDialog = false,
            currentClickedNoteId = null,
        )
    )

    private fun onAcceptDeleteWarningDialogClicked(model: Model): UpdateResult {
        val currentClickedNote = model.notes.data.filter { it.id == model.currentClickedNoteId }
        val delete = if (model.selectedList.isEmpty() && model.currentClickedNoteId != null)
            currentClickedNote else model.selectedList.toList()

        return ElmUpdate(
            model = model.copy(
                isVisibleAcceptDeleteNotesDialog = false,
                currentClickedNoteId = null,
                isSelectionState = false,
                selectedList = emptySet()
            ),
            commands = setOf(Cmd.DeleteOrRestoreNotes(isRestore = false, delete))
        )
    }
}
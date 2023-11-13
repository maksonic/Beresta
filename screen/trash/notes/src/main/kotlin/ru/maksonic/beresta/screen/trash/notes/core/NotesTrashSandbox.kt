package ru.maksonic.beresta.screen.trash.notes.core

import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.unselectAll
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.Loading
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.loadedFailure
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel.Companion.loadedSuccess
import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox

/**
 * @Author maksonic on 30.05.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class NotesTrashSandbox(program: NotesTrashProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchData),
    subscriptions = listOf(program)
) {

    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Inner.FetchedDataResult -> fetchedDataResult(model, msg)
        is Msg.Inner.FetchedDataFail -> fetchedDataFail(model, msg)
        is Msg.Ui.OnRetryFetchDataClicked -> retryFetchData(model)
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
        is Msg.Ui.OnDeleteAllNotesClicked -> onDeleteAllNotesClicked(model)
    }

    private fun fetchedDataResult(
        model: Model,
        msg: Msg.Inner.FetchedDataResult
    ): Update =
        ElmUpdate(model.copy(base = model.base.loadedSuccess, notes = model.notes.copy(msg.notes)))

    private fun fetchedDataFail(model: Model, msg: Msg.Inner.FetchedDataFail): Update =
        ElmUpdate(
            model.copy(
                base = model.base.loadedFailure(msg.message),
                notes = NoteUi.Collection.Empty
            )
        )

    private fun retryFetchData(model: Model): Update = ElmUpdate(
        model = model.copy(base = model.base.Loading),
        commands = setOf(Cmd.RetryFetchData)
    )

    private fun onTopBarBackPressed(model: Model): Update = ElmUpdate(
        model = model,
        effects = setOf(if (model.modalSheet.isVisible) Eff.HideModalSheet else Eff.NavigateBack)
    )

    private fun onTrashedFoldersBtnClicked(model: Model): Update = ElmUpdate(
        model = model.copy(
            isSelection = false,
            modalSheet = model.modalSheet.copy(isVisible = false),
            notes = model.notes.unselectAll(),
        ),
        effects = if (model.modalSheet.isVisible) emptySet()
        else setOf(Eff.NavigateToTrashedFoldersList)
    )

    private fun onNoteClicked(model: Model, msg: Msg.Ui.OnNoteClicked): Update =
        if (model.isSelection)
            baseOnNoteAction(model, msg.id)
        else
            ElmUpdate(
                model = model.copy(
                    modalSheet = model.modalSheet.copy(isVisible = !model.modalSheet.isVisible),
                    currentClickedNoteId = msg.id
                )
            )


    private fun onNoteLongClicked(model: Model, msg: Msg.Ui.OnNoteLongClicked): Update =
        baseOnNoteAction(model, msg.id)

    private fun baseOnNoteAction(model: Model, folderId: Long): Update {
        val notes = model.notes.copy(model.notes.data.map { note ->
            val isSelected = if (note.id == folderId) !note.isSelected else note.isSelected
            note.copy(isSelected = isSelected)
        })

        return ElmUpdate(model.copy(notes = notes, isSelection = true))
    }

    private fun onSelectAllNotesClicked(model: Model): Update {
        val allSelected = model.notes.data.all { it.isSelected }
        val notes = model.notes.copy(model.notes.data.map { item ->
            item.copy(isSelected = !allSelected)
        })

        return ElmUpdate(model.copy(notes = notes))
    }

    private fun onCancelSelectionClicked(model: Model): Update =
        ElmUpdate(model.copy(notes = model.notes.unselectAll(), isSelection = false))

    private fun updatedModalSheetState(
        model: Model,
        msg: Msg.Inner.UpdatedModalSheetState
    ): Update =
        ElmUpdate(model.copy(modalSheet = model.modalSheet.copy(isVisible = msg.isVisible)))

    private fun hideModalBottomSheet(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.HideModalSheet))

    private fun onModalSheetRestoreNoteClicked(model: Model): Update {
        val findNote = model.notes.data.find { it.id == model.currentClickedNoteId }
        val restoredNote = if (findNote != null) listOf(findNote) else emptyList()

        return ElmUpdate(
            model = model.copy(isVisibleAcceptDeleteNotesDialog = false),
            commands = setOf(Cmd.RestoreNotes(notes = restoredNote)),
            effects = setOf(Eff.HideModalSheet)
        )
    }

    private fun showedAcceptDeleteWarningDialog(model: Model): Update = ElmUpdate(
        model = model.copy(
            isVisibleAcceptDeleteNotesDialog = true,
            isSingleSelection = model.notes.data.count { it.isSelected } <= 1
        ),
        effects = setOf(Eff.HideModalSheet)
    )

    private fun onBottomBarRestoreSelectedClicked(model: Model): Update = ElmUpdate(
        model = model.copy(isSelection = false),
        commands = setOf(Cmd.RestoreNotes(model.notes.data.filter { it.isSelected }))
    )

    private fun onCancelDeleteWarningDialogClicked(model: Model): Update = ElmUpdate(
        model = model.copy(
            isVisibleAcceptDeleteNotesDialog = false, currentClickedNoteId = null
        )
    )

    private fun onAcceptDeleteWarningDialogClicked(model: Model): Update {
        val selectedList = model.notes.data.filter { it.isSelected }
        val currentClickedFolder = model.notes.data.filter { it.id == model.currentClickedNoteId }
        val delete = if (selectedList.isEmpty() && model.currentClickedNoteId != null)
            currentClickedFolder else selectedList

        return ElmUpdate(
            model = model.copy(
                isVisibleAcceptDeleteNotesDialog = false,
                currentClickedNoteId = null,
                isSelection = false,
            ),
            commands = setOf(Cmd.DeleteNotes(delete))
        )
    }

    private fun onDeleteAllNotesClicked(model: Model): Update {
        val notes = model.notes.copy(model.notes.data.map { item ->
            item.copy(isSelected = true)
        })

        return ElmUpdate(
            model = model.copy(
                notes = notes,
                isVisibleAcceptDeleteNotesDialog = true,
                currentClickedNoteId = null,
                isSelection = true,
                isSingleSelection = false
            )
        )
    }
}
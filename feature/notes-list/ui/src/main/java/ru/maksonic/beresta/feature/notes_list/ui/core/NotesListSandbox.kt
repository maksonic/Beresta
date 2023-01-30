package ru.maksonic.beresta.feature.notes_list.ui.core

import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanel
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature
import ru.maksonic.beresta.feature.notes_list.api.NoteUi


/**
 * @Author maksonic on 25.12.2022
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class NotesListSandbox(
    notesListProgram: NotesListProgram,
    bottomPanelActionsProgram: BottomPanelActionsProgram,
    bottomPanelFeature: BottomPanelFeature
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model(
        base = BaseModel(isLoading = true), bottomPanelState = bottomPanelFeature.state
    ),
    initialCmd = setOf(Cmd.FetchData, Cmd.ListenBottomPanelActions),
    subscriptions = listOf(notesListProgram, bottomPanelActionsProgram)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.RetryFetching -> retryFetching(model)
        is Msg.Ui.OnNoteClicked -> onNoteClicked(model, msg)
        is Msg.Ui.SelectAll -> selectAllNotes(model)
        is Msg.Ui.OnNoteLongClicked -> onNoteLongClicked(model, msg)
        is Msg.Inner.FetchingSuccess -> fetchingSuccess(model, msg)
        is Msg.Inner.FetchingError -> fetchingError(model, msg)
        is Msg.Ui.RemoveSelectedItems -> onBottomPanelRemoveClicked(model)
        is Msg.Inner.AfterRemoveResult -> afterRemoveNotes(model, msg)
        is Msg.Ui.CancelSelection -> cancelNotesSelection(model)
        is Msg.Ui.PinSelected -> pinSelectedNotesToTopList(model)
        is Msg.Ui.ReplaceSelected -> replaceSelectedNotesToFolder(model, msg)
        is Msg.Ui.OnSelectFilter -> onFilterSelected(model, msg)
        is Msg.Inner.SelectPanelVisibility -> selectPanelVisibilityState(model, msg)
    }

    private fun selectPanelVisibilityState(
        model: Model, msg: Msg.Inner.SelectPanelVisibility
    ): UpdateResult {
        val state = if (msg.isVisible) BottomPanel.State.SELECTED else BottomPanel.State.IDLE
        val panelState = model.bottomPanelState.update { old -> old.copy(state = state) }
        return UpdatedModel(model.copy(bottomPanelState = panelState))
    }

    private fun retryFetching(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(base = model.base.copy(isLoading = true)),
        commands = setOf(Cmd.FetchData)
    )

    private fun fetchingSuccess(model: Model, msg: Msg.Inner.FetchingSuccess): UpdateResult {
        val sortNotes =
            msg.notes.sortedWith(comparator = compareByDescending<NoteUi> { it.isPinned }.thenBy { it.id })
        return UpdatedModel(
            model = model.copy(
                base = model.base.copy(isLoading = false, isSuccessLoading = true),
                notes = sortNotes
            )
        )
    }

    private fun fetchingError(model: Model, msg: Msg.Inner.FetchingError): UpdateResult =
        UpdatedModel(
            model = model.copy(
                base = model.base.copy(
                    isLoading = false,
                    isSuccessLoading = false,
                    isErrorLoading = true,
                    errorMsg = msg.errorMsg
                ),
            )
        )

    private fun onBottomPanelRemoveClicked(model: Model): UpdateResult {
        val remove = model.notes.map { note ->
            return@map note.copy(isMovedToTrash = note.isSelected)
        }

        val bottomPanelState = model.bottomPanelState.update { panelState ->
            panelState.copy(selectedCount = 0)
        }

        return UpdatedModel(
            model.copy(
                notes = remove.filter { !it.isMovedToTrash },
                isSelectionState = false,
                bottomPanelState = bottomPanelState
            ),
            commands = setOf(Cmd.RemoveSelected(remove))
        )
    }

    private fun afterRemoveNotes(model: Model, msg: Msg.Inner.AfterRemoveResult): UpdateResult =
        UpdatedModel(
            model.copy(
                notes = msg.notes,
                isSelectionState = msg.isAllSelected,
                bottomPanelState = model.bottomPanelState.update { it.copy(selectedCount = 0) },
                isVisibleRemoveAllNotesDialog = msg.isAllSelected,
            )
        )

    private fun onNoteClicked(model: Model, msg: Msg.Ui.OnNoteClicked): UpdateResult =
        if (model.isSelectionState)
            baseOnNoteAction(model, msg.id)
        else
            UpdatedModel(model, effects = setOf(Eff.ShowNoteForEdit(msg.id)))


    private fun onNoteLongClicked(model: Model, msg: Msg.Ui.OnNoteLongClicked): UpdateResult =
        baseOnNoteAction(model, msg.id)

    private fun selectAllNotes(model: Model): UpdateResult {
        var selectedCount = 0
        val isAllSelected = model.notes.all { note -> note.isSelected }
        val notes = model.notes.map { note ->
            selectedCount = if (isAllSelected) 0 else model.notes.map { it.isSelected }.count()
            return@map note.copy(isSelected = !isAllSelected)

        }
        val isSelected = notes.map { it.isSelected }.contains(true)
        val isShowUnpinButton = notes.all { note -> note.isPinned }

        return UpdatedModel(
            model = model.copy(
                notes = notes,
                isSelectionState = isSelected,
                bottomPanelState = model.bottomPanelState.update {
                    it.copy(selectedCount = selectedCount)
                }
            ),
            commands = setOf(Cmd.PassPinNotesStateToBottomPanel(isShowUnpinButton))
        )
    }

    private fun cancelNotesSelection(model: Model): UpdateResult {
        val unselectedAll = model.notes.map { it.copy(isSelected = false) }
        return UpdatedModel(model = model.copy(
            notes = unselectedAll,
            isSelectionState = false,
            bottomPanelState = model.bottomPanelState.update { it.copy(selectedCount = 0) }
        ))
    }

    private fun pinSelectedNotesToTopList(model: Model): UpdateResult {
        val selectedNotes = model.notes.filter { it.isSelected }
        val isSelectedContainsUnpinnedNotes = selectedNotes.map { it.isPinned }.contains(false)
        val notes = model.notes.map { note ->
            val isPinned = if (isSelectedContainsUnpinnedNotes) true else !note.isPinned
            return@map if (note.isSelected) note.copy(isPinned = isPinned) else note
        }

        return UpdatedModel(
            model = model.copy(notes = notes),
            commands = setOf(Cmd.UpdatePinnedNotesInCache(notes))
        )
    }

    private fun replaceSelectedNotesToFolder(
        model: Model, msg: Msg.Ui.ReplaceSelected
    ): UpdateResult = UpdatedModel(model)

    private fun onFilterSelected(model: Model, msg: Msg.Ui.OnSelectFilter): UpdateResult {
        val filters = model.chipsNotesFilter.mapIndexed { index, filter ->
            if (index == msg.index)
                filter.copy(isSelected = true)
            else
                filter
        }
        return UpdatedModel(model.copy(chipsNotesFilter = filters))
    }

    private fun baseOnNoteAction(model: Model, noteId: Long): UpdateResult {
        var counter = model.bottomPanelState.mutableState.value.selectedCount
        val updateCounter = { isSelected: Boolean -> if (isSelected) counter++ else counter-- }
        val afterSelectNotes = model.notes.map { note ->
            if (note.id == noteId) updateCounter(!note.isSelected)
            return@map if (note.id == noteId) note.copy(isSelected = !note.isSelected) else note
        }
        val isSelected = afterSelectNotes.map { it.isSelected }.contains(true)
        val selectedNotes = afterSelectNotes.filter { it.isSelected }
        val isShowUnpinButton = !selectedNotes.map { it.isPinned }.contains(false)
        val bottomPanelState = model.bottomPanelState.update { it.copy(selectedCount = counter) }

        return UpdatedModel(
            model.copy(
                notes = afterSelectNotes,
                isSelectionState = isSelected,
                bottomPanelState = bottomPanelState
            ),
            commands = setOf(Cmd.PassPinNotesStateToBottomPanel(isShowUnpinButton))
        )
    }
}

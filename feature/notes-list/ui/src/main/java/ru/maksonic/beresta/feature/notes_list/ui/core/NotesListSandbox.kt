package ru.maksonic.beresta.feature.notes_list.ui.core

import androidx.compose.runtime.mutableStateOf
import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanel
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature
import ru.maksonic.beresta.feature.notes_list.api.NoteUi


/**
 * @Author maksonic on 25.12.2022
 */
private typealias UpdateResult = UpdatedModel<Feature.Model, Set<Feature.Cmd>, Set<Feature.Eff>>

class NotesListSandbox(
    notesListProgram: NotesListProgram,
    bottomPanelActionProgram: BottomPanelActionProgram,
    bottomPanelFeature: BottomPanelFeature
) : Sandbox<Feature.Model, Feature.Msg, Feature.Cmd, Feature.Eff>(
    initialModel = Feature.Model(
        base = BaseModel(isLoading = true), bottomPanelState = bottomPanelFeature.state
    ),
    initialCmd = setOf(Feature.Cmd.FetchData, Feature.Cmd.ListenBottomPanelActions),
    subscriptions = listOf(notesListProgram, bottomPanelActionProgram)
) {
    override fun update(msg: Feature.Msg, model: Feature.Model): UpdateResult = when (msg) {
        is Feature.Msg.Ui.RetryFetching -> retryFetching(model)
        is Feature.Msg.Ui.OnNoteClicked -> onNoteClicked(model, msg)
        is Feature.Msg.Ui.SelectAllNotes -> selectAllNotes(model)
        is Feature.Msg.Ui.OnNoteLongClicked -> onNoteLongClicked(model, msg)
        is Feature.Msg.Inner.FetchingSuccess -> fetchingSuccess(model, msg)
        is Feature.Msg.Inner.FetchingError -> fetchingError(model, msg)
        is Feature.Msg.Ui.RemoveSelectedItems -> afterRemoveSelectedNotes(model)
        is Feature.Msg.Ui.CancelNotesSelection -> cancelNotesSelection(model)
        is Feature.Msg.Ui.PinSelectedNotes -> pinSelectedNotesToTopList(model)
        is Feature.Msg.Ui.ReplaceSelectedNotes -> replaceSelectedNotesToFolder(model, msg)
        is Feature.Msg.Ui.OnSelectNotesFilter -> onFilterSelected(model, msg)
        is Feature.Msg.Inner.SelectPanelVisibility -> selectPanelVisibilityState(model, msg)
    }

    private fun selectPanelVisibilityState(
        model: Feature.Model,
        msg: Feature.Msg.Inner.SelectPanelVisibility
    ): UpdateResult {
        val state = if (msg.isVisible) BottomPanel.State.SELECTED else BottomPanel.State.IDLE
        val panelState = model.bottomPanelState.update { old -> old.copy(state = state) }
        return UpdatedModel(model.copy(bottomPanelState = panelState))
    }

    private fun retryFetching(model: Feature.Model): UpdateResult =
        UpdatedModel(
            model.copy(base = model.base.copy(isLoading = true)),
            commands = setOf(Feature.Cmd.FetchData)
        )

    private fun fetchingSuccess(
        model: Feature.Model,
        msg: Feature.Msg.Inner.FetchingSuccess
    ): UpdateResult =
        UpdatedModel(
            model.copy(
                base = model.base.copy(
                    isLoading = false,
                    isSuccessLoading = true
                ),
                notes = msg.notes
            )
        )

    private fun fetchingError(
        model: Feature.Model,
        msg: Feature.Msg.Inner.FetchingError
    ): UpdateResult =
        UpdatedModel(
            model.copy(
                base = model.base.copy(
                    isLoading = false,
                    isSuccessLoading = false,
                    isErrorLoading = true,
                    errorMsg = msg.errorMsg
                ),
            )
        )

    private fun afterRemoveSelectedNotes(
        model: Feature.Model,
    ): UpdateResult {
        val notes = model.notes.filter { !it.isSelected }
        val bottomDrawerState = model.bottomPanelState.update { it.copy(selectedCount = 0) }
        return UpdatedModel(
            model.copy(
                notes = notes,
                isSelectionState = false,
                bottomPanelState = bottomDrawerState
            )
        )
    }

    private fun onNoteClicked(
        model: Feature.Model,
        msg: Feature.Msg.Ui.OnNoteClicked
    ): UpdateResult {
        val isShowUnpinButton = mutableStateOf(false)
        var afterSelectedNotes: List<NoteUi> = model.notes
        var selectedCount = model.bottomPanelState.mutableState.value.selectedCount
        if (model.isSelectionState) {
            afterSelectedNotes = model.notes.map { note ->
                if (note.id == msg.id) {
                    val updatedNote = note.copy(isSelected = !note.isSelected)
                    if (updatedNote.isSelected) {
                        selectedCount++
                    } else {
                        selectedCount--
                    }
                    updatedNote
                } else
                    note
            }
        } else {
            // TODO: Action onClick note item
        }

        val isSelected = afterSelectedNotes.map { it.isSelected }.contains(true)
        val selectedNotes = afterSelectedNotes.filter { it.isSelected }
        isShowUnpinButton.value = !selectedNotes.map { !it.isPinned }.contains(true)

        return UpdatedModel(
            model.copy(
                notes = afterSelectedNotes,
                isSelectionState = isSelected,
                bottomPanelState = model.bottomPanelState.update {
                    it.copy(selectedCount = selectedCount)
                }
            ),
            commands = setOf(Feature.Cmd.PassPinNotesStateToBottomPanel(isShowUnpinButton.value))
        )
    }

    private fun onNoteLongClicked(
        model: Feature.Model,
        msg: Feature.Msg.Ui.OnNoteLongClicked
    ): UpdateResult {
        val isShowUnpinButton = mutableStateOf(false)
        var selectedCount = model.bottomPanelState.mutableState.value.selectedCount
        val afterSelectedNotes = model.notes.map { note ->
            if (note.id == msg.id) {
                val updatedNote = note.copy(isSelected = !note.isSelected)
                if (updatedNote.isSelected) {
                    selectedCount++
                } else {
                    selectedCount--
                }
                updatedNote
            } else
                note
        }
        val isSelected = afterSelectedNotes.map { it.isSelected }.contains(true)
        val selectedNotes = afterSelectedNotes.filter { it.isSelected }
        isShowUnpinButton.value = !selectedNotes.map { !it.isPinned }.contains(true)

        return UpdatedModel(
            model.copy(
                notes = afterSelectedNotes,
                isSelectionState = isSelected,
                bottomPanelState = model.bottomPanelState.update {
                    it.copy(selectedCount = selectedCount)
                }
            ),
            commands = setOf(Feature.Cmd.PassPinNotesStateToBottomPanel(isShowUnpinButton.value))
        )
    }

    private fun selectAllNotes(model: Feature.Model): UpdateResult {
        var selectedCount = 0
        val predicate: (NoteUi) -> Boolean = { note -> note.isSelected }
        val notes = if (model.notes.all(predicate)) {
            model.notes.map { note ->
                selectedCount = 0
                note.copy(isSelected = false)
            }
        } else {
            model.notes.map { note ->
                selectedCount++
                note.copy(isSelected = true)
            }
        }

        val isSelected = notes.map { it.isSelected }.contains(true)
        return UpdatedModel(
            model.copy(
                notes = notes,
                isSelectionState = isSelected,
                bottomPanelState = model.bottomPanelState.update {
                    it.copy(selectedCount = selectedCount)
                }
            )
        )
    }

    private fun cancelNotesSelection(model: Feature.Model): UpdateResult {
        val unselectedAll = model.notes.map { it.copy(isSelected = false) }
        return UpdatedModel(
            model.copy(
                notes = unselectedAll,
                isSelectionState = false,
                bottomPanelState = model.bottomPanelState.update { it.copy(selectedCount = 0) }
            )
        )
    }

    private fun pinSelectedNotesToTopList(model: Feature.Model): UpdateResult {
        val selected = model.notes.filter { it.isSelected }
        val pinned = selected.filter { it.isPinned }

        val notes = model.notes.map { note ->

            if (selected.count() > pinned.count()) {
                if (note.isSelected) {
                    return@map note.copy(isPinned = true)
                } else {
                    return@map note
                }
            } else {
                if (note.isSelected) {
                    return@map note.copy(isPinned = !note.isPinned)
                } else {
                    return@map note
                }
            }
        }.sortedWith(comparator = compareByDescending<NoteUi> { it.isPinned }.thenBy { it.id })

        return UpdatedModel(
            model.copy(notes = notes)
        )
    }

    private fun replaceSelectedNotesToFolder(
        model: Feature.Model,
        msg: Feature.Msg.Ui.ReplaceSelectedNotes
    ): UpdateResult {

        return UpdatedModel(model)
    }

    private fun onFilterSelected(
        model: Feature.Model,
        msg: Feature.Msg.Ui.OnSelectNotesFilter
    ): UpdateResult {
        val filters = model.chipsNotesFilter.mapIndexed { index, filter ->
            if (index == msg.index)
                filter.copy(isSelected = true)
            else
                filter
        }
        return UpdatedModel(model.copy(chipsNotesFilter = filters))
    }
}

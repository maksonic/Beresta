package ru.maksonic.beresta.feature.notes_list.ui.core

import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel


/**
 * @Author maksonic on 25.12.2022
 */
private typealias UpdateResult = UpdatedModel<Feature.Model, Set<Feature.Cmd>, Set<Feature.Eff>>

class NotesListSandbox(
    notesListProgram: NotesListProgram
) : Sandbox<Feature.Model, Feature.Msg, Feature.Cmd, Feature.Eff>(
    initialModel = Feature.Model(base = BaseModel(isLoading = true)),
    initialCmd = setOf(Feature.Cmd.FetchData),
    subscriptions = listOf(notesListProgram)
) {
    override fun update(
        msg: Feature.Msg,
        model: Feature.Model
    ): UpdateResult = when (msg) {
        is Feature.Msg.Ui.RetryFetching -> retryFetching(model)
        is Feature.Msg.Ui.OnItemClicked -> UpdatedModel(
            model,
            effects = setOf(Feature.Eff.ShowNoteForEdit)
        )
        is Feature.Msg.Ui.SelectItemForRemove -> onItemSelectedForRemove(model, msg)
        is Feature.Msg.Ui.SelectAllItems -> selectAllNotes(model)
        is Feature.Msg.Ui.OnItemLongPressed -> onItemLongPressed(model, msg)
        is Feature.Msg.Inner.Idle -> idle(model)
        is Feature.Msg.Inner.FetchingSuccess -> fetchingSuccess(model, msg)
        is Feature.Msg.Inner.FetchingError -> fetchingError(model, msg)
        is Feature.Msg.Ui.RemoveSelectedItems -> afterRemoveSelectedNotes(model)
        is Feature.Msg.Ui.CancelNotesSelection -> cancelNotesSelection(model)
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

    private fun idle(model: Feature.Model): UpdateResult =
        UpdatedModel(
            model.copy(
                base = model.base.copy(
                    isLoading = false,
                    isSuccessLoading = false,
                    isErrorLoading = false,
                    errorMsg = "",
                    isIdle = true
                ),
                isSelectionState = false
            )
        )

    private fun afterRemoveSelectedNotes(
        model: Feature.Model,
    ): UpdateResult {
        val notes = model.notes.filter { !it.isSelected }
        return UpdatedModel(model.copy(notes = notes, isSelectionState = false))
    }

    private fun selectAllNotes(model: Feature.Model): UpdateResult {
        val notes = model.notes.map { note ->
            note.copy(isSelected = true)
        }
        val isSelected = notes.map { it.isSelected }.contains(true)
        return UpdatedModel(model.copy(notes = notes, isSelectionState = isSelected))
    }
    private fun onItemSelectedForRemove(
        model: Feature.Model,
        msg: Feature.Msg.Ui.SelectItemForRemove
    ): UpdateResult {
        val notes = model.notes.map { note ->
            if (note.id == msg.id) {
                note.copy(isSelected = msg.isSelected)
            } else
                note
        }
        val isSelected = notes.map { it.isSelected }.contains(true)
        return UpdatedModel(model.copy(notes = notes, isSelectionState = isSelected))
    }

    private fun onItemLongPressed(
        model: Feature.Model,
        msg: Feature.Msg.Ui.OnItemLongPressed
    ): UpdateResult {
        val selectedList = model.selectedNotes
        selectedList.add(msg.id)
        return UpdatedModel(model.copy(selectedNotes = selectedList))
    }

    private fun cancelNotesSelection(model: Feature.Model): UpdateResult =
        UpdatedModel(
            model = model.copy(
                notes = model.notes.map { it.copy(isSelected = false) },
                isSelectionState = false
            )
        )
}
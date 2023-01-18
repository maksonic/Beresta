package ru.maksonic.beresta.feature.notes_list.ui.core

import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.notes_list.api.NoteUi


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
        is Feature.Msg.Ui.OnNoteClicked -> onNoteClicked(model, msg)
        is Feature.Msg.Ui.SelectAllItems -> selectAllNotes(model)
        is Feature.Msg.Ui.OnNoteLongClicked -> onItemLongPressed(model, msg)
        is Feature.Msg.Inner.FetchingSuccess -> fetchingSuccess(model, msg)
        is Feature.Msg.Inner.FetchingError -> fetchingError(model, msg)
        is Feature.Msg.Ui.RemoveSelectedItems -> afterRemoveSelectedNotes(model)
        is Feature.Msg.Ui.CancelNotesSelection -> cancelNotesSelection(model)
        is Feature.Msg.Ui.OnSelectNotesFilter -> onFilterSelected(model, msg)
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
        return UpdatedModel(model.copy(notes = notes, isSelectionState = false))
    }

    private fun selectAllNotes(model: Feature.Model): UpdateResult {
        val notes = model.notes.map { note ->
            note.copy(isSelected = true)
        }
        val isSelected = notes.map { it.isSelected }.contains(true)
        return UpdatedModel(model.copy(notes = notes, isSelectionState = isSelected))
    }

    private fun onNoteClicked(
        model: Feature.Model,
        msg: Feature.Msg.Ui.OnNoteClicked
    ): UpdateResult {
        var afterSelectedNotes = emptyList<NoteUi>()
        val isSelected = model.notes.map { it.isSelected }.contains(true)

        afterSelectedNotes = if (isSelected) {
            model.notes.map { oldNote ->
                if (oldNote.id == msg.id) {
                    oldNote.copy(isSelected = !oldNote.isSelected)
                } else {
                    oldNote
                }
            }
        } else {
            model.notes
        }
        return UpdatedModel(model.copy(notes = afterSelectedNotes))
    }

    private fun onItemLongPressed(
        model: Feature.Model,
        msg: Feature.Msg.Ui.OnNoteLongClicked
    ): UpdateResult {

        val afterSelectedNotes = model.notes.map { oldNote ->
            if (oldNote.id == msg.id) {
                oldNote.copy(isSelected = !oldNote.isSelected)
            } else {
                oldNote
            }
        }
        val isSelected = model.notes.map { it.isSelected }.contains(true)
        return UpdatedModel(model.copy(notes = afterSelectedNotes, isSelectionState = isSelected))
    }

    private fun cancelNotesSelection(model: Feature.Model): UpdateResult =
        UpdatedModel(
            model = model.copy(
                notes = model.notes.map { it.copy(isSelected = false) },
                isSelectionState = false
            )
        )

    private fun onFilterSelected(
        model: Feature.Model,
        msg: Feature.Msg.Ui.OnSelectNotesFilter
    ): UpdateResult {
        /* val filters = model.notesFilter.map { filter ->
             if (filter.ind == msg.folderId) {
                 filter.copy(isSelected = true)
             } else
                 filter
         }*/
        val filters = model.chipsNotesFilter.mapIndexed { index, filter ->
            if (index == msg.index) {
                filter.copy(isSelected = true)
            } else {
                filter
            }
        }
        return UpdatedModel(model.copy(chipsNotesFilter = filters))
    }
}

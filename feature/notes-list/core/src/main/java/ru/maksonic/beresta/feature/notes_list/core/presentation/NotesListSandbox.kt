package ru.maksonic.beresta.feature.notes_list.core.presentation

import android.util.Log
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 21.02.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class NotesListSandbox : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model(),
    subscriptions = listOf()
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedNotesCollection -> fetchedNotes(model, msg)
        is Msg.Ui.OnChipFilterClicked -> onFilterChipClicked(model, msg)
        is Msg.Ui.OnNoteClicked -> onNoteClicked(model, msg)
        is Msg.Ui.OnNoteLongClicked -> onNoteLongClicked(model, msg)
    }

    private fun fetchedNotes(model: Model, msg: Msg.Inner.FetchedNotesCollection): UpdateResult =
        UpdatedModel(model = model.copy(notes = msg.notes))

    private fun onFilterChipClicked(model: Model, msg: Msg.Ui.OnChipFilterClicked): UpdateResult {
        val afterSelect = model.filters.copy(data = model.filters.data.map { note ->
            note.copy(isSelected = note.id == msg.id)
        })
        return UpdatedModel(model.copy(filters = afterSelect))
    }

    private fun onNoteClicked(model: Model, msg: Msg.Ui.OnNoteClicked): UpdateResult =
        if (model.isSelectionState)
            baseOnNoteAction(model, msg.id)
        else
            UpdatedModel(model, effects = setOf(Eff.ShowNoteForEdit(msg.id)))


    private fun onNoteLongClicked(model: Model, msg: Msg.Ui.OnNoteLongClicked): UpdateResult =
        baseOnNoteAction(model, msg.id)

    private fun baseOnNoteAction(model: Model, noteId: Long): UpdateResult {
        val afterSelectNotes = model.notes.copy(data = model.notes.data.map { note ->
            return@map if (note.id == noteId) note.copy(isSelected = !note.isSelected) else note
        })

        val isSelected = afterSelectNotes.data.map { it.isSelected }.contains(true)

        return UpdatedModel(
            model.copy(
                notes = afterSelectNotes,
                isSelectionState = isSelected,
            ),
        )
    }
}
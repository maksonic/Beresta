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
    }

    private fun fetchedNotes(model: Model, msg: Msg.Inner.FetchedNotesCollection): UpdateResult =
        UpdatedModel(model = model.copy(notes = msg.notes))

    private fun onFilterChipClicked(model: Model, msg: Msg.Ui.OnChipFilterClicked): UpdateResult {
        val afterSelect = model.filters.copy(data = model.filters.data.map { note ->
            note.copy(isSelected = note.id == msg.id)
        })
        return UpdatedModel(model.copy(filters = afterSelect))
    }
}
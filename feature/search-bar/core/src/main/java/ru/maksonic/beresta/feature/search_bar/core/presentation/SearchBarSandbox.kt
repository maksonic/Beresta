package ru.maksonic.beresta.feature.search_bar.core.presentation

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesCollection

/**
 * @Author maksonic on 21.02.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class SearchBarSandbox : Sandbox<Model, Msg, Cmd, Eff>(initialModel = Model()) {

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {

        is Msg.Inner.FetchedNotesCollection -> fetchedNotes(model, msg)
        is Msg.Ui.OnCollapseSearchBarClicked -> afterCollapseSearchBarClicked(model)
        is Msg.Ui.OnExpandSearchBarClicked -> afterExpandSearchBarClicked(model)
        is Msg.Inner.AfterUserInputQueryChanged -> afterQueryChange(model, msg)
        is Msg.Ui.OnClearInputQueryClicked -> afterClearQueryClicked(model)
    }

    private fun fetchedNotes(model: Model, msg: Msg.Inner.FetchedNotesCollection): UpdateResult =
        UpdatedModel(model.copy(notes = msg.collection))

    private fun afterCollapseSearchBarClicked(model: Model): UpdateResult =
        UpdatedModel(
            model.copy(
                isExpandedBar = false,
                searchQuery = "",
                searchList = model.searchList.copy(emptyList())
            )
        )

    private fun afterExpandSearchBarClicked(model: Model): UpdateResult =
        UpdatedModel(model.copy(isExpandedBar = true))


    private fun afterQueryChange(
        model: Model,
        msg: Msg.Inner.AfterUserInputQueryChanged
    ): UpdateResult {
        val searchResult = if (msg.updatedQuery.isEmpty()) emptyList()
        else model.notes.data.filter { note -> searchNoteBy(msg.updatedQuery, note) }

        return UpdatedModel(
            model = model.copy(
                searchQuery = msg.updatedQuery,
                searchList = NotesCollection(searchResult)
            )
        )
    }

    private fun afterClearQueryClicked(model: Model): UpdateResult =
        UpdatedModel(model.copy(searchQuery = "", searchList = model.searchList.copy(emptyList())))

    private fun searchNoteBy(query: String, note: NoteUi): Boolean {
        val ignoreCase = true
        return when {
            note.title.trim().contains(query.trim(), ignoreCase) -> true
            note.message.trim().contains(query.trim(), ignoreCase) -> true
            note.dateCreation.trim().contains(query.trim(), ignoreCase) -> true
            else -> false
        }
    }
}
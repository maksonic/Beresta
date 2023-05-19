package ru.maksonic.beresta.feature.search_bar.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarState

/**
 * @Author maksonic on 21.02.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class SearchBarSandbox : Sandbox<Model, Msg, Cmd, Eff>(initialModel = Model()) {

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnCollapseSearchBarClicked -> onCollapseSearchBarClicked(model)
        is Msg.Ui.OnExpandSearchBarClicked -> onExpandSearchBarClicked(model)
        is Msg.Inner.UpdatedUserInputQueryChanged -> updatedInputField(model, msg)
        is Msg.Ui.OnClearInputQueryClicked -> onClearQueryClicked(model)
        is Msg.Inner.UpdatedSearchBarState -> updatedSearchBarState(model, msg)
    }

    private fun onCollapseSearchBarClicked(model: Model): UpdateResult =
        UpdatedModel(
            model.copy(
                barState = SearchBarState.Collapsed,
                searchQuery = "",
                searchList = model.searchList.copy(emptyList())
            )
        )

    private fun onExpandSearchBarClicked(model: Model): UpdateResult =
        UpdatedModel(model.copy(barState = SearchBarState.Expanded))

    private fun updatedInputField(
        model: Model,
        msg: Msg.Inner.UpdatedUserInputQueryChanged
    ): UpdateResult {
        val searchResult = if (msg.updatedQuery.isEmpty()) emptyList()
        else model.notes.data.filter { note -> searchNoteBy(msg.updatedQuery, note) }

        return UpdatedModel(
            model = model.copy(
                searchQuery = msg.updatedQuery,
                searchList = NoteUi.Collection(searchResult)
            )
        )
    }


    private fun onClearQueryClicked(model: Model): UpdateResult =
        UpdatedModel(model.copy(searchQuery = "", searchList = model.searchList.copy(emptyList())))

    private fun searchNoteBy(query: String, note: NoteUi): Boolean {
        val ignoreCase = true
        return when {
            note.title.trim().contains(query.trim(), ignoreCase) -> true
            note.message.trim().contains(query.trim(), ignoreCase) -> true
            //  note.dateCreation.trim().contains(query.trim(), ignoreCase) -> true
            else -> false
        }
    }

    private fun updatedSearchBarState(
        model: Model,
        msg: Msg.Inner.UpdatedSearchBarState
    ): UpdateResult =
        UpdatedModel(model.copy(barState = msg.state))
}
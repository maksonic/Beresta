package ru.maksonic.beresta.feature.search_bar.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarState

/**
 * @Author maksonic on 21.02.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class SearchBarSandbox(program: SearchProgram) :
    Sandbox<Model, Msg, Cmd, Eff>(initialModel = Model.Initial, subscriptions = listOf(program)) {

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnCollapseSearchBarClicked -> onCollapseSearchBarClicked(model)
        is Msg.Ui.OnExpandSearchBarClicked -> onExpandSearchBarClicked(model)
        is Msg.Inner.UpdatedSelectedBarState -> updatedSelectedBarState(model, msg)
        is Msg.Inner.UpdatedUserInputQueryChanged -> updatedInputField(model, msg)
        is Msg.Ui.OnClearInputQueryClicked -> onClearQueryClicked(model)
        is Msg.Inner.FetchedResultData -> fetchedResultData(model, msg)
        is Msg.Inner.FetchedResultError -> fetchedResultError(model, msg)
    }

    private fun onCollapseSearchBarClicked(model: Model): UpdateResult =
        UpdatedModel(
            model.copy(
                barState = SearchBarState.Collapsed,
                searchQuery = "",
                searchList = model.searchList.copy(emptyList())
            ),
            effects = setOf(Eff.UpdateSharedBarState(SearchBarState.Collapsed))
        )

    private fun onExpandSearchBarClicked(model: Model): UpdateResult =
        UpdatedModel(
            model.copy(barState = SearchBarState.Expanded),
            commands = setOf(Cmd.FetchNotesList),
            effects = setOf(Eff.UpdateSharedBarState(SearchBarState.Expanded))
        )

    private fun updatedSelectedBarState(
        model: Model,
        msg: Msg.Inner.UpdatedSelectedBarState
    ): UpdateResult {
        val barState = when (model.barState) {
            SearchBarState.Collapsed -> {
                if (msg.isSelected) SearchBarState.Selected else SearchBarState.Collapsed
            }

            SearchBarState.Selected -> {
                if (msg.isSelected) SearchBarState.Selected else SearchBarState.Collapsed
            }

            else -> SearchBarState.Expanded
        }


        return UpdatedModel(
            model.copy(barState = barState),
            effects = setOf(Eff.UpdateSharedBarState(barState))
        )
    }

    private fun updatedInputField(
        model: Model,
        msg: Msg.Inner.UpdatedUserInputQueryChanged
    ): UpdateResult {
        val searchResult = if (msg.updatedQuery.isEmpty()) emptyList()
        else model.notes.data.filter { note -> searchNoteBy(msg.updatedQuery, note) }

        return UpdatedModel(
            model = model.copy(
                searchQuery = msg.updatedQuery,
                searchList = model.searchList.copy(searchResult)
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
            else -> false
        }
    }

    private fun fetchedResultData(model: Model, msg: Msg.Inner.FetchedResultData): UpdateResult {
        return UpdatedModel(
            model.copy(
                base = model.base.copy(isLoading = false, isSuccessLoading = true),
                notes = msg.notes
            )
        )
    }

    private fun fetchedResultError(model: Model, msg: Msg.Inner.FetchedResultError): UpdateResult =
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
}
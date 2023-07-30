package ru.maksonic.beresta.screen.hidden_notes.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.screen.hidden_notes.core.Model
import ru.maksonic.beresta.screen.hidden_notes.core.Msg
import ru.maksonic.beresta.screen.hidden_notes.ui.SendMessage

/**
 * @Author maksonic on 21.07.2023
 */
@Composable
internal fun SearchBar(
    model: State<Model>,
    send: SendMessage,
    isColoredBackplate: State<Boolean>,
    searchBarApi: SearchBarApi.Ui = koinInject()
) {
    val searchBarState = rememberUpdatedState(model.value.searchBarState)
    val gridCount = rememberUpdatedState(if (listUiSortState.gridHiddenNotesCount == 1) 2 else 1)
    val actions = mapOf(
        SearchBarApi.ActionKey.OnCollapseBar to { send(Msg.Ui.OnCollapseSearchBar) },
        SearchBarApi.ActionKey.OnExpandBar to { send(Msg.Ui.OnExpandSearchBar) },
        SearchBarApi.ActionKey.OnCancelClicked to { send(Msg.Ui.CancelNotesSelection) },
        SearchBarApi.ActionKey.OnShareClicked to { send(Msg.Ui.OnCounterBarShareClicked) },
        SearchBarApi.ActionKey.OnSelectAllClicked to { send(Msg.Ui.OnCounterBarSelectAllClicked) },
        SearchBarApi.ActionKey.OnBackClicked to { send(Msg.Ui.OnTopBarBackPressed) },
        SearchBarApi.ActionKey.OnSortByClicked to { send(Msg.Ui.OnTopBarSortNotesClicked) },
        SearchBarApi.ActionKey.OnChangeGridClicked to {
            send(Msg.Ui.OnTopBarGridViewClicked(gridCount.value))
        },
    )

    searchBarApi.Widget(
        state = searchBarState,
        isColoredBackplate = isColoredBackplate,
        actions = actions,
        onSearchResultNoteClicked = { send(Msg.Ui.OnNoteClicked(it)) },
    )
}
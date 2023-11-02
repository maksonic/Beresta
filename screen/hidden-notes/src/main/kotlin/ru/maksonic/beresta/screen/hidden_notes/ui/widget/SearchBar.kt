package ru.maksonic.beresta.screen.hidden_notes.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listHiddenNotesSortState
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiApi
import ru.maksonic.beresta.screen.hidden_notes.core.Model
import ru.maksonic.beresta.screen.hidden_notes.core.Msg
import ru.maksonic.beresta.screen.hidden_notes.ui.Send

/**
 * @Author maksonic on 21.07.2023
 */
@Composable
internal fun SearchBar(
    model: Model,
    send: Send,
    isColoredBackplate: State<Boolean>,
    searchBarApi: SearchBarUiApi = koinInject()
) {
    val gridCount = rememberUpdatedState(if (listHiddenNotesSortState.gridCount == 1) 2 else 1)
    val actions = mapOf(
        SearchBarUiApi.ActionKey.OnCollapseBar to { send(Msg.Ui.OnCollapseSearchBar) },
        SearchBarUiApi.ActionKey.OnExpandBar to { send(Msg.Ui.OnExpandSearchBar) },
        SearchBarUiApi.ActionKey.OnCancelClicked to { send(Msg.Ui.CancelNotesSelection) },
        SearchBarUiApi.ActionKey.OnShareClicked to { send(Msg.Ui.OnCounterBarShareClicked) },
        SearchBarUiApi.ActionKey.OnSelectAllClicked to { send(Msg.Ui.OnCounterBarSelectAllClicked) },
        SearchBarUiApi.ActionKey.OnBackClicked to { send(Msg.Ui.OnTopBarBackPressed) },
        SearchBarUiApi.ActionKey.OnSortByClicked to { send(Msg.Ui.OnTopBarSortNotesClicked) },
        SearchBarUiApi.ActionKey.OnChangeGridClicked to {
            send(Msg.Ui.OnTopBarGridViewClicked(gridCount.value))
        },
    )

    searchBarApi.Widget(
        state = model.searchBarState,
        isColoredBackplate = isColoredBackplate,
        actions = actions,
        onSearchResultNoteClicked = { send(Msg.Ui.OnNoteClicked(it)) },
    )
}
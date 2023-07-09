package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.screen.main.core.Model
import ru.maksonic.beresta.screen.main.core.Msg
import ru.maksonic.beresta.screen.main.ui.SendMessage

/**
 * @Author maksonic on 02.07.2023
 */
@Composable
fun MainSearchBar(
    model: State<Model>,
    send: SendMessage,
    searchBarApi: SearchBarApi.Ui = koinInject()
) {
    val searchBarState = rememberUpdatedState(model.value.searchBarState)
    val gridCount = rememberUpdatedState(if (listUiSortState.gridCount == 1) 2 else 1)
    val actions = mapOf(
        SearchBarApi.ActionKey.OnCollapseBar to { send(Msg.Ui.OnCollapseSearchBar) },
        SearchBarApi.ActionKey.OnExpandBar to { send(Msg.Ui.OnExpandSearchBar) },
        SearchBarApi.ActionKey.OnCancelClicked to { send(Msg.Ui.CancelNotesSelection) },
        SearchBarApi.ActionKey.OnShareClicked to { send(Msg.Ui.OnCounterBarShareClicked) },
        SearchBarApi.ActionKey.OnSelectAllClicked to { send(Msg.Ui.OnCounterBarSelectAllClicked) },
        SearchBarApi.ActionKey.OnChangeGridClicked to {
            send(Msg.Ui.OnChangeGridViewClicked(gridCount.value))
        },
    )

    searchBarApi.Widget(
        state = searchBarState,
        actions = actions,
        onSearchResultNoteClicked = { send(Msg.Ui.OnNoteClicked(it)) },
    )
}
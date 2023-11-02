package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersFeature
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listNotesSortState
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiApi
import ru.maksonic.beresta.screen.main.core.Model
import ru.maksonic.beresta.screen.main.core.Msg
import ru.maksonic.beresta.screen.main.ui.screen.Send

/**
 * @Author maksonic on 02.07.2023
 */
@Composable
internal fun MainSearchBar(
    model: Model,
    send: Send,
    isColoredBackplate: State<Boolean>,
    searchBarApi: SearchBarUiApi = koinInject()
) {
    val gridCount = rememberUpdatedState(if (listNotesSortState.gridCount == 1) 2 else 1)
    val currentFolderId = FoldersFeature.currentSelected
    val actions = mapOf(
        SearchBarUiApi.ActionKey.OnCollapseBar to { send(Msg.Ui.OnCollapseSearchBar) },
        SearchBarUiApi.ActionKey.OnExpandBar to { send(Msg.Ui.OnExpandSearchBar) },
        SearchBarUiApi.ActionKey.OnCancelClicked to { send(Msg.Ui.OnCancelSelectionClicked) },
        SearchBarUiApi.ActionKey.OnShareClicked to { send(Msg.Ui.OnCounterBarShareClicked) },
        SearchBarUiApi.ActionKey.OnSelectAllClicked to {
            send(Msg.Ui.OnCounterBarSelectAllClicked(currentFolderId))
        },
        SearchBarUiApi.ActionKey.OnChangeGridClicked to {
            send(Msg.Ui.OnChangeGridViewClicked(gridCount.value))
        },
    )

    searchBarApi.Widget(
        state = model.searchBarState,
        isColoredBackplate = isColoredBackplate,
        actions = actions,
        onSearchResultNoteClicked = { send(Msg.Ui.OnNoteClicked(it)) },
    )
}
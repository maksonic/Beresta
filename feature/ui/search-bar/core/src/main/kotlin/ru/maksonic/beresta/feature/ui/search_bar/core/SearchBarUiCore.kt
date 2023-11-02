package ru.maksonic.beresta.feature.ui.search_bar.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiApi
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiState

/**
 * @Author maksonic on 26.04.2023
 */
class SearchBarUiCore : SearchBarUiApi {

    @Composable
    override fun Widget(
        state: SearchBarUiState,
        isColoredBackplate: State<Boolean>,
        actions: Map<SearchBarUiApi.ActionKey, () -> Unit>,
        onSearchResultNoteClicked: (Long) -> Unit,
    ) {
        Container(state, isColoredBackplate, actions, onSearchResultNoteClicked)
    }
}


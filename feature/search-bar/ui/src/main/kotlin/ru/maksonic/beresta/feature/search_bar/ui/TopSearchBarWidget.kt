package ru.maksonic.beresta.feature.search_bar.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarUiState

/**
 * @Author maksonic on 26.04.2023
 */
class TopSearchBarWidget : SearchBarApi.Ui {

    @Composable
    override fun Widget(
        state: State<SearchBarUiState>,
        isColoredBackplate: State<Boolean>,
        actions: Map<SearchBarApi.ActionKey, () -> Unit>,
        onSearchResultNoteClicked: (Long) -> Unit,
    ) {
        Container(state, isColoredBackplate, actions, onSearchResultNoteClicked)
    }
}
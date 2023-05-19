package ru.maksonic.beresta.feature.search_bar.api

import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 30.04.2023
 */
enum class SearchBarState {
    Collapsed, Expanded, Selected
}

data class TopSearchBarSharedUiState(val state: SearchBarState) {
    companion object {
        private val DefaultState = TopSearchBarSharedUiState(SearchBarState.Collapsed)

        val Initial = object : SharedUiState<TopSearchBarSharedUiState>(DefaultState) {}
    }
}
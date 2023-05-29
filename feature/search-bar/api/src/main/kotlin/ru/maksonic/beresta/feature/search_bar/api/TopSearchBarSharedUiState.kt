package ru.maksonic.beresta.feature.search_bar.api

import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 30.04.2023
 */
enum class SearchBarState {
    Collapsed, Expanded, Selected
}

val SearchBarState.isCollapsed: Boolean
    get() = this == SearchBarState.Collapsed

val SearchBarState.isExpanded: Boolean
    get() = this == SearchBarState.Expanded

val SearchBarState.isSelection: Boolean
    get() = this == SearchBarState.Selected


data class TopSearchBarSharedUiState(val state: SearchBarState) {
    companion object {
        private val DefaultState = TopSearchBarSharedUiState(SearchBarState.Collapsed)

        val Initial = object : SharedUiState<TopSearchBarSharedUiState>(DefaultState) {}
    }
}

fun SharedUiState<TopSearchBarSharedUiState>.setBarState(state: SearchBarState) =
    this.update { it.copy(state = state) }
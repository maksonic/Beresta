package ru.maksonic.beresta.feature.search_bar.api

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

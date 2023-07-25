package ru.maksonic.beresta.feature.notes.api.ui

import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 03.07.2023
 */
data class SharedNotesUiScrollState(val isScrollUp: Boolean) {
    companion object {
        private val Default = SharedNotesUiScrollState(isScrollUp = true)
        val Initial = object : SharedUiState<SharedNotesUiScrollState>(Default) {}
    }
}

fun SharedUiState<SharedNotesUiScrollState>.updateScroll(isScrollUp: Boolean) =
    this.update { it.copy(isScrollUp = isScrollUp) }
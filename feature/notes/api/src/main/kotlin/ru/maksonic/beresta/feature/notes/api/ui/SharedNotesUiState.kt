package ru.maksonic.beresta.feature.notes.api.ui

import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 03.07.2023
 */
data class SharedNotesUiState(
    val isScrollUp: Boolean,
    val canScrollBackward: Boolean,
) {
    companion object {
        val Default = SharedNotesUiState(
            isScrollUp = true,
            canScrollBackward = false,
        )

        val Initial = object : SharedUiState<SharedNotesUiState>(Default) {}
    }
}

fun SharedUiState<SharedNotesUiState>.updateScroll(isScrollUp: Boolean) =
    this.update { it.copy(isScrollUp = isScrollUp) }
package ru.maksonic.beresta.feature.edit_note.api

import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 26.04.2023
 */
data class EditNoteSharedExpandUiState(
    val isExpandedFab: Boolean
) {
    companion object {
        val Initial = EditNoteSharedExpandUiState(
            isExpandedFab = false
        )
    }
}

fun SharedUiState<EditNoteSharedExpandUiState>.expandFab() =
    this.update { it.copy(isExpandedFab = true) }

fun SharedUiState<EditNoteSharedExpandUiState>.collapseFab() =
    this.update { it.copy(isExpandedFab = false) }

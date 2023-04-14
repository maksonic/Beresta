package ru.maksonic.beresta.feature.edit_note.api

import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 09.04.2023
 */
data class EditNoteFabUiSharedState(
    val isExpandedFab: Boolean,
    val isEndExpand: Boolean,
    val isVisibleOnFabDraftIndicator: Boolean,
) {
    companion object {
        private val DefaultState = EditNoteFabUiSharedState(
            isExpandedFab = false,
            isEndExpand = false,
            isVisibleOnFabDraftIndicator = false,
        )
        val Initial = object : SharedUiState<EditNoteFabUiSharedState>(DefaultState) {}
    }
}

fun SharedUiState<EditNoteFabUiSharedState>.expandFab() =
    this.updateState { it.copy(isExpandedFab = true) }

fun SharedUiState<EditNoteFabUiSharedState>.collapseFab() =
    this.updateState { it.copy(isExpandedFab = false) }

fun SharedUiState<EditNoteFabUiSharedState>.showDraftFabIcon() =
    this.updateState { it.copy(isVisibleOnFabDraftIndicator = true) }

fun SharedUiState<EditNoteFabUiSharedState>.resetDraftFabIcon() =
    this.updateState { it.copy(isVisibleOnFabDraftIndicator = false) }
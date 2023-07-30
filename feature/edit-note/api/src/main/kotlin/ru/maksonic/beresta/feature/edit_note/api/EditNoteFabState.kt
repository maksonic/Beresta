package ru.maksonic.beresta.feature.edit_note.api

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

/**
 * @Author maksonic on 29.07.2023
 */
enum class EditNoteFabState {
    EXPANDED, COLLAPSED
}
val EditNoteFabState.isExpanded get() = this == EditNoteFabState.EXPANDED


@Stable
@Immutable
data class EditNoteUiFabState(
    val isVisible: Boolean,
    val state: EditNoteFabState,
) {
    companion object {
        val Initial = EditNoteUiFabState(isVisible = true, state = EditNoteFabState.COLLAPSED)
    }
}
package ru.maksonic.beresta.feature.notes_list.ui.api.card

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * @Author maksonic on 13.10.2023
 */
val LocalNoteCardState = staticCompositionLocalOf<NoteCardUiState> {
    error("No note card ui state provided")
}
val noteUiCardState: NoteCardUiState @Composable get() = LocalNoteCardState.current
val NoteCardShapeUi.isSquare get() = this == NoteCardShapeUi.SQUARED
val NoteCardElevationUi.isEnabled get() = this == NoteCardElevationUi.ENABLED
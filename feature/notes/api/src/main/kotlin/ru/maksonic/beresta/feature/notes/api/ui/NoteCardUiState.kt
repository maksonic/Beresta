package ru.maksonic.beresta.feature.notes.api.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 06.07.2023
 */
enum class NoteCardShape(val dp: Dp) {
    ROUNDED(16.dp), SQUARED(4.dp)
}

enum class NoteCardElevation(val dp: Dp) {
    ENABLED(3.dp), DISABLED(0.dp)
}

val LocalNoteCardState = staticCompositionLocalOf<NoteCardUiState> {
    error("No note card ui state provided")
}

data class NoteCardUiState(
    val shape: NoteCardShape,
    val elevation: NoteCardElevation,
    val maxTitleLines: Int,
    val maxMessageLines: Int,
) {
    companion object {
        val Initial = NoteCardUiState(
            shape = NoteCardShape.ROUNDED,
            elevation = NoteCardElevation.DISABLED,
            maxTitleLines = 1,
            maxMessageLines = 3,
        )
    }
}

val noteUiCardState: NoteCardUiState @Composable get() = LocalNoteCardState.current

fun SharedUiState<NoteCardUiState>.updateShape(shape: NoteCardShape) =
    this.update { it.copy(shape = shape) }

fun SharedUiState<NoteCardUiState>.updateElevation(elevation: NoteCardElevation) =
    this.update { it.copy(elevation = elevation) }

fun SharedUiState<NoteCardUiState>.updateMaxTitleLines(count: Int) =
    this.update { it.copy(maxTitleLines = count) }

fun SharedUiState<NoteCardUiState>.updateMaxMessageLines(count: Int) =
    this.update { it.copy(maxMessageLines = count) }

val NoteCardShape.isSquare get() = this == NoteCardShape.SQUARED
val NoteCardElevation.isEnabled get() = this == NoteCardElevation.ENABLED

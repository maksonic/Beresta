package ru.maksonic.beresta.feature.notes.list.api.ui

/**
 * @Author maksonic on 17.06.2023
 */
enum class NoteCardCornerRadius { SQUARED, ROUNDED }

data class NoteCardUiState(
    val cornerRadius: NoteCardCornerRadius
) {
    companion object {
        val Initial = NoteCardUiState(
            cornerRadius = NoteCardCornerRadius.ROUNDED
        )
    }
}

val NoteCardCornerRadius.isRounded: Boolean
    get() = this == NoteCardCornerRadius.ROUNDED
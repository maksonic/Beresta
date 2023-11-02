package ru.maksonic.beresta.feature.notes_list.ui.api.card

/**
 * @Author maksonic on 13.10.2023
 */
data class NoteCardUiState(
    val shape: NoteCardShapeUi,
    val elevation: NoteCardElevationUi,
    val maxTitleLines: Int,
    val maxMessageLines: Int,
    val isVisibleColorMarker: Boolean,
    val isVisibleWallpaper: Boolean,
) {
    companion object {
        val Initial = NoteCardUiState(
            shape = NoteCardShapeUi.ROUNDED,
            elevation = NoteCardElevationUi.DISABLED,
            maxTitleLines = 1,
            maxMessageLines = 2,
            isVisibleColorMarker = true,
            isVisibleWallpaper = true
        )
    }
}
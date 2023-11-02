package ru.maksonic.beresta.feature.notes_list.ui.api.card

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper

/**
 * @Author maksonic on 15.10.2023
 */
interface NotesCardUiApi {
    interface CardState {
        val sharedState: State<NoteCardUiState>
        fun update(state: NoteCardUiState)
        fun updateShape(shape: NoteCardShapeUi)
        fun updateElevation(elevation: NoteCardElevationUi)
        fun updateMaxTitleLines(count: Int)
        fun updateMaxMessageLines(count: Int)
        fun updateColorMarkerVisibility(isVisible: Boolean)
        fun updateWallpaperVisibility(isVisible: Boolean)
        fun resetNoteCardLinesByDefault()
    }

    @Composable
    fun Card(
        note: NoteUi,
        onNoteClicked: (Long) -> Unit,
        onNoteLongClicked: (Long) -> Unit,
        modifier: Modifier,
        cardBackground: @Composable (wallpaper: BaseWallpaper<Color>) -> Unit
    )
}
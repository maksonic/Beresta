package ru.maksonic.beresta.feature.note_wallpaper_selector.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.StateFlow

/**
 * @Author maksonic on 09.03.2023
 */
interface NoteWallpaperSelectorApi {
    @Composable
    fun BottomSheetContent(isVisibleSheet: Boolean, hideSheet: () -> Unit)

    val currentWallpaper: StateFlow<Color>
}
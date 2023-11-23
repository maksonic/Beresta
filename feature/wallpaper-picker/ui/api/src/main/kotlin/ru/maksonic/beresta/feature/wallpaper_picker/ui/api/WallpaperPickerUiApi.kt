package ru.maksonic.beresta.feature.wallpaper_picker.ui.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper

/**
 * @Author maksonic on 29.10.2023
 */
interface WallpaperPickerUiApi {
    @Composable
    fun SheetContent(
        isVisible: Boolean,
        currentWallpaper: BaseWallpaper<Color>,
        hideSheet: () -> Unit,
        updateWallpaper: (BaseWallpaper<Color>) -> Unit
    )

    interface Wallpaper {
        @Composable
        fun Widget(wallpaper: BaseWallpaper<Color>, isCardContainer: Boolean, modifier: Modifier)
    }
}
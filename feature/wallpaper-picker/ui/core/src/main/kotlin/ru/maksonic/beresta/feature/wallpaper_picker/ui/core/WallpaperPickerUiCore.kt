package ru.maksonic.beresta.feature.wallpaper_picker.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.ui.api.WallpaperPickerUiApi

/**
 * @Author maksonic on 29.10.2023
 */
class WallpaperPickerUiCore : WallpaperPickerUiApi {
    @Composable
    override fun SheetContent(
        isVisible: Boolean,
        currentWallpaper: BaseWallpaper<Color>,
        hideSheet: () -> Unit,
        updateWallpaper: (BaseWallpaper<Color>) -> Unit
    ) {
        Container(
            isVisible = isVisible,
            currentWallpaper = currentWallpaper,
            onCloseClicked = hideSheet,
            updateWallpaper = { updateWallpaper(it) }
        )
    }
}
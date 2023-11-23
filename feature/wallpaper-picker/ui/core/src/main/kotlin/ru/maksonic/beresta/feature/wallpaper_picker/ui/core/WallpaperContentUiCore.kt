package ru.maksonic.beresta.feature.wallpaper_picker.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperGradient
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperImage
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperTexture
import ru.maksonic.beresta.feature.wallpaper_picker.ui.api.WallpaperPickerUiApi
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.pages.WallpaperColorContent
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.pages.WallpaperGradientContent
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.pages.WallpaperImageContent
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.pages.WallpaperTextureContent

/**
 * @Author maksonic on 29.10.2023
 */
class WallpaperContentUiCore : WallpaperPickerUiApi.Wallpaper {
    @Composable
    override fun Widget(
        wallpaper: BaseWallpaper<Color>,
        isCardContainer: Boolean,
        modifier: Modifier
    ) {
        Content(wallpaper, isCardContainer, modifier)
    }
}

@Composable
private fun Content(wallpaper: BaseWallpaper<Color>, isMainCard: Boolean, modifier: Modifier) {
    when (wallpaper) {
        is WallpaperColor<Color> -> WallpaperColorContent(wallpaper, modifier)
        is WallpaperGradient<Color> -> WallpaperGradientContent(wallpaper, modifier)
        is WallpaperTexture<Color> -> WallpaperTextureContent(wallpaper, modifier)
        is WallpaperImage<Color> -> WallpaperImageContent(wallpaper, isMainCard, modifier)
    }
}
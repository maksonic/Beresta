package ru.maksonic.beresta.feature.wallpaper_picker.data.store

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.data.R
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperImage

/**
 * @Author maksonic on 23.09.2023
 */
class WallpaperImagesStore : BaseWallpaperStore<WallpaperImage<Color>> {
    private val _images: List<WallpaperImage<Color>> = listOf(
        WallpaperImage(id = 400001L, resId = R.drawable.wall_0001, isDark = true),
        WallpaperImage(id = 400002L, resId = R.drawable.wall_0002, isDark = true),
    )

    private val wallpaperDefault = WallpaperImage<Color>(id = 0, resId = 0, isDark = false)

    override val data: List<WallpaperImage<Color>> = _images

    override fun findByParams(params: WallpaperParams): WallpaperImage<Color> =
        _images.find { it.id == params.id } ?: wallpaperDefault
}
package ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper

import ru.maksonic.beresta.feature.wallpaper_picker.domain.BackgroundColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.TintColor

/**
 * @Author maksonic on 29.10.2023
 */
data class WallpaperTexture<T>(
    override val id: Long,
    val tintColor: TintColor<T>,
    val backgroundColor: BackgroundColor<T>,
    val resId: Int,
    override val isDark: Boolean = false,
    val isTextureStyle: Boolean = false
) : BaseWallpaper<T>()


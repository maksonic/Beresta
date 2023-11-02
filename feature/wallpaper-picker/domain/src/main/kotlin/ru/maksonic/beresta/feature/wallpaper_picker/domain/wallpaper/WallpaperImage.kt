package ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper

/**
 * @Author maksonic on 29.10.2023
 */
data class WallpaperImage<T>(
    override val id: Long,
    val resId: Int,
    override val isDark: Boolean
) : BaseWallpaper<T>()
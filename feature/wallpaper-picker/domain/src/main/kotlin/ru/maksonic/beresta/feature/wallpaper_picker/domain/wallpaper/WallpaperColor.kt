package ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper

/**
 * @Author maksonic on 29.10.2023
 */
data class WallpaperColor<T>(
    override val id: Long,
    val value: T,
    override val isDark: Boolean
) : BaseWallpaper<T>()
package ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper

/**
 * @Author maksonic on 29.10.2023
 */
data class WallpaperGradient<T>(
    override val id: Long,
    val gradientType: Type,
    val value: List<T>,
    override val isDark: Boolean
) : BaseWallpaper<T>() {
    enum class Type {
        VERTICAL, HORIZONTAL
    }
}
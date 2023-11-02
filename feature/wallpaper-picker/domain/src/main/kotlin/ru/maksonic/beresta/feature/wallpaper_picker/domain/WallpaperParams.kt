package ru.maksonic.beresta.feature.wallpaper_picker.domain

/**
 * @Author maksonic on 30.10.2023
 */
data class WallpaperParams(
    val type: WallpaperType.Value,
    val id: Long,
    val tintColorId: Long = 0,
    val backgroundColorId: Long = 0,
    val isTextureStyle: Boolean = false,
) {
    companion object {
        val Default = WallpaperParams(
            type = WallpaperType.Value.NONE,
            id = 0,
            tintColorId = 0,
            backgroundColorId = 0,
            isTextureStyle = false,
        )

        fun color(id: Long) = Default.copy(type = WallpaperType.Value.COLOR, id = id)

        fun gradient(id: Long) = Default.copy(type = WallpaperType.Value.GRADIENT, id = id)

        fun image(id: Long) = Default.copy(type = WallpaperType.Value.IMAGE, id = id)
    }
}
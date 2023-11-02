package ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper

import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperType

/**
 * @Author maksonic on 29.10.2023
 */
abstract class BaseWallpaper<T> {
    abstract val id: Long
    abstract val isDark: Boolean

    companion object {
        fun <T> empty() = object : BaseWallpaper<T>() {
            override val id: Long = 0L
            override val isDark: Boolean = false
        }
    }

    fun getType() = when (this) {
        is WallpaperColor<T> -> WallpaperType.Value.COLOR
        is WallpaperGradient<T> -> WallpaperType.Value.GRADIENT
        is WallpaperTexture<T> -> WallpaperType.Value.TEXTURE
        is WallpaperImage<T> -> WallpaperType.Value.IMAGE
        else -> WallpaperType.Value.NONE
    }

    fun getParams() = when (this) {
        is WallpaperColor<T> -> WallpaperParams.color(this.id)
        is WallpaperGradient<T> -> WallpaperParams.gradient(this.id)
        is WallpaperImage<T> -> WallpaperParams.image(this.id)
        is WallpaperTexture<T> -> {
            WallpaperParams.Default.copy(
                type = WallpaperType.Value.TEXTURE,
                id = this.id,
                tintColorId = this.tintColor.id,
                backgroundColorId = this.backgroundColor.id,
                isTextureStyle = this.isTextureStyle
            )
        }

        else -> WallpaperParams.Default
    }

    fun onClick(
        isCurrentId: Long?,
        callBack: (BaseWallpaper<T>) -> Unit,
        default: BaseWallpaper<T>?
    ) {
        if (this.id == isCurrentId) callBack(default ?: empty()) else callBack(this)
    }
}


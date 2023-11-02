package ru.maksonic.beresta.feature.wallpaper_picker.domain

import ru.maksonic.beresta.feature.wallpaper_picker.domain.helpers.TextureStyle
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperGradient
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperImage
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperTexture

/**
 * @Author maksonic on 30.10.2023
 */
interface WallpaperRepository<T> {
    fun fetchAllWallpapers(): WallpapersDataContainer<T>

    fun fetchWallpaperColors(): List<WallpaperColor<T>>

    fun fetchWallpaperGradients(): List<WallpaperGradient<T>>

    fun fetchWallpaperTextures(): List<WallpaperTexture<T>>

    fun fetchWallpaperImages(): List<WallpaperImage<T>>

    fun fetchTexturesReadyStyles(): TextureStyle<T>

    fun fetchTexturesTintColors(): List<TintColor<T>>

    fun fetchTexturesBackgroundColors(): List<BackgroundColor<T>>

    fun findWallpaper(params: WallpaperParams): BaseWallpaper<T>
}
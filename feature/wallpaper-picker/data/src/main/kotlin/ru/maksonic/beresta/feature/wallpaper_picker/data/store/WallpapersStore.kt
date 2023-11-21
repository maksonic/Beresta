package ru.maksonic.beresta.feature.wallpaper_picker.data.store

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.gradient.WallpaperGradientsStore
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture.WallpaperTexturesStore
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperType
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpapersDataContainer
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperGradient

/**
 * @Author maksonic on 30.10.2023
 */
class WallpapersStore(
    private val colorsStore: WallpaperColorsStore,
    private val gradientsStore: WallpaperGradientsStore,
    private val texturesStore: WallpaperTexturesStore,
    private val imagesStore: WallpaperImagesStore
) {
    fun fetchAllWallpapers() = WallpapersDataContainer(
        colorsStore.data, gradientsStore.data, texturesStore.data, imagesStore.data
    )

    fun fetchWallpaperColors() = colorsStore.data

    fun fetchWallpaperGradients(): List<WallpaperGradient<Color>> = gradientsStore.data

    fun fetchWallpaperTextures() = texturesStore.data

    fun fetchWallpaperImages() = imagesStore.data

    fun fetchTexturesReadyStyles() = texturesStore.styles

    fun fetchTexturesTintColors() = texturesStore.tintColors

    fun fetchTexturesBackgroundColors() = texturesStore.backgroundColors

    fun findWallpaper(params: WallpaperParams): BaseWallpaper<Color> = when (params.type) {
        WallpaperType.Value.COLOR -> colorsStore.findByParams(params)
        WallpaperType.Value.GRADIENT -> gradientsStore.findByParams(params)
        WallpaperType.Value.TEXTURE -> texturesStore.findByParams(params)
        WallpaperType.Value.IMAGE -> imagesStore.findByParams(params)
        else -> BaseWallpaper.empty()
    }
}
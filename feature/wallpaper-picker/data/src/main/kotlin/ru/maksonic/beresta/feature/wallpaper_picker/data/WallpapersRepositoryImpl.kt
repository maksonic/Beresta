package ru.maksonic.beresta.feature.wallpaper_picker.data

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.WallpapersStore
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperRepository

/**
 * @Author maksonic on 30.10.2023
 */
class WallpapersRepositoryImpl(
    private val store: WallpapersStore
) : WallpaperRepository<Color> {
    override fun fetchAllWallpapers() = store.fetchAllWallpapers()

    override fun fetchWallpaperColors() = store.fetchWallpaperColors()

    override fun fetchWallpaperGradients() = store.fetchWallpaperGradients()

    override fun fetchWallpaperTextures() = store.fetchWallpaperTextures()

    override fun fetchWallpaperImages() = store.fetchWallpaperImages()

    override fun fetchTexturesReadyStyles() = store.fetchTexturesReadyStyles()

    override fun fetchTexturesTintColors() = store.fetchTexturesTintColors()

    override fun fetchTexturesBackgroundColors() = store.fetchTexturesBackgroundColors()

    override fun findWallpaper(params: WallpaperParams) = store.findWallpaper(params)
}
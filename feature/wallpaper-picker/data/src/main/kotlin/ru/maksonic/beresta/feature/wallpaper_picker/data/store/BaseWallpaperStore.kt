package ru.maksonic.beresta.feature.wallpaper_picker.data.store

import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams

/**
 * @Author maksonic on 30.10.2023
 */

interface BaseWallpaperStore<T> {
    /**
     *         MAP ID'S
     *   - 100000 - colors
     *   - 200000 - gradients
     *   - 300000 - textures
     *   - 400000 - images
     * */
    val data: List<T>
    fun findByParams(params: WallpaperParams): T
}
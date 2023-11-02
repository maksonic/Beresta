package ru.maksonic.beresta.feature.wallpaper_picker.data

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperRepository
import ru.maksonic.beresta.feature.wallpaper_picker.domain.usecase.FindWallpaperByParamsUseCase
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper

/**
 * @Author maksonic on 31.10.2023
 */
class FindWallpaperByParamsUseCaseImpl(
    private val repository: WallpaperRepository<Color>
) : FindWallpaperByParamsUseCase<Color> {
    override fun invoke(args: WallpaperParams): BaseWallpaper<Color> =
        repository.findWallpaper(args)
}
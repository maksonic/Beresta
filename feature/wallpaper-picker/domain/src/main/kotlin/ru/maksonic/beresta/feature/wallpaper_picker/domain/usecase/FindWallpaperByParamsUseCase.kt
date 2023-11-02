package ru.maksonic.beresta.feature.wallpaper_picker.domain.usecase

import ru.maksonic.beresta.common.domain.UseCase
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper

/**
 * @Author maksonic on 31.10.2023
 */
interface FindWallpaperByParamsUseCase<T> : UseCase.WithArgs<BaseWallpaper<T>, WallpaperParams>
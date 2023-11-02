package ru.maksonic.beresta.feature.wallpaper_picker.ui.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.wallpaper_picker.ui.api.WallpaperPickerUiApi
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.WallpaperContentUiCore
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.WallpaperPickerUiCore
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.WallpaperPickerViewModel

/**
 * @Author maksonic on 29.10.2023
 */
val wallpaperPickerUiFeatureModule = module {
    factory<WallpaperPickerUiApi> { WallpaperPickerUiCore() }
    factory<WallpaperPickerUiApi.Wallpaper> { WallpaperContentUiCore() }
    viewModel { WallpaperPickerViewModel(repository = get()) }
}
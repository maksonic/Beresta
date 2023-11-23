package ru.maksonic.beresta.feature.wallpaper_picker.data.di

import androidx.compose.ui.graphics.Color
import org.koin.dsl.module
import ru.maksonic.beresta.feature.wallpaper_picker.data.FindWallpaperByParamsUseCaseImpl
import ru.maksonic.beresta.feature.wallpaper_picker.data.WallpapersRepositoryImpl
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.WallpaperColorsStore
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.WallpaperImagesStore
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.WallpapersStore
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.gradient.WallpaperGradientsStore
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture.TextureStyleStore
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture.WallpaperTexturesStore
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture.color.TextureColorsStore
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperRepository
import ru.maksonic.beresta.feature.wallpaper_picker.domain.usecase.FindWallpaperByParamsUseCase

/**
 * @Author maksonic on 30.10.2023
 */
val wallpaperPickerDataFeatureModule = module {
    single { TextureStyleStore() }
    single {
        TextureColorsStore(
            json = get(),
            jsonConverter = get(),
            colorConverter = get()
        )
    }

    single { WallpaperColorsStore() }
    single { WallpaperGradientsStore(json = get(), jsonConverter = get(), colorConverter = get()) }
    single { WallpaperImagesStore() }
    single { WallpaperTexturesStore(textureStyleStore = get(), textureColorsStore = get()) }

    single {
        WallpapersStore(
            colorsStore = get(),
            gradientsStore = get(),
            texturesStore = get(),
            imagesStore = get()
        )
    }
    single<WallpaperRepository<Color>> { WallpapersRepositoryImpl(store = get()) }
    single<FindWallpaperByParamsUseCase<Color>> {
        FindWallpaperByParamsUseCaseImpl(repository = get())
    }
}
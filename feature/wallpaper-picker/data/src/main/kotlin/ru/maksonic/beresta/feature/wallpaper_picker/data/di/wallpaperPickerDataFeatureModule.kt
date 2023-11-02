package ru.maksonic.beresta.feature.wallpaper_picker.data.di

import androidx.compose.ui.graphics.Color
import org.koin.dsl.module
import ru.maksonic.beresta.feature.wallpaper_picker.data.FindWallpaperByParamsUseCaseImpl
import ru.maksonic.beresta.feature.wallpaper_picker.data.WallpapersRepositoryImpl
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.WallpaperColorsStore
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.WallpaperGradientsStore
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.WallpaperImagesStore
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.WallpapersStore
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture.TextureBackgroundColorsStore
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture.TextureStyleStore
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture.TextureTintColorsStore
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture.WallpaperTexturesStore
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperRepository
import ru.maksonic.beresta.feature.wallpaper_picker.domain.usecase.FindWallpaperByParamsUseCase

/**
 * @Author maksonic on 30.10.2023
 */
val wallpaperPickerDataFeatureModule = module {
    factory { TextureStyleStore() }
    factory { TextureTintColorsStore() }
    factory { TextureBackgroundColorsStore() }

    factory { WallpaperColorsStore() }
    factory { WallpaperGradientsStore() }
    factory { WallpaperImagesStore() }
    factory {
        WallpaperTexturesStore(
            textureStyleStore = get(),
            tintColorsStore = get(),
            backgroundColorsStore = get()
        )
    }

    factory {
        WallpapersStore(
            colorsStore = get(),
            gradientsStore = get(),
            texturesStore = get(),
            imagesStore = WallpaperImagesStore()
        )
    }
    factory<WallpaperRepository<Color>> { WallpapersRepositoryImpl(store = get()) }
    factory<FindWallpaperByParamsUseCase<Color>> {
        FindWallpaperByParamsUseCaseImpl(repository = get())
    }
}
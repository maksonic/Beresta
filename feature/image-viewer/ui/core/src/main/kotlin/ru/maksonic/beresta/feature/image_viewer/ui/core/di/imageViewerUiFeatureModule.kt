package ru.maksonic.beresta.feature.image_viewer.ui.core.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.image_viewer.ui.api.ImageViewerApi
import ru.maksonic.beresta.feature.image_viewer.ui.core.ImageViewerCore

/**
 * @Author maksonic on 02.12.2023
 */
val imageViewerUiFeatureModule = module {
    factory<ImageViewerApi> { ImageViewerCore() }
}
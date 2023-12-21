package ru.maksonic.beresta.feature.image_viewer.ui.core

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.image_viewer.ui.api.ImageViewerApi

/**
 * @Author maksonic on 02.12.2023
 */
class ImageViewerCore : ImageViewerApi {
    @Composable
    override fun Screen() {
        Container()
    }
}
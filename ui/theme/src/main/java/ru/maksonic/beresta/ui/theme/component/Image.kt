package ru.maksonic.beresta.ui.theme.component

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * @Author maksonic on 08.11.2022
 */
val LocalAppImage = staticCompositionLocalOf<AppImage> {
    error("No images provided")
}

data class AppImage(
    val splashBottomLogo: Int
)
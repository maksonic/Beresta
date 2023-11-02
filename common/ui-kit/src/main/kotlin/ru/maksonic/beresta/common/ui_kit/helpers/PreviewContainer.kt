package ru.maksonic.beresta.common.ui_kit.helpers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_theme.colors.background
import ru.maksonic.beresta.common.ui_theme.AppDarkMode
import ru.maksonic.beresta.common.ui_theme.BerestaTheme

/**
 * @Author maksonic on 28.09.2023
 */
@Composable
fun PreviewContainer(isDarkMode: Boolean = false, content: @Composable () -> Unit) {
    BerestaTheme(darkMode = AppDarkMode(isDarkMode)) {
        Box(Modifier.background(background)) {
            content()
        }
    }
}
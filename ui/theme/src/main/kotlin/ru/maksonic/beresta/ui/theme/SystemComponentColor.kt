package ru.maksonic.beresta.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * @Author maksonic on 08.11.2022
 */
@Composable
fun SystemComponentColor(theme: AppTheme, isDarkTheme: Boolean) {
    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController, useDarkIcons(theme, isDarkTheme)) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent, darkIcons = !useDarkIcons(theme, isDarkTheme)
        )

        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = !useDarkIcons(theme, isDarkTheme),
            navigationBarContrastEnforced = false
        )

        onDispose { }
    }
}

private fun useDarkIcons(theme: AppTheme, isSystemInDark: Boolean): Boolean {
    return when (theme) {
        AppTheme.SYSTEM -> isSystemInDark
        AppTheme.LIGHT -> false
        else -> true
    }
}
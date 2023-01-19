package ru.maksonic.beresta.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController

/**
 * @Author maksonic on 08.11.2022
 */
@Composable
fun SystemComponentColor(
    systemUiController: SystemUiController,
    useDarkIcons: Boolean,
) {
    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = !useDarkIcons
        )

        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = !useDarkIcons,
            navigationBarContrastEnforced = false
        )

        onDispose { }
    }
}
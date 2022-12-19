package ru.maksonic.beresta.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import com.google.accompanist.systemuicontroller.SystemUiController
import ru.maksonic.beresta.ui.theme.color.background

/**
 * @Author maksonic on 08.11.2022
 */
@Composable
fun SystemComponentColor(
    systemUiController: SystemUiController,
    systemStatusBarColor: Color = background,
    systemNavigationBar: Color = background,
) {
    val minLuminanceForDarkIcons = .5f

    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemStatusBarColor,
            darkIcons = systemStatusBarColor.luminance() > minLuminanceForDarkIcons
        )

        systemUiController.setNavigationBarColor(
            color = systemNavigationBar,
            darkIcons = systemNavigationBar.luminance() > minLuminanceForDarkIcons,
            navigationBarContrastEnforced = false
        )
    }
}
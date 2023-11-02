package ru.maksonic.beresta.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.common.ui_theme.BerestaTheme
import ru.maksonic.beresta.navigation.router.routes.SplashRouter

/**
 * @Author maksonic on 10.02.2023
 */
@Composable
fun SplashScreen(router: SplashRouter) {
    Container(router = router)
}


@Preview("Light theme")
@Composable
private fun Preview() {
    BerestaTheme {
        Content()
    }
}
package ru.maksonic.beresta.feature.splash_screen.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.navigation.router.router.SplashScreenRouter

/**
 * @Author maksonic on 10.02.2023
 */
interface SplashApi {
    @Composable
    fun Screen(router: SplashScreenRouter)

    @Composable
    fun Widget(modifier: Modifier)
}
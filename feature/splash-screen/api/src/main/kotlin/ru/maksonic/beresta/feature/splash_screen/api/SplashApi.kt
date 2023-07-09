package ru.maksonic.beresta.feature.splash_screen.api

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.navigation.router.router.SplashScreenRouter

/**
 * @Author maksonic on 10.02.2023
 */
interface SplashApi {
   interface Ui {
       @Composable
       fun Screen(router: SplashScreenRouter)
   }
}
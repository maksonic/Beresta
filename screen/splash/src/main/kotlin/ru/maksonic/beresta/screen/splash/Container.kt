package ru.maksonic.beresta.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.navigation.router.routes.SplashRouter

/**
 * @Author maksonic on 27.09.2023
 */
@Composable
internal fun Container(router: SplashRouter, viewModel: SplashViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isRunNavigationAction) {
        if (state.isRunNavigationAction) {
            delay(17)
            router.toOnboardingOrMain(state.route)
        }
    }

    Content()
}
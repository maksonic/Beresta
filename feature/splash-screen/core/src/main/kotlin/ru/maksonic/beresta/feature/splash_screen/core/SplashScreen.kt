package ru.maksonic.beresta.feature.splash_screen.core

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.splash_screen.api.SplashUiApi
import ru.maksonic.beresta.navigation.router.router.SplashScreenRouter
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background

/**
 * @Author maksonic on 10.02.2023
 */
class SplashScreen : SplashUiApi {

    @Composable
    override fun Screen(router: SplashScreenRouter) {
        SplashContainer(router = router)
    }
}

@Composable
private fun SplashContainer(
    router: SplashScreenRouter,
    viewModel: SplashViewModel = koinViewModel()
) {
    val state = viewModel.uiState.state.collectAsStateWithLifecycle().value
    val lightModeColor = Color(0xFFFFFFFF)
    val darkModeColor = Color(0xFF212121)
    val splashBackgroundColor = if (state.isDarkTheme) darkModeColor else lightModeColor

    SideEffect {
        if (state.isRunNavigationAction) router.toOnboardingOrMain(state.route)
    }

    SplashContent(splashBackgroundColor = splashBackgroundColor)
}


@Composable
private fun SplashContent(modifier: Modifier = Modifier, splashBackgroundColor: Color) {
    Box(
        modifier
            .fillMaxSize()
            .background(splashBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Theme.image.splashCenterLogo),
            contentDescription = "",
            modifier = modifier.size(300.dp),
        )

        Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Image(
                painter = painterResource(Theme.image.splashBottomLogo),
                contentDescription = "",
                modifier = modifier
                    .padding(bottom = 60.dp)
                    .size(200.dp, 80.dp)
            )
        }
    }
}

@Preview("Light theme")
@Preview("Night theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    BerestaTheme {
        SplashContent(splashBackgroundColor = background)
    }
}
package ru.maksonic.beresta.feature.splash_screen.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.splash_screen.api.SplashApi
import ru.maksonic.beresta.navigation.router.router.SplashScreenRouter
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme

/**
 * @Author maksonic on 10.02.2023
 */
class SplashScreen : SplashApi.Ui {

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
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.value.isRunNavigationAction) {
        if (state.value.isRunNavigationAction) {
            delay(100)
            router.toOnboardingOrMain(state.value.route)
        }
    }

    SplashContent(state)
}


@Composable
private fun SplashContent(state: State<SplashState>, modifier: Modifier = Modifier) {
    val lightModeColor = Color(0xFFFFFFFF)
    val darkModeColor = Color(0xFF212121)
    val splashBackgroundColor = rememberUpdatedState(
        if (state.value.isDarkMode) darkModeColor else lightModeColor
    )

    Box(
        modifier
            .fillMaxSize()
            .drawBehind { drawRect(splashBackgroundColor.value) },
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
        SplashContent(state = remember { mutableStateOf(SplashState.Initial) })
    }
}
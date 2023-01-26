package ru.maksonic.beresta.feature.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.navigation.router.router.SplashScreenRouter
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSurface

/**
 * @Author maksonic on 15.12.2022
 */
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = koinViewModel(),
    router: SplashScreenRouter
) {
    val state = viewModel.destination.collectAsState().value

    SideEffect { if (state.isNavigate) router.toOnboardingOrMain(state.route) }

    /**
     * @see [size] and [padding] compare with [ru.maksonic.beresta.feature.splash_screen.R.drawable.splash_bg]
     */
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier.weight(1f))
        Image(
            painter = painterResource(R.drawable.logo_beresta_foreground),
            contentDescription = "",
            modifier = modifier
                .padding(top = 60.dp)
                .size(150.dp),
            colorFilter = ColorFilter.tint(color = onSurface)
        )
        Spacer(modifier.weight(1f))
        Image(
            painter = painterResource(Theme.image.splashBottomLogo),
            contentDescription = "",
            modifier = modifier
                .padding(bottom = 60.dp)
                .size(125.dp, 45.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    BerestaTheme {
        SplashScreen(router = SplashScreenRouter { })
    }
}
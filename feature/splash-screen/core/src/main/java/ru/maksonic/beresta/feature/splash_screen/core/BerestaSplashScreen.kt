package ru.maksonic.beresta.feature.splash_screen.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.splash_screen.api.SplashApi
import ru.maksonic.beresta.navigation.router.router.SplashScreenRouter
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.widget.functional.animation.pulsating

/**
 * @Author maksonic on 10.02.2023
 */
private const val PULSE_FRACTION = 1.3f

class BerestaSplashScreen : SplashApi {

    @Composable
    override fun Screen(router: SplashScreenRouter) {
        val viewModel: SplashViewModel = koinViewModel()
        val state = viewModel.destination.collectAsState().value

        SideEffect { if (state.isNavigate) router.toOnboardingOrMain(state.route) }

        Widget(Modifier)
    }

    @Composable
    override fun Widget(modifier: Modifier) {
        SplashContent(modifier)
    }
}

@Composable
private fun SplashContent(modifier: Modifier) {
    /**
     * @see [size] and [padding] compare with
     * [ru.maksonic.beresta.feature.splash_screen.core.R.drawable.splash_bg]
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
                .size(150.dp)
                .pulsating(PULSE_FRACTION),
            colorFilter = ColorFilter.tint(color = onBackground)
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
        BerestaSplashScreen().Widget(Modifier)
    }
}
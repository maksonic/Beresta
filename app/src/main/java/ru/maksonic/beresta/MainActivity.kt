package ru.maksonic.beresta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.beresta.feature.onboarding.ui.OnboardingScreen
import ru.maksonic.beresta.feature.splash_screen.SplashScreen
import ru.maksonic.beresta.navigation.graph_builder.GraphBuilder
import ru.maksonic.beresta.navigation.router.AppNavigator
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.screen.main.ui.MainScreen
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.SystemComponentColor
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()
    private val navigator: AppNavigator by inject()
    private val graphBuilder: GraphBuilder by inject()


    @OptIn(ExperimentalAnimationApi::class, ExperimentalLifecycleComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(ru.maksonic.beresta.ui.theme.R.style.Theme_Beresta_Default)
        super.onCreate(savedInstanceState)

        setContent {
            val appState = viewModel.state.collectAsStateWithLifecycle(lifecycle).value
            val systemUiController = rememberSystemUiController()
            navigator.navController = rememberAnimatedNavController()
            val theme: @Composable (
                content: @Composable () -> Unit
            ) -> Unit = when (appState.theme) {
                AppTheme.SYSTEM -> { content -> AppTheme(isSystemInDarkTheme(), content = content) }
                AppTheme.DARK -> { content -> AppTheme(darkTheme = true, content) }
                AppTheme.LIGHT -> { content -> AppTheme(darkTheme = false, content = content) }
                AppTheme.HIGH_CONTRAST -> { content -> AppTheme(content = content) }
            }
            theme.invoke {
                SystemComponentColor(systemUiController = systemUiController)

                Scaffold(backgroundColor = background) { padding ->
                    AnimatedNavHost(
                        navController = navigator.navController,
                        startDestination = Destination.route,
                        modifier = Modifier
                            .padding(paddingValues = padding)
                            .systemBarsPadding()
                            .fillMaxSize()
                    ) {
                        graphBuilder.buildGraph(
                            graphBuilder = this,
                            systemUiController,
                            startDestination = appState.startScreen.route
                        )
                    }
                }
            }
        }
    }

    //On some Chinese devices, when launching app or switching the theme, a blank screen appears.
    private fun fixChinesVendorEmptyScreen() {
        lifecycleScope.launch {
            delay(50)
            window.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }
}
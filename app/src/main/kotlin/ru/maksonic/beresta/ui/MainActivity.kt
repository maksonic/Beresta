package ru.maksonic.beresta.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.beresta.core.MainActivitySandbox
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.language_engine.shell.provider.BerestaLanguage
import ru.maksonic.beresta.navigation.graph_builder.GraphBuilder
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.HighContrastTheme
import ru.maksonic.beresta.ui.theme.SystemComponentColor
import ru.maksonic.beresta.ui.theme.color.PaletteStore
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.SurfacePro

class MainActivity : ComponentActivity() {

    private companion object {
        private const val THEME_CHANGE_INITIAL_ALPHA = 0f

        //If the value is less than .9f, then friezes are visible when changing the theme
        private const val THEME_CHANGE_TARGET_ALPHA = .9f
    }

    private val sandbox: MainActivitySandbox by viewModel()
    private val navigator: AppNavigator by inject()
    private val graphBuilder: GraphBuilder by inject()
    private val darkModeChecker: ThemePickerApi.DarkModeChecker by inject()
    private val splashVisibility = MutableStateFlow(true)

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        updateSplashState()
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            navigator.navController = rememberAnimatedNavController()
            val model = sandbox.model.collectAsStateWithLifecycle(lifecycle).value
            val isDarkTheme = isSystemInDarkTheme()
            darkModeChecker.checkSystemDarkTheme(isDarkTheme)

            AnimatedContent(
                modifier = Modifier.fillMaxSize(),
                targetState = model.theme,
                transitionSpec = {
                    fadeIn(
                        initialAlpha = THEME_CHANGE_INITIAL_ALPHA,
                        animationSpec = tween(300, 0)
                    ) with fadeOut(
                        targetAlpha = THEME_CHANGE_TARGET_ALPHA,
                        animationSpec = tween(300, 0)
                    )
                },
                label = "MainActivityContentAnimation",
            ) { animatedTheme ->
                initTheme(
                    theme = animatedTheme,
                    isDark = isDarkTheme,
                    language = model.languageProvider,
                    palette = model.themePalette
                ).invoke {

                    SystemComponentColor(theme = model.theme, isDarkTheme = isDarkTheme)

                    SurfacePro(color = background) {

                        AnimatedNavHost(
                            navController = navigator.navController,
                            startDestination = Destination.route,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            graphBuilder.buildGraph(graphBuilder = this)
                        }
                    }
                }
            }
        }
    }

    private fun initTheme(
        theme: AppTheme,
        isDark: Boolean,
        language: BerestaLanguage,
        palette: PaletteStore,
    ): @Composable (content: @Composable () -> Unit) -> Unit = when (theme) {
        AppTheme.SYSTEM -> { content ->
            AppTheme(
                darkTheme = isDark,
                provideLanguages = language,
                palette = palette,
                content = content
            )
        }

        AppTheme.LIGHT -> { content ->
            AppTheme(
                darkTheme = false,
                provideLanguages = language,
                palette = palette,
                content = content
            )
        }

        AppTheme.DARK -> { content ->
            AppTheme(
                darkTheme = true,
                provideLanguages = language,
                palette = palette,
                content = content
            )
        }

        AppTheme.HIGH_CONTRAST -> { content ->
            HighContrastTheme(
                darkTheme = true,
                provideLanguages = language,
                palette = palette.dark,
                content = content
            )
        }
    }

    private fun updateSplashState() {
        lifecycleScope.launch {
            splashVisibility.update { true }
            delay(50)
            splashVisibility.update { false }
        }
    }
}

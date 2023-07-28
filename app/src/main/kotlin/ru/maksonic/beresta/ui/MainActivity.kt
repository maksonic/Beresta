package ru.maksonic.beresta.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
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
import ru.maksonic.beresta.core.Msg
import ru.maksonic.beresta.core.system.VibrationPerformer
import ru.maksonic.beresta.core.secure.ScreenCaptureManager
import ru.maksonic.beresta.language_engine.shell.provider.BerestaLanguage
import ru.maksonic.beresta.navigation.graph_builder.GraphBuilder
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.HighContrastTheme
import ru.maksonic.beresta.ui.theme.SystemComponentColor
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.PaletteStore
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.component.AppAnimationVelocity
import ru.maksonic.beresta.ui.theme.component.AppDarkMode
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

class MainActivity : ComponentActivity() {

    private companion object {
        private const val THEME_CHANGE_INITIAL_ALPHA = 0f

        //If the value is less than .9f, then friezes are visible when changing the theme
        private const val THEME_CHANGE_TARGET_ALPHA = .9f
        private const val THEME_CHANGE_ANIM_DURATION = 300
    }

    private val sandbox: MainActivitySandbox by viewModel()
    private val navigator: AbstractNavigator by inject()
    private val graphBuilder: GraphBuilder by inject()
    private val splashVisibility = MutableStateFlow(true)
    private val screenCaptureManager: ScreenCaptureManager by inject()
    private val vibrationPerformer: VibrationPerformer by inject()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        screenCaptureManager.initMutablePermission(window, lifecycleScope)
        updateSplashState()
        installSplashScreen()
        super.onCreate(savedInstanceState)

        fixChinesVendorEmptyScreen()
        setContent {
            navigator.navController = rememberAnimatedNavController()
            val model = sandbox.model.collectAsStateWithLifecycle(lifecycle)
            val isDarkTheme = isSystemInDarkTheme()

            LaunchedEffect(isDarkTheme) {
                sandbox.send(Msg.Inner.UpdatedThemeDarkModeValue(isDarkTheme))
            }

            AnimatedContent(
                modifier = Modifier.fillMaxSize(),
                targetState = model.value.currentTheme,
                transitionSpec = {
                    fadeIn(
                        initialAlpha = THEME_CHANGE_INITIAL_ALPHA,
                        animationSpec = tween(THEME_CHANGE_ANIM_DURATION, 0)
                    ) togetherWith fadeOut(
                        targetAlpha = THEME_CHANGE_TARGET_ALPHA,
                        animationSpec = tween(THEME_CHANGE_ANIM_DURATION, 0)
                    )
                },
                label = "MainActivityContentAnimation",
            ) { animatedTheme ->
                initTheme(
                    theme = animatedTheme,
                    darkMode = model.value.darkMode,
                    language = model.value.languageProvider,
                    palette = model.value.themePalette,
                    animVelocity = model.value.animationVelocity
                ).invoke {

                    SystemComponentColor(
                        theme = animatedTheme,
                        isDarkTheme = model.value.darkMode.value
                    )

                    SurfacePro(color = surface) {
                        val animationVelocity =
                            rememberUpdatedState(Theme.animVelocity.navigationVelocity)
                        AnimatedNavHost(
                            navController = navigator.navController,
                            startDestination = Destination.route,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            graphBuilder.buildGraph(graphBuilder = this, animationVelocity.value)
                        }
                    }
                }
            }
        }
    }

    private fun initTheme(
        theme: AppTheme,
        darkMode: AppDarkMode,
        language: BerestaLanguage,
        palette: PaletteStore,
        animVelocity: AppAnimationVelocity.Key
    ): @Composable (content: @Composable () -> Unit) -> Unit = when (theme) {
        AppTheme.SYSTEM -> { content ->
            AppTheme(
                darkMode = darkMode,
                provideLanguages = language,
                palette = palette,
                animationVelocity = animVelocity,
                content = content
            )
        }

        AppTheme.LIGHT -> { content ->
            AppTheme(
                darkMode = AppDarkMode.Disabled,
                provideLanguages = language,
                palette = palette,
                animationVelocity = animVelocity,
                content = content
            )
        }

        AppTheme.DARK -> { content ->
            AppTheme(
                darkMode = AppDarkMode.Enabled,
                provideLanguages = language,
                palette = palette,
                animationVelocity = animVelocity,
                content = content
            )
        }

        AppTheme.HIGH_CONTRAST -> { content ->
            HighContrastTheme(
                darkMode = AppDarkMode.Enabled,
                provideLanguages = language,
                palette = palette.highContrast,
                animationVelocity = animVelocity,
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

    //On some Chinese devices, when launching app or switching the theme, a blank screen appears.
    private fun fixChinesVendorEmptyScreen() {
        lifecycleScope.launch {
            delay(50)
            window.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }
}

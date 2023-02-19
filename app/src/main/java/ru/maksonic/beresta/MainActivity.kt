package ru.maksonic.beresta

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
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.beresta.core.MainActivitySandbox
import ru.maksonic.beresta.feature.language_selector.api.provider.BerestaLanguage
import ru.maksonic.beresta.feature.language_selector.api.provider.LanguageProvider
import ru.maksonic.beresta.feature.language_selector.api.provider.LocalBerestaLanguage
import ru.maksonic.beresta.navigation.graph_builder.GraphBuilder
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.SystemComponentColor
import ru.maksonic.beresta.ui.theme.color.background

class MainActivity : ComponentActivity() {

    private companion object {
        private const val THEME_CHANGE_INITIAL_ALPHA = 0f

        //If the value is less than .9f, then friezes are visible when changing the theme
        private const val THEME_CHANGE_TARGET_ALPHA = .9f
    }

    private val sandbox: MainActivitySandbox by viewModel()
    private val navigator: AppNavigator by inject()
    private val graphBuilder: GraphBuilder by inject()
    private val languageProvider: LanguageProvider by inject()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(ru.maksonic.beresta.ui.theme.R.style.Theme_Beresta_Default)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        fixChinesVendorEmptyScreen()

        setContent {
            navigator.navController = rememberAnimatedNavController()

            val model = sandbox.model.collectAsStateWithLifecycle(lifecycle).value
            val isDarkTheme = isSystemInDarkTheme()

            AnimatedContent(
                modifier = Modifier
                    .fillMaxSize(),
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
            ) { animatedTheme ->
                initTheme(animatedTheme, isDarkTheme).invoke {
                    ProvideAppLanguage(model.languageProvider) {
                        SystemComponentColor(theme = model.theme, isDarkTheme = isDarkTheme)
                        Scaffold(backgroundColor = background) { paddings ->
                            AnimatedNavHost(
                                navController = navigator.navController,
                                startDestination = Destination.route,
                                modifier = Modifier
                                    .padding(paddingValues = paddings)
                                    .fillMaxSize()
                            ) {
                                graphBuilder.buildGraph(graphBuilder = this)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initTheme(
        theme: AppTheme, isDark: Boolean
    ): @Composable (content: @Composable () -> Unit) -> Unit = when (theme) {
        AppTheme.SYSTEM -> { content -> AppTheme(darkTheme = isDark, content = content) }
        AppTheme.LIGHT -> { content -> AppTheme(darkTheme = false, content = content) }
        else -> { content -> AppTheme(darkTheme = true, content) }
    }

    @Composable
    private fun ProvideAppLanguage(
        provided: BerestaLanguage,
        content: @Composable () -> Unit
    ) {
        CompositionLocalProvider(LocalBerestaLanguage provides provided) {
            content()
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
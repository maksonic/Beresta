package ru.maksonic.beresta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.beresta.feature.language_selector.api.AppLanguage
import ru.maksonic.beresta.feature.language_selector.api.LanguageProvider
import ru.maksonic.beresta.feature.language_selector.api.LocalBerestaLanguage
import ru.maksonic.beresta.feature.language_selector.api.provideLanguage
import ru.maksonic.beresta.navigation.graph_builder.GraphBuilder
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.SystemComponentColor
import ru.maksonic.beresta.ui.theme.color.background

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()
    private val navigator: AppNavigator by inject()
    private val graphBuilder: GraphBuilder by inject()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(ru.maksonic.beresta.ui.theme.R.style.Theme_Beresta_Default)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        fixChinesVendorEmptyScreen()

        setContent {
            navigator.navController = rememberAnimatedNavController()

            val model = viewModel.state.collectAsStateWithLifecycle(lifecycle).value
            val systemUiController = rememberSystemUiController()
            val isDarkTheme = isSystemInDarkTheme()

            initTheme(model.theme, isDarkTheme).invoke {
                ProvideAppLanguage(model.language) {
                    SystemComponentColor(systemUiController, isDarkTheme)
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

    private fun initTheme(
        theme: AppTheme, isDark: Boolean
    ): @Composable (content: @Composable () -> Unit) -> Unit = when (theme) {
        AppTheme.SYSTEM -> { content -> AppTheme(darkTheme = isDark, content = content) }
        AppTheme.DARK -> { content -> AppTheme(darkTheme = true, content) }
        AppTheme.LIGHT -> { content -> AppTheme(darkTheme = false, content = content) }
        AppTheme.HIGH_CONTRAST -> { content -> AppTheme(darkTheme = true, content = content) }
    }

    @Composable
    fun ProvideAppLanguage(language: AppLanguage, content: @Composable () -> Unit) {
        CompositionLocalProvider(LocalBerestaLanguage provides provideLanguage(language)) {
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
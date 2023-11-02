package ru.maksonic.beresta.ui

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.beresta.common.core.ScreenCaptureManager
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemStatusBarHeight
import ru.maksonic.beresta.common.ui_theme.AppThemeUi
import ru.maksonic.beresta.common.ui_theme.BaseTheme
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.background
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.core.MainActivitySandbox
import ru.maksonic.beresta.core.Msg
import ru.maksonic.beresta.navigation.graph_builder.GraphBuilder
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination

class MainActivity : FragmentActivity() {
    private val sandbox: MainActivitySandbox by viewModel()
    private val navigator: AbstractNavigator by inject()
    private val graphBuilder: GraphBuilder by inject()
    private val screenCaptureManager: ScreenCaptureManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        screenCaptureManager.init(window, lifecycleScope)
        super.onCreate(savedInstanceState)

        setContent {
            navigator.init(rememberNavController())

            val model by sandbox.model.collectAsStateWithLifecycle(lifecycle)
            val isSystemInDarkTheme = isSystemInDarkTheme()
            val isDarkMode = when (model.currentTheme) {
                AppThemeUi.SYSTEM -> isSystemInDarkTheme
                AppThemeUi.DAY -> false
                AppThemeUi.NIGHT -> true
                AppThemeUi.DARK -> true
            }

            LaunchedEffect(isSystemInDarkTheme) {
                sandbox.send(Msg.Inner.UpdatedThemeDarkModeValue(isDarkMode))
            }

            LaunchedEffect(model.currentTheme) {
                sandbox.send(Msg.Inner.UpdatedThemeDarkModeValue(isDarkMode))
            }

            BaseTheme(
                theme = model.currentTheme,
                darkMode = model.darkMode,
                palette = model.paletteContainer,
                languages = model.languageProvider,
                animationVelocity = model.animationVelocity
            ) {
                LaunchedEffect(model.darkMode.value) {
                    val navigationBarStyle = with(Color.TRANSPARENT) {
                        if (model.darkMode.value) SystemBarStyle.dark(this)
                        else SystemBarStyle.light(this, this)
                    }

                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.auto(
                            lightScrim = Color.TRANSPARENT,
                            darkScrim = Color.TRANSPARENT,
                            detectDarkMode = { model.darkMode.value }
                        ),
                        navigationBarStyle = navigationBarStyle
                    )
                }

                Scaffold(containerColor = background) { paddings ->
                    val screenOrientation = LocalConfiguration.current.orientation
                    val navigationVelocity = Theme.animVelocity.navigation
                    val velocity = Theme.animVelocity.navigation.fade
                    val landscapePadding =
                        if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE)
                            Modifier.padding(end = SystemStatusBarHeight.plus(dp4)) else Modifier

                    NavHost(
                        navController = navigator.navController,
                        startDestination = Destination.route,
                        enterTransition = { fadeIn(tween(velocity)) },
                        exitTransition = { fadeOut(tween(velocity)) },
                        modifier = landscapePadding
                    ) {
                        graphBuilder.buildGraph(graphBuilder = this, navigationVelocity)
                    }
                }
            }
        }
    }
}
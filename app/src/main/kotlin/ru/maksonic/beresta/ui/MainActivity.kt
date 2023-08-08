package ru.maksonic.beresta.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.beresta.core.MainActivitySandbox
import ru.maksonic.beresta.core.Msg
import ru.maksonic.beresta.core.secure.ScreenCaptureManager
import ru.maksonic.beresta.core.system.VibrationPerformer
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.service.CounterService
import ru.maksonic.beresta.navigation.graph_builder.GraphBuilder
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.ui.theme.BaseTheme
import ru.maksonic.beresta.ui.theme.SystemComponentColor
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

class MainActivity : ComponentActivity() {

    private val sandbox: MainActivitySandbox by viewModel()
    private val navigator: AbstractNavigator by inject()
    private val graphBuilder: GraphBuilder by inject()
    private val splashVisibility = MutableStateFlow(true)
    private val screenCaptureManager: ScreenCaptureManager by inject()
    private val vibrationPerformer: VibrationPerformer by inject()
    private val pinFailCounter: HiddenNotesApi.Feature.PinFailCounter by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        screenCaptureManager.initPermission(window, lifecycleScope)
        updateSplashState()
        installSplashScreen()
        super.onCreate(savedInstanceState)
        fixChinesVendorEmptyScreen()

       /* lifecycleScope.launch {
            checkCounter(this@MainActivity)
        }*/

        setContent {
            navigator.init(rememberNavController())

            val model = sandbox.model.collectAsStateWithLifecycle(lifecycle)
            val isDarkTheme = isSystemInDarkTheme()

            LaunchedEffect(isDarkTheme) {
                sandbox.send(Msg.Inner.UpdatedThemeDarkModeValue(isDarkTheme))
            }

            BaseTheme(
                theme = model.value.currentTheme,
                darkMode = model.value.darkMode,
                provideLanguages = model.value.languageProvider,
                palette = model.value.themePalette,
                animationVelocity = model.value.animationVelocity
            ) {

                SystemComponentColor(model.value.currentTheme, model.value.darkMode.value)

                SurfacePro(color = surface) {
                    val velocity = rememberUpdatedState(Theme.animVelocity.navigationVelocity)

                    NavHost(
                        navController = navigator.navController,
                        startDestination = Destination.route,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        graphBuilder.buildGraph(graphBuilder = this, velocity.value)
                    }
                }
            }
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

    private suspend fun checkCounter(context: Context) {
        Log.e("AAA", "ACTIVITY SETVICE IS - ${CounterService.isRunning}")
        pinFailCounter.state.collect { info ->
            Log.e("AAA", "ACTIVITY data - ${info}")
            if (!CounterService.isRunning && info.timestamp != null && info.failCount >= 1) {
                context.startService(Intent(context, CounterService::class.java))
            }
        }

    /*pinFailCounter.state.collect { info ->
            if (info.tick > 0 && !CounterService.isRunning) {
                context.startService(Intent(context, CounterService::class.java))
            }
        }*/
    }
}

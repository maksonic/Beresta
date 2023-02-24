package ru.maksonic.beresta.navigation.graph_builder

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.navigation
import ru.maksonic.beresta.navigation.graph_builder.destination.*
import ru.maksonic.beresta.navigation.graph_builder.destination.mainScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.onboardingScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.settingsScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.splashScreen
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator
import ru.maksonic.beresta.navigation.router.Destination

/**
 * @Author maksonic on 15.12.2022
 */
interface GraphBuilder {
    fun buildGraph(graphBuilder: NavGraphBuilder)

    class Core(
        private val navigator: AppNavigator, private val apiStore: FeatureApiStore
    ) : GraphBuilder {

        private companion object {
            private const val DEF_ANIM_SPEED = 300
        }

        @OptIn(ExperimentalAnimationApi::class)
        override fun buildGraph(graphBuilder: NavGraphBuilder) {
            graphBuilder.navigation(
                route = Destination.route,
                startDestination = Destination.Splash.route,
                enterTransition = { fadeIn(animationSpec = tween(DEF_ANIM_SPEED)) },
                exitTransition = { fadeOut(animationSpec = tween(DEF_ANIM_SPEED)) }
            ) {
                with(navigator) {
                    splashScreen(apiStore.splash, this)
                    onboardingScreen(apiStore.onboarding, this)
                    mainScreen(this)
                    settingsScreen(this, DEF_ANIM_SPEED)
                    trashListScreen(this, DEF_ANIM_SPEED)
                    /*

                     settingsScreen(this, DEF_ANIM_SPEED)
                     trashListScreen(this, DEF_ANIM_SPEED)
                     editNoteScreen(this)*/
                }
            }
        }
    }
}

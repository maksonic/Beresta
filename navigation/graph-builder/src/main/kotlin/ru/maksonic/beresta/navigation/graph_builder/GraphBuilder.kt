package ru.maksonic.beresta.navigation.graph_builder

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.navigation
import ru.maksonic.beresta.navigation.graph_builder.destination.editNoteScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.foldersScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.hiddenNotesScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.mainScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.onboardingScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.settings.settingsAppearanceScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.settings.settingsScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.settings.settingsSecurityScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.splashScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.trash.trashFoldersScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.trash.trashNotesScreen
import ru.maksonic.beresta.navigation.graph_builder.navigator.NavigationAnimator
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.ui.theme.component.NavigationVelocity

/**
 * @Author maksonic on 15.12.2022
 */
interface GraphBuilder {
    fun buildGraph(graphBuilder: NavGraphBuilder, navigationVelocity: NavigationVelocity)

    class Core(
        private val navigator: AbstractNavigator,
        private val apiStore: FeatureApiStore,
        private val animator: NavigationAnimator
    ) : GraphBuilder {
        @OptIn(ExperimentalAnimationApi::class)
        override fun buildGraph(
            graphBuilder: NavGraphBuilder,
            navigationVelocity: NavigationVelocity
        ) {
            val fade = navigationVelocity.fade
            val slide = navigationVelocity.slide

            graphBuilder.navigation(
                route = Destination.route,
                startDestination = Destination.Splash.route,
                enterTransition = { fadeIn(animationSpec = tween(fade)) },
                exitTransition = { fadeOut(animationSpec = tween(fade)) },
            ) {
                with(navigator) {
                    splashScreen(apiStore.splash, this)
                    onboardingScreen(apiStore.onboarding, this)
                    mainScreen(this)
                    settingsScreen(this, slide, animator)
                    settingsAppearanceScreen(this, slide)
                    settingsSecurityScreen(this, slide)
                    editNoteScreen(apiStore.editNote, this)
                    foldersScreen(this, slide, animator)
                    trashNotesScreen(this, slide, animator)
                    trashFoldersScreen(this, slide, animator)
                    hiddenNotesScreen(this, slide, animator)
                }
            }
        }
    }
}

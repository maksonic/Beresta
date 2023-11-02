package ru.maksonic.beresta.navigation.graph_builder

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import ru.maksonic.beresta.common.ui_theme.NavigationVelocity
import ru.maksonic.beresta.navigation.graph_builder.destination.editNoteScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.foldersScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.hiddenNotesScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.mainDestination
import ru.maksonic.beresta.navigation.graph_builder.destination.onboardingDestination
import ru.maksonic.beresta.navigation.graph_builder.destination.settings.settingsAppearanceScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.settings.settingsNotificationsScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.settings.settingsScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.settings.settingsSecurityScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.splashDestination
import ru.maksonic.beresta.navigation.graph_builder.destination.trash.trashFoldersScreen
import ru.maksonic.beresta.navigation.graph_builder.destination.trash.trashNotesScreen
import ru.maksonic.beresta.navigation.graph_builder.navigator.NavigationAnimator
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination

/**
 * @Author maksonic on 15.12.2022
 */
interface GraphBuilder {
    fun buildGraph(graphBuilder: NavGraphBuilder, velocity: NavigationVelocity)

    class Core(
        private val animator: NavigationAnimator,
        private val apiContainer: FeatureApiContainer,
        private val navigator: AbstractNavigator
    ) : GraphBuilder {

        override fun buildGraph(graphBuilder: NavGraphBuilder, velocity: NavigationVelocity) {
            graphBuilder.navigation(
                route = Destination.route,
                startDestination = Destination.Splash.route
            ) {
                with(navigator) {
                    splashDestination(this)
                    onboardingDestination(apiContainer.onboarding, this)
                    mainDestination(this)
                    editNoteScreen(apiContainer.editNote, this)
                    foldersScreen(this, animator, velocity)
                    hiddenNotesScreen(this, animator, velocity)
                    settingsScreen(this, animator, velocity)
                    settingsAppearanceScreen(this)
                    settingsNotificationsScreen(this)
                    settingsSecurityScreen(this)
                    trashFoldersScreen(this, animator, velocity)
                    trashNotesScreen(this, animator, velocity)
                }
            }
        }
    }
}

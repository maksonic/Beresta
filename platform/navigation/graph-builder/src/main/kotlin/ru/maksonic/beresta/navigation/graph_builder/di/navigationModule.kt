package ru.maksonic.beresta.navigation.graph_builder.di

import org.koin.dsl.module
import ru.maksonic.beresta.navigation.graph_builder.FeatureApiContainer
import ru.maksonic.beresta.navigation.graph_builder.GraphBuilder
import ru.maksonic.beresta.navigation.graph_builder.navigator.AppNavigator
import ru.maksonic.beresta.navigation.graph_builder.navigator.NavigationAnimator
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator

/**
 * @Author maksonic on 22.04.2023
 */
val navigationModule = module {
    single<NavigationAnimator> { NavigationAnimator.Core() }
    single<GraphBuilder> {
        GraphBuilder.Core(
            animator = get(),
            apiContainer = FeatureApiContainer(
                onboarding = get(),
                editNote = get()
            ),
            navigator = get()
        )
    }
    single<AbstractNavigator> { AppNavigator() }
}
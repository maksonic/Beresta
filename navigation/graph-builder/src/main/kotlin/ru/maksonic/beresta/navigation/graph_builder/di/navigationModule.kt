package ru.maksonic.beresta.navigation.graph_builder.di

import org.koin.dsl.module
import ru.maksonic.beresta.navigation.graph_builder.FeatureApiStore
import ru.maksonic.beresta.navigation.graph_builder.GraphBuilder
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator

/**
 * @Author maksonic on 22.04.2023
 */
val navigationModule = module {
    single { FeatureApiStore(splash = get(), onboarding = get(), editNote = get(), foldersList = get()) }
    single<GraphBuilder> { GraphBuilder.Core(navigator = get(), apiStore = get()) }
    single { AppNavigator() }
}
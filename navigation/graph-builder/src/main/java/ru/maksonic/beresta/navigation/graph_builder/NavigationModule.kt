package ru.maksonic.beresta.navigation.graph_builder

import org.koin.dsl.module
import ru.maksonic.beresta.navigation.router.AppNavigator

/**
 * @Author maksonic on 15.12.2022
 */
val navigationModule = module {
    single<GraphBuilder> { GraphBuilder.Builder() }
    single { AppNavigator() }
}
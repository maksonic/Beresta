package ru.maksonic.beresta.feature.top_bar_counter.core.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.feature.top_bar_counter.core.TopBarCounterWidget

/**
 * @Author maksonic on 30.04.2023
 */
val topBarCounterUiFeatureModule = module {
    single<TopBarCounterApi.Ui> { TopBarCounterWidget() }
}
package ru.maksonic.beresta.feature.theme_selector

import org.koin.dsl.module

/**
 * @Author maksonic on 15.12.2022
 */
val themeSelectorFeatureModule = module {
    single<ThemeSelector> { ThemeSelector.Feature(datastore = get()) }
}
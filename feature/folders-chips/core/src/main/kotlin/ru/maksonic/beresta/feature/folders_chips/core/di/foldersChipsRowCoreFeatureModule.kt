package ru.maksonic.beresta.feature.folders_chips.core.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.folders_chips.api.ui.StickyItemsTitleFormatter
import ru.maksonic.beresta.feature.folders_chips.core.StickyItemsTitleFormatterCore

/**
 * @Author maksonic on 04.07.2023
 */
val foldersChipsRowCoreFeatureModule = module {
    single<StickyItemsTitleFormatter> { StickyItemsTitleFormatterCore() }
}
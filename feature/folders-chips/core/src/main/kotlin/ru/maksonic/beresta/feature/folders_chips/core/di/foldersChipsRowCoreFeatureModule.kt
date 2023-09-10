package ru.maksonic.beresta.feature.folders_chips.core.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.folders_chips.api.ui.StickyFoldersTitleFormatter
import ru.maksonic.beresta.feature.folders_chips.core.StickyFoldersTitleFormatterCore

/**
 * @Author maksonic on 04.07.2023
 */
val foldersChipsRowCoreFeatureModule = module {
    single<StickyFoldersTitleFormatter> { StickyFoldersTitleFormatterCore() }
}
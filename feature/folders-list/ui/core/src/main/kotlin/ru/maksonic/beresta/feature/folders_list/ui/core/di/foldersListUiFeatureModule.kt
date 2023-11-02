package ru.maksonic.beresta.feature.folders_list.ui.core.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUiMapper
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersChipsRowUiApi
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersListUiApi
import ru.maksonic.beresta.feature.folders_list.ui.core.CurrentSelectedFolderUiStoreCore
import ru.maksonic.beresta.feature.folders_list.ui.core.chips.FoldersChipsRowUiCore
import ru.maksonic.beresta.feature.folders_list.ui.core.list.FoldersListUiCore
import ru.maksonic.beresta.feature.folders_list.ui.core.mapper.FolderUiMapperImpl
import ru.maksonic.beresta.feature.folders_list.ui.core.mapper.FoldersSortUiMapperImpl
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.FoldersSortUiMapper

/**
 * @Author maksonic on 17.10.2023
 */
val foldersListUiFeatureModule = module {
    factory<FolderUiMapper> { FolderUiMapperImpl() }
    factory<FoldersSortUiMapper> { FoldersSortUiMapperImpl() }
    factory<FoldersChipsRowUiApi> { FoldersChipsRowUiCore() }
    factory<FoldersListUiApi> { FoldersListUiCore() }
    single<FoldersChipsRowUiApi.CurrentSelectedFolderStore> { CurrentSelectedFolderUiStoreCore() }
}
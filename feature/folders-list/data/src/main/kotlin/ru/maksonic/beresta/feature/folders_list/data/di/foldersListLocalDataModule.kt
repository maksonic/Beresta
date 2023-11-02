package ru.maksonic.beresta.feature.folders_list.data.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.core.CoroutineDispatchers
import ru.maksonic.beresta.feature.folders_list.data.local.FolderCacheMapper
import ru.maksonic.beresta.feature.folders_list.data.local.FoldersDataDataSource

/**
 * @Author maksonic on 13.10.2023
 */
val foldersListLocalDataModule = module {
    factory { FolderCacheMapper() }
    single {
        FoldersDataDataSource(folderDao = get(), ioDispatcher = get(named(CoroutineDispatchers.IO)))
    }
}
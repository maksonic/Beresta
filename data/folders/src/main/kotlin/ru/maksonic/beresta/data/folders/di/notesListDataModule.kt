package ru.maksonic.beresta.data.folders.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.coroutine_dispatchers.CoroutineDispatchers
import ru.maksonic.beresta.data.folders.FoldersRepositoryImpl
import ru.maksonic.beresta.data.folders.cache.FolderCacheMapper
import ru.maksonic.beresta.data.folders.cache.FoldersDataDataSource
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersInteractor
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersRepository
import ru.maksonic.beresta.feature.folders_chips.api.domain.usecase.FetchFoldersTrashListUseCase

/**
 * @Author maksonic on 24.04.2023
 */
val foldersDataModule = module {
    single { FolderCacheMapper() }
    single {
        FoldersDataDataSource(
            folderDao = get(),
            dispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    single<FoldersRepository> { FoldersRepositoryImpl(cache = get(), mapper = get()) }
    single { FoldersInteractor(repository = get()) }
    single { FetchFoldersTrashListUseCase(repository = get()) }
}
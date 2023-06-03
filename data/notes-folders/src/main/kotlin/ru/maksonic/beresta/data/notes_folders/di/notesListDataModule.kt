package ru.maksonic.beresta.data.notes_folders.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.coroutine_dispatchers.CoroutineDispatchers
import ru.maksonic.beresta.data.notes_folders.NotesFoldersRepositoryImpl
import ru.maksonic.beresta.data.notes_folders.cache.FoldersCacheDataSource
import ru.maksonic.beresta.data.notes_folders.cache.NoteFolderCacheMapper
import ru.maksonic.beresta.feature.notes.folders.api.domain.usecase.FetchFolderByIdUseCase
import ru.maksonic.beresta.feature.notes.folders.api.domain.usecase.FetchFoldersListUseCase
import ru.maksonic.beresta.feature.notes.folders.api.domain.NotesFoldersInteractor
import ru.maksonic.beresta.feature.notes.folders.api.domain.NotesFoldersRepository
import ru.maksonic.beresta.feature.notes.folders.api.domain.usecase.FetchFoldersTrashListUseCase

/**
 * @Author maksonic on 24.04.2023
 */
val notesFoldersDataModule = module {
    single { NoteFolderCacheMapper() }
    single {
        FoldersCacheDataSource(
            folderDao = get(),
            dispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    single<NotesFoldersRepository> { NotesFoldersRepositoryImpl(cache = get(), mapper = get()) }
    single<NotesFoldersInteractor> { NotesFoldersInteractor.Impl(repository = get()) }
    single { FetchFoldersListUseCase(repository = get()) }
    single { FetchFolderByIdUseCase(repository = get()) }
    single { FetchFoldersTrashListUseCase(repository = get()) }
}
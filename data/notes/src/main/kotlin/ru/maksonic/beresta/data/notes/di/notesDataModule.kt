package ru.maksonic.beresta.data.notes.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.coroutine_dispatchers.CoroutineDispatchers
import ru.maksonic.beresta.data.notes.NotesRepositoryImpl
import ru.maksonic.beresta.data.notes.cache.NoteCacheMapper
import ru.maksonic.beresta.data.notes.cache.NotesDataDataSource
import ru.maksonic.beresta.feature.notes.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.api.domain.NotesRepository
import ru.maksonic.beresta.feature.notes.api.domain.usecase.DeleteHiddenNotesUseCase
import ru.maksonic.beresta.feature.notes.api.domain.usecase.FetchHiddenNotesUseCase
import ru.maksonic.beresta.feature.notes.api.domain.usecase.FetchNotesByFolderTrashListUseCase
import ru.maksonic.beresta.feature.notes.api.domain.usecase.FetchNotesWithoutFolderTrashListUseCase

/**
 * @Author maksonic on 24.04.2023
 */
val notesDataModule = module {
    single { NoteCacheMapper() }
    single {
        NotesDataDataSource(
            noteDao = get(), dispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    single<NotesRepository> { NotesRepositoryImpl(cache = get(), mapper = get()) }
    single { NotesInteractor(repository = get()) }
    single { FetchHiddenNotesUseCase(repository = get()) }
    single { DeleteHiddenNotesUseCase(repository = get()) }
    single { FetchNotesWithoutFolderTrashListUseCase(repository = get()) }
    single { FetchNotesByFolderTrashListUseCase(repository = get()) }
}
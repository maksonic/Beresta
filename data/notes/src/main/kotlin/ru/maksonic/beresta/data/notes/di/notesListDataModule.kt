package ru.maksonic.beresta.data.notes.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.coroutine_dispatchers.CoroutineDispatchers
import ru.maksonic.beresta.data.notes.NotesRepositoryImpl
import ru.maksonic.beresta.data.notes.cache.NoteCacheMapper
import ru.maksonic.beresta.data.notes.cache.NotesCacheDataSource
import ru.maksonic.beresta.feature.notes.list.api.domain.NotesRepository
import ru.maksonic.beresta.feature.notes.list.api.domain.usecase.FetchNoteByIdUseCase
import ru.maksonic.beresta.feature.notes.list.api.domain.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes.list.api.domain.usecase.FetchRemovedNotesUseCase

/**
 * @Author maksonic on 24.04.2023
 */
val notesListDataModule = module {
    single { NoteCacheMapper() }
    single {
        NotesCacheDataSource(
            noteDao = get(), dispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    single<NotesRepository> { NotesRepositoryImpl(cache = get(), mapper = get()) }
    single { FetchNotesUseCase(repository = get()) }
    single { FetchNoteByIdUseCase(repository = get()) }
    single { FetchRemovedNotesUseCase(repository = get()) }
}
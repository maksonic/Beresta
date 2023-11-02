package ru.maksonic.beresta.feature.notes_list.data.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.core.CoroutineDispatchers
import ru.maksonic.beresta.feature.notes_list.data.list.local.NoteCacheMapper
import ru.maksonic.beresta.feature.notes_list.data.list.local.NotesDataDataSource

/**
 * @Author maksonic on 13.10.2023
 */
val notesListLocalDataModule = module {
    factory { NoteCacheMapper() }
    single {
        NotesDataDataSource(noteDao = get(), ioDispatcher = get(named(CoroutineDispatchers.IO)))
    }
}
package ru.maksonic.beresta.feature.notes.list.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.coroutine_dispatchers.CoroutineDispatchers
import ru.maksonic.beresta.feature.notes.list.api.domain.DateFormatter
import ru.maksonic.beresta.feature.notes.list.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUiMapper
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.notes.list.core.NoteCardStateDatastore
import ru.maksonic.beresta.feature.notes.list.core.NotesListSandbox
import ru.maksonic.beresta.feature.notes.list.core.program.NotesListDataProgram
import ru.maksonic.beresta.feature.notes.list.core.program.NotesListPreferencesProgram
import ru.maksonic.beresta.feature.notes.list.core.sort.core.SortNotesDatastore
import ru.maksonic.beresta.feature.notes.list.core.ui.NotesListWidget

/**
 * @Author maksonic on 24.04.2023
 */
val notesListFeatureModel = module {

    single<NotesInteractor> { NotesInteractor.Impl(repository = get()) }
    single<DateFormatter> { DateFormatter.Core() }
    single<NotesListApi.CardState> { NoteCardStateDatastore(datastore = get()) }
    single { NoteUiMapper() }
    single {
        NotesListDataProgram(
            notesInteractor = get(),
            fetchNotesUseCase = get(),
            mapper = get(),
            appLanguageEngineApi = get(),
            dateFormatter = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    single {
        NotesListPreferencesProgram(
            appLanguageEngineApi = get(),
            sortNotesDatastore = get(),
            noteCardStateDatastore = get()
        )
    }
    viewModel { NotesListSandbox(notesDataProgram = get(), notesPrefsProgram = get()) }
    single<NotesListApi.Ui> { NotesListWidget() }
    single<NotesListApi.SortedNotesState> { SortNotesDatastore(datastore = get()) }
}
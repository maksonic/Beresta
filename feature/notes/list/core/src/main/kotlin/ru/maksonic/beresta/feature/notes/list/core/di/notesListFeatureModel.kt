package ru.maksonic.beresta.feature.notes.list.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.coroutine_dispatchers.CoroutineDispatchers
import ru.maksonic.beresta.feature.notes.list.api.domain.DateFormatter
import ru.maksonic.beresta.feature.notes.list.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUiMapper
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.notes.list.core.NotesListProgram
import ru.maksonic.beresta.feature.notes.list.core.NotesListSandbox
import ru.maksonic.beresta.feature.notes.list.core.ui.NotesListWidget

/**
 * @Author maksonic on 24.04.2023
 */
val notesListFeatureModel = module {
    single<NotesInteractor> { NotesInteractor.Impl(repository = get()) }
    single<DateFormatter> { DateFormatter.Core() }
    single { NoteUiMapper() }
    single {
        NotesListProgram(
            notesInteractor = get(),
            fetchNotesUseCase = get(),
            mapper = get(),
            appLanguageEngineApi = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    viewModel { NotesListSandbox(program = get()) }
    single<NotesListApi.Ui> { NotesListWidget() }
}
package ru.maksonic.beresta.screen.hidden_notes.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.coroutine_dispatchers.CoroutineDispatchers
import ru.maksonic.beresta.screen.hidden_notes.core.HiddenNotesSandbox
import ru.maksonic.beresta.screen.hidden_notes.core.programs.HiddenNotesDataProgram
import ru.maksonic.beresta.screen.hidden_notes.core.programs.HiddenNotesSortProgram

/**
 * @Author maksonic on 21.07.2023
 */
val hiddenNotesScreenModule = module {
    single {
        HiddenNotesDataProgram(
            notesInteractor = get(),
            fetchHiddenNotesUseCase = get(),
            mapper = get(),
            appLanguageEngineApi = get(),
            dateFormatter = get(),
            navigator = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    single {
        HiddenNotesSortProgram(
            listSortStateUiApi = get(),
            listSortStateFeatureState = get(),
            noteCardUiState = get(),
            noteCardFeatureState = get(),
        )
    }

    viewModel {
        HiddenNotesSandbox(hiddenNotesDataProgram = get(), hiddenNotesSortProgram = get())
    }
}
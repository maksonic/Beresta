package ru.maksonic.beresta.screen.main.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.coroutine_dispatchers.CoroutineDispatchers
import ru.maksonic.beresta.screen.main.core.MainSandbox
import ru.maksonic.beresta.screen.main.core.programs.ChipsDataProgram
import ru.maksonic.beresta.screen.main.core.programs.NotesDataProgram
import ru.maksonic.beresta.screen.main.core.programs.NotesPreferencesProgram

/**
 * @Author maksonic on 25.04.2023
 */
val mainScreenModule = module {
    single {
        ChipsDataProgram(
            foldersListUseCase = get(),
            foldersMapper = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO)),
            appLanguageEngineApi = get(),
            stickyItemsTitleFormatter = get(),
        )
    }
    single {
        NotesDataProgram(
            notesInteractor = get(),
            fetchNotesUseCase = get(),
            mapper = get(),
            appLanguageEngineApi = get(),
            dateFormatter = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    single {
        NotesPreferencesProgram(
            listSortStateUiApi = get(),
            listSortStateFeatureState = get(),
            noteCardUiState = get(),
            noteCardFeatureState = get(),
        )
    }

    viewModel {
        MainSandbox(
            notesDataProgram = get(),
            notesPreferencesProgram = get(),
            chipsDataProgram = get()
        )
    }
}
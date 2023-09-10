package ru.maksonic.beresta.screen.main.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.coroutine_dispatchers.CoroutineDispatchers
import ru.maksonic.beresta.screen.main.core.MainSandbox
import ru.maksonic.beresta.screen.main.core.programs.ChipsDataProgram
import ru.maksonic.beresta.screen.main.core.programs.ChipsSortProgram
import ru.maksonic.beresta.screen.main.core.programs.NotesDataProgram
import ru.maksonic.beresta.screen.main.core.programs.NotesSortProgram

/**
 * @Author maksonic on 25.04.2023
 */
val mainScreenModule = module {
    single {
        ChipsSortProgram(
            listSortStateUiApi = get(),
            listSortStateFeatureState = get()
        )
    }
    single {
        ChipsDataProgram(
            interactor = get(),
            foldersMapper = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO)),
            appLanguageEngineApi = get(),
            stickyFoldersTitleFormatter = get(),
            chipsRowApi = get()
        )
    }
    single {
        NotesDataProgram(
            notesInteractor = get(),
            mapper = get(),
            appLanguageEngineApi = get(),
            dateFormatter = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    single {
        NotesSortProgram(
            listSortStateUiApi = get(),
            listSortStateFeatureState = get(),
            noteCardUiState = get(),
            noteCardFeatureState = get(),
        )
    }

    viewModel {
        MainSandbox(
            notesDataProgram = get(),
            notesSortProgram = get(),
            chipsDataProgram = get(),
            chipsSortProgram = get(),
        )
    }
}
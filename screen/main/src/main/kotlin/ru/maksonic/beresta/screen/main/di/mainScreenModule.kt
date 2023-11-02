package ru.maksonic.beresta.screen.main.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.core.CoroutineDispatchers
import ru.maksonic.beresta.screen.main.core.MainSandbox
import ru.maksonic.beresta.screen.main.core.program.ChipsDataProgram
import ru.maksonic.beresta.screen.main.core.program.ChipsSortProgram
import ru.maksonic.beresta.screen.main.core.program.NotesDataProgram
import ru.maksonic.beresta.screen.main.core.program.NotesSortProgram

/**
 * @Author maksonic on 15.10.2023
 */
val mainScreenModule = module {
    factory {
        NotesDataProgram(
            fetchNotesUseCase = get(),
            fetchHiddenNotesPinStatusUseCase = get(),
            findMarkerColorByIdUseCase = get(),
            findWallpaperByParamsUseCase = get(),
            notesInteractor = get(),
            mapper = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    factory { ChipsDataProgram(fetchFoldersUseCase = get(), mapper = get()) }
    factory { ChipsSortProgram(fetchFoldersSortUseCase = get(), mapper = get()) }
    factory {
        NotesSortProgram(
            fetchNotesSortUseCase = get(),
            fetchNoteCardStateUseCase = get(),
            notesCardStateStoreUiApi = get(),
            notesCardStateMapper = get(),
            sortInteractor = get(),
            mapper = get(),
        )
    }
    viewModel {
        MainSandbox(
            notesDataProgram = get(),
            notesSortProgram = get(),
            chipsDataProgram = get(),
            chipsSortProgram = get()
        )
    }
}
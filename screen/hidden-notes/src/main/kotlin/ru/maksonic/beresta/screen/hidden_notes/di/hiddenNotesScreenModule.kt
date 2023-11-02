package ru.maksonic.beresta.screen.hidden_notes.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.core.CoroutineDispatchers
import ru.maksonic.beresta.screen.hidden_notes.core.HiddenNotesSandbox
import ru.maksonic.beresta.screen.hidden_notes.core.programs.HiddenNotesDataProgram
import ru.maksonic.beresta.screen.hidden_notes.core.programs.HiddenNotesScreenCaptureProgram
import ru.maksonic.beresta.screen.hidden_notes.core.programs.HiddenNotesSortProgram

/**
 * @Author maksonic on 21.07.2023
 */
val hiddenNotesScreenModule = module {
    factory {
        HiddenNotesDataProgram(
            fetchHiddenNotesUseCase = get(),
            findMarkerColorByIdUseCase = get(),
            findWallpaperByParamsUseCase = get(),
            notesInteractor = get(),
            mapper = get()
        )
    }
    factory {
        HiddenNotesScreenCaptureProgram(
            screenCaptureManager = get(), ioDispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    factory {
        HiddenNotesSortProgram(
            fetchHiddenNotesSortUseCase = get(),
            sortInteractor = get(),
            sortMapper = get()
        )
    }

    viewModel {
        HiddenNotesSandbox(
            hiddenNotesDataProgram = get(),
            hiddenNotesSortProgram = get(),
            hiddenNotesScreenCaptureProgram = get()
        )
    }
}
package ru.maksonic.beresta.screen.trash.notes.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.screen.trash.notes.core.NotesTrashProgram
import ru.maksonic.beresta.screen.trash.notes.core.NotesTrashSandbox

/**
 * @Author maksonic on 30.05.2023
 */
val trashNotesScreenModule = module {
    single {
        NotesTrashProgram(
            fetchRemovedNotesUseCase = get(),
            findMarkerColorByIdUseCase = get(),
            findWallpaperByParamsUseCase = get(),
            notesInteractor = get(),
            notesMapper = get()
        )
    }

    viewModel { NotesTrashSandbox(program = get()) }
}
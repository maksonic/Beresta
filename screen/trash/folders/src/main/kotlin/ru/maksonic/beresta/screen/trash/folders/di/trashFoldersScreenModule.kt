package ru.maksonic.beresta.screen.trash.folders.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.screen.trash.folders.core.FoldersTrashProgram
import ru.maksonic.beresta.screen.trash.folders.core.FoldersTrashSandbox

/**
 * @Author maksonic on 30.05.2023
 */
val trashFoldersScreenModule = module {
    single {
        FoldersTrashProgram(
            fetchFoldersTrashListUseCase = get(),
            fetchNotesByFolderTrashListUseCase = get(),
            notesFoldersInteractor = get(),
            notesInteractor = get(),
            foldersMapper = get(),
            notesMapper = get()
        )
    }

    viewModel { FoldersTrashSandbox(program = get()) }
}
package ru.maksonic.beresta.screen.folders.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.screen.folders.core.FoldersListProgram
import ru.maksonic.beresta.screen.folders.core.FoldersScreenSandbox

/**
 * @Author maksonic on 04.07.2023
 */
val foldersScreenModule = module {
    single {
        FoldersListProgram(
            fetchFoldersUseCase = get(),
            fetchFoldersSortUseCase = get(),
            foldersInteractor = get(),
            foldersMapper = get(),
            fetchNotesUseCase = get(),
            notesInteractor = get(),
            notesMapper = get(),
            foldersSortUiMapper = get(),
            navigator = get(),
            currentFolderStoreUiApi = get()
        )
    }
    viewModel { FoldersScreenSandbox(program = get()) }
}
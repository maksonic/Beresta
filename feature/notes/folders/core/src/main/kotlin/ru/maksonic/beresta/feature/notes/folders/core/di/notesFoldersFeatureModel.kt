package ru.maksonic.beresta.feature.notes.folders.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.FoldersScreenSandbox
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderToUiMapper
import ru.maksonic.beresta.feature.notes.folders.core.add_folder_dialog.core.FolderDialogSandbox
import ru.maksonic.beresta.feature.notes.folders.core.add_folder_dialog.core.FolderDialogProgram
import ru.maksonic.beresta.feature.notes.folders.core.FoldersListCore
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.FoldersListProgram

/**
 * @Author maksonic on 24.04.2023
 */
val notesFoldersFeatureModel = module {
    single { NoteFolderToUiMapper() }
    //dialog
    single {
        FolderDialogProgram(
            fetchFolderByIdUseCase = get(),
            interactor = get(),
            mapper = get()
        )
    }
    viewModel { FolderDialogSandbox(program = get()) }
    //screen
    single {
        FoldersListProgram(
            fetchFoldersUseCase = get(),
            mapper = get(),
            foldersInteractor = get(),
            navigator = get()
        )
    }
    viewModel { FoldersScreenSandbox(program = get()) }
    single<FoldersListApi.Ui> { FoldersListCore() }}
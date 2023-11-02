package ru.maksonic.beresta.feature.ui.add_folder_dialog.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.ui.add_folder_dialog.core.AddFolderDialogUiCore
import ru.maksonic.beresta.feature.ui.add_folder_dialog.core.core.FolderDialogSandbox
import ru.maksonic.beresta.feature.ui.add_folder_dialog.api.AddFolderDialogUiApi
import ru.maksonic.beresta.feature.ui.add_folder_dialog.core.core.FolderDialogProgram

/**
 * @Author maksonic on 04.10.2023
 */
val addFolderDialogUiFeatureModule = module {
    single<AddFolderDialogUiApi> { AddFolderDialogUiCore() }
    single {
        FolderDialogProgram(
            fetchFolderByIdUseCase = get(),
            interactor = get(),
            mapper = get(),
            currentFolderStoreUiApi = get(),
            addChipDialogApi = get()
        )
    }
    viewModel { FolderDialogSandbox(program = get()) }
}
package ru.maksonic.beresta.feature.folders_chips.ui.di

import androidx.compose.runtime.mutableStateOf
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderToUiMapper
import ru.maksonic.beresta.feature.folders_chips.ui.FolderItemWidget
import ru.maksonic.beresta.feature.folders_chips.ui.FoldersChipsRowWidget
import ru.maksonic.beresta.feature.folders_chips.ui.dialog.core.FolderDialogProgram
import ru.maksonic.beresta.feature.folders_chips.ui.dialog.core.FolderDialogSandbox
import ru.maksonic.beresta.feature.folders_chips.ui.dialog.ui.AddNewChipDialog

/**
 * @Author maksonic on 03.07.2023
 */
val foldersChipsRowUiFeatureModule = module {
    single(named(FoldersApi.CurrentFolderKey.VALUE)) { mutableStateOf(1L) }

    single<FoldersApi.Ui.ChipsRow> {
        FoldersChipsRowWidget(currentSelectedChipId = get(named(FoldersApi.CurrentFolderKey.VALUE)))
    }
    single<FoldersApi.Ui.AddChipDialog> { AddNewChipDialog() }
    single<FoldersApi.Ui.FolderItem> { FolderItemWidget() }
    single { FolderToUiMapper() }
    single {
        FolderDialogProgram(
            fetchFolderByIdUseCase = get(),
            interactor = get(),
            mapper = get()
        )
    }
    viewModel { FolderDialogSandbox(program = get()) }
}
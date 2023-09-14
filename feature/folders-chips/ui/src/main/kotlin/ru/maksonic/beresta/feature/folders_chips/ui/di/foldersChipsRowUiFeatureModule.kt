package ru.maksonic.beresta.feature.folders_chips.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUiMapper
import ru.maksonic.beresta.feature.folders_chips.ui.chips_row.FoldersChipsRowWidget
import ru.maksonic.beresta.feature.folders_chips.ui.dialog.core.FolderDialogProgram
import ru.maksonic.beresta.feature.folders_chips.ui.dialog.core.FolderDialogSandbox
import ru.maksonic.beresta.feature.folders_chips.ui.dialog.ui.FoldersAddNewChipDialog
import ru.maksonic.beresta.feature.folders_chips.ui.folder_item.FolderItemWidget
import ru.maksonic.beresta.feature.folders_chips.ui.placeholder.FoldersPlaceholder

/**
 * @Author maksonic on 03.07.2023
 */
val foldersChipsRowUiFeatureModule = module {
    single<FoldersApi.ChipsRow.Ui> { FoldersChipsRowWidget() }
    factory<FoldersApi.AddChipDialog.Ui> { FoldersAddNewChipDialog() }
    single<FoldersApi.FolderItem.Ui> { FolderItemWidget() }
    single<FoldersApi.ListPlaceholder.Ui> { FoldersPlaceholder() }

    single { FolderUiMapper() }
    single {
        FolderDialogProgram(
            interactor = get(),
            mapper = get(),
            chipsRowApi = get(),
            addChipDialogApi = get()
        )
    }
    viewModel { FolderDialogSandbox(program = get()) }
}
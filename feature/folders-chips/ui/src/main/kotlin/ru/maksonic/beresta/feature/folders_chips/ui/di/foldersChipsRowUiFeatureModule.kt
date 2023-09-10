package ru.maksonic.beresta.feature.folders_chips.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUiMapper
import ru.maksonic.beresta.feature.folders_chips.ui.FolderItemWidget
import ru.maksonic.beresta.feature.folders_chips.ui.FoldersChipsRowWidget
import ru.maksonic.beresta.feature.folders_chips.ui.dialog.core.FolderDialogProgram
import ru.maksonic.beresta.feature.folders_chips.ui.dialog.core.FolderDialogSandbox
import ru.maksonic.beresta.feature.folders_chips.ui.dialog.ui.AddNewChipDialog
import ru.maksonic.beresta.feature.folders_chips.ui.widget.FoldersPlaceholder

/**
 * @Author maksonic on 03.07.2023
 */
val foldersChipsRowUiFeatureModule = module {
    single<FoldersApi.Ui.ChipsRow> { FoldersChipsRowWidget() }
    single<FoldersApi.Ui.AddChipDialog> { AddNewChipDialog() }
    single<FoldersApi.Ui.FolderItem> { FolderItemWidget() }
    single<FoldersApi.Ui.Placeholder> { FoldersPlaceholder() }
    single { FolderUiMapper() }
    single {
        FolderDialogProgram(
            interactor = get(),
            mapper = get(),
            chipsRowApi = get(),
            addChipDialog = get()
        )
    }
    viewModel { FolderDialogSandbox(program = get()) }
}
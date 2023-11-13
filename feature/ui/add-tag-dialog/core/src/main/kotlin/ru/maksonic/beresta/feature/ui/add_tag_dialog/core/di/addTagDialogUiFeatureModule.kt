package ru.maksonic.beresta.feature.ui.add_tag_dialog.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.core.CoroutineDispatchers
import ru.maksonic.beresta.feature.ui.add_tag_dialog.api.AddTagDialogUiApi
import ru.maksonic.beresta.feature.ui.add_tag_dialog.core.AddTagDialogUiCore
import ru.maksonic.beresta.feature.ui.add_tag_dialog.core.core.AddTagDialogProgram
import ru.maksonic.beresta.feature.ui.add_tag_dialog.core.core.AddTagDialogSandbox

/**
 * @Author maksonic on 13.11.2023
 */
val addTagDialogUiFeatureModule = module {
    factory<AddTagDialogUiApi> { AddTagDialogUiCore() }
    factory {
        AddTagDialogProgram(
            repository = get(),
            mapper = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    viewModel { AddTagDialogSandbox(program = get()) }
}
package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.HiddenNotesEnterPasswordDialog
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.HiddenNotesEnterPasswordProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.HiddenNotesEnterPasswordSandbox

/**
 * @Author maksonic on 15.07.2023
 */
val hiddenNotesUiFeatureModule = module {
    single { HiddenNotesEnterPasswordProgram(screenCaptureManager = get(), passwordStore = get()) }
    viewModel { HiddenNotesEnterPasswordSandbox(program = get()) }
    single<HiddenNotesApi.Ui.EnterPasswordDialog> { HiddenNotesEnterPasswordDialog() }
}
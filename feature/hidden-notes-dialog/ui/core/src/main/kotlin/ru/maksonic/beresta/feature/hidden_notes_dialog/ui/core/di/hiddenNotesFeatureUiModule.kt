package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.core.CoroutineDispatchers
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.HiddenNotesDialogUiApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.HiddenNotesPinInputDialog
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.NotCreatedHiddenNotesPinDialog
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.HiddenNotesDialogSandbox
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.program.HiddenNotesPinProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.program.HiddenNotesScreenCaptureProgram

/**
 * @Author maksonic on 15.07.2023
 */
val hiddenNotesDialogUiFeatureModule = module {
    factory<HiddenNotesDialogUiApi.PinInputDialog> { HiddenNotesPinInputDialog() }
    factory<HiddenNotesDialogUiApi.NotCreatedPinDialog> { NotCreatedHiddenNotesPinDialog() }
    factory { HiddenNotesScreenCaptureProgram(screenCaptureManager = get()) }
    factory {
        HiddenNotesPinProgram(
            hiddenNotesPinCreator = get(),
            deleteHiddenNotesUseCase = get(),
            pinPrivacySettings = get(),
            pinFailManager = get(),
            biometricEngine = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO))
        )
    }

    viewModel {
        HiddenNotesDialogSandbox(
            pinProgram = get(),
            screenCaptureProgram = get()
        )
    }
}
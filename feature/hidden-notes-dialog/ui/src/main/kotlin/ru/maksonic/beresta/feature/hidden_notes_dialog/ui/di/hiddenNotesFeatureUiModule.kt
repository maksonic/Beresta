package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.coroutine_dispatchers.CoroutineDispatchers
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.HiddenNotesDialogSandbox
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.program.HiddenNotesPasswordProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.program.HiddenNotesScreenCaptureProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.service.AlarmReceiver
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.service.AndroidAlarmScheduler
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.HiddenNotesEnterPasswordDialog

/**
 * @Author maksonic on 15.07.2023
 */
val hiddenNotesUiFeatureModule = module {
    single { HiddenNotesScreenCaptureProgram(screenCaptureManager = get()) }
    single { AndroidAlarmScheduler(context = androidContext()) }
    //single { AlarmReceiver(pinFailCounter = get()) }
    single {
        HiddenNotesPasswordProgram(
            deleteHiddenNotesUseCase = get(),
            passwordStore = get(),
            pinSecurePrefs = get(),
            pinFailCounter = get(),
            alarmScheduler = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    viewModel { HiddenNotesDialogSandbox(passwordProgram = get(), screenCaptureProgram = get()) }
    single<HiddenNotesApi.Ui.EnterPasswordDialog> { HiddenNotesEnterPasswordDialog() }
}
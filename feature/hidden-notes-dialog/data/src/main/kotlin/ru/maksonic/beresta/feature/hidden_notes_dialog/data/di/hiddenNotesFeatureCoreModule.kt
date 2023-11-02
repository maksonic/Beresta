package ru.maksonic.beresta.feature.hidden_notes_dialog.data.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.core.CoroutineDispatchers
import ru.maksonic.beresta.feature.hidden_notes_dialog.data.FetchHiddenNotesPinStatusUseCaseImpl
import ru.maksonic.beresta.feature.hidden_notes_dialog.data.HiddenNotesPinCreatorCore
import ru.maksonic.beresta.feature.hidden_notes_dialog.data.PinFailManagerCore
import ru.maksonic.beresta.feature.hidden_notes_dialog.data.PinPrivacySettingsCore
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.HiddenNotesPinCreator
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.PinFailManager
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.PinPrivacySettings
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.usecase.FetchHiddenNotesPinStatusUseCase

/**
 * @Author maksonic on 15.07.2023
 */
val hiddenNotesDialogDataFeatureModule = module {
    factory<PinPrivacySettings> {
        PinPrivacySettingsCore(datastore = get(), biometricEngine = get())
    }
    factory<PinFailManager> { PinFailManagerCore(datastore = get()) }
    factory<FetchHiddenNotesPinStatusUseCase> {
        FetchHiddenNotesPinStatusUseCaseImpl(hiddenNotesPinCreator = get())
    }
    factory<HiddenNotesPinCreator> {
        HiddenNotesPinCreatorCore(
            context = androidContext(),
            cryptoEngine = get(),
            datastore = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
}
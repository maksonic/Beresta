package ru.maksonic.beresta.feature.hidden_notes_dialog.core.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.core.HiddenNotesFeatureCore
import ru.maksonic.beresta.feature.hidden_notes_dialog.core.SecurePrefsFeatureCore

/**
 * @Author maksonic on 15.07.2023
 */
val hiddenNotesCoreFeatureModule = module {
    single<HiddenNotesApi.Feature> { HiddenNotesFeatureCore(datastore = get()) }
    single<HiddenNotesApi.Feature.SecurePrefs> { SecurePrefsFeatureCore(datastore = get()) }
}
package ru.maksonic.beresta.feature.hidden_notes.core.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.hidden_notes.api.HiddenNotesApi
import ru.maksonic.beresta.feature.hidden_notes.core.HiddenNotesFeatureCore

/**
 * @Author maksonic on 15.07.2023
 */
val hiddenNotesCoreFeatureModule = module {
    single<HiddenNotesApi.Feature> { HiddenNotesFeatureCore(datastore = get()) }
}
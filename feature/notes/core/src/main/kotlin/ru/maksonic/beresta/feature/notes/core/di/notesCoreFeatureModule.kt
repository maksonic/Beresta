package ru.maksonic.beresta.feature.notes.core.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.core.CardStateStorageCore

/**
 * @Author maksonic on 27.06.2023
 */
val notesCoreFeatureModule = module {
    single<NotesApi.CardStateStorage> { CardStateStorageCore(datastore = get()) }
}
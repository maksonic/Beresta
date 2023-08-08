package ru.maksonic.beresta.feature.hidden_notes_dialog.core.di

import androidx.datastore.core.ExperimentalMultiProcessDataStore
import androidx.datastore.core.MultiProcessDataStoreFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.core.HiddenNotesFeatureCore
import ru.maksonic.beresta.feature.hidden_notes_dialog.core.PinFailCounterCore
import ru.maksonic.beresta.feature.hidden_notes_dialog.core.SecurePrefsFeatureCore
import java.io.File

/**
 * @Author maksonic on 15.07.2023
 */
@OptIn(ExperimentalMultiProcessDataStore::class)
val hiddenNotesCoreFeaturePinFail = module {
  /*  single {
        MultiProcessDataStoreFactory.create(
            serializer = PinFailInfoSerializer,
            produceFile = {
                File("${androidContext().cacheDir.path}/beresta.preferences_pb")
            }
        )
    }*/
}
val hiddenNotesCoreFeatureModule = module {
    single<HiddenNotesApi.Feature> { HiddenNotesFeatureCore(datastore = get()) }
    single<HiddenNotesApi.Feature.SecurePrefs> { SecurePrefsFeatureCore(datastore = get()) }
    single<HiddenNotesApi.Feature.PinFailCounter> { PinFailCounterCore(datastore = get()) }
}
package ru.maksonic.beresta.feature.hidden_notes_dialog.core.di

import androidx.datastore.core.ExperimentalMultiProcessDataStore
import org.koin.dsl.module
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.core.HiddenNotesFeatureCore
import ru.maksonic.beresta.feature.hidden_notes_dialog.core.PinFailCounterCore
import ru.maksonic.beresta.feature.hidden_notes_dialog.core.PinPrivacyStateFeatureCore

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
    single<HiddenNotesApi.Feature.PinPrivacyState> { PinPrivacyStateFeatureCore(datastore = get()) }
    single<HiddenNotesApi.Feature.PinFailCounter> { PinFailCounterCore(datastore = get()) }
}
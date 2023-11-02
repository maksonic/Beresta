package ru.maksonic.beresta.platform.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import ru.maksonic.beresta.common.data.Datastore

/**
 * @Author maksonic on 14.10.2023
 */
class DatastoreCore(private val context: Context) : Datastore {
    private val Context.coreDatabase by preferencesDataStore(name = "app_preferences")

    override val datastore: DataStore<Preferences>
        get() = context.coreDatabase
}

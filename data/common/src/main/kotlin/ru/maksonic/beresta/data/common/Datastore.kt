package ru.maksonic.beresta.data.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
 * @Author maksonic on 19.12.2022
 */
interface Datastore {
    val datastore: DataStore<Preferences>

    class Core(private val context: Context) : Datastore {
        private companion object {
            private const val NAME = "user_saved_preferences"
        }
        private val Context.coreDatabase by preferencesDataStore(name = NAME)

        override val datastore: DataStore<Preferences>
            get() = context.coreDatabase
    }
}
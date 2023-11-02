package ru.maksonic.beresta.common.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

/**
 * @Author maksonic on 13.10.2023
 */
interface Datastore {
    val datastore: DataStore<Preferences>
}

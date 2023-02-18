package ru.maksonic.beresta.feature.onboarding.core.data

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
/**
 * @Author maksonic on 19.12.2022
 */
class OnboardingVisibilityDatastore(private val datastore: Datastore) : OnboardingApi.Visibility {
    private val key = booleanPreferencesKey("prefs_onb_showing_state_key")

    override suspend fun notShowAgain() {
        datastore.datastore.edit { preferences ->
            preferences[key] = false
        }
    }

    override val currentState: Flow<Boolean> = datastore.datastore.data.map { preferences ->
        return@map preferences[key] ?: true
    }
}
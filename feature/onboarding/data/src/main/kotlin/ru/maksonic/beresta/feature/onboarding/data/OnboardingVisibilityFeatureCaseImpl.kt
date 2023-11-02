package ru.maksonic.beresta.feature.onboarding.data

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.common.data.Datastore
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingVisibilityFeatureCase

/**
 * @Author maksonic on 19.06.2023
 */
class OnboardingVisibilityFeatureCaseImpl(
    private val datastore: Datastore
) : OnboardingVisibilityFeatureCase {
    private val key = booleanPreferencesKey("prefs_onboarding_showing_state_key")

    override val visibilityState: Flow<Boolean> = datastore.datastore.data.map { preferences ->
        return@map preferences[key] ?: true
    }

    override suspend fun notShowOnboardingAgain() {
        datastore.datastore.edit { preferences -> preferences[key] = false }
    }
}
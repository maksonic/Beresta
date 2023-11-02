package ru.maksonic.beresta.feature.hidden_notes_dialog.data

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.common.core.security.BiometricEngine
import ru.maksonic.beresta.common.data.Datastore
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.PinPrivacy
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.PinPrivacySettings

/**
 * @Author maksonic on 03.08.2023
 */
private const val BASE_HIDDEN_NOTES_KEY = "prefs_hidden_notes_"

class PinPrivacySettingsCore(
    private val biometricEngine: BiometricEngine,
    private val datastore: Datastore) : PinPrivacySettings {
    private val pinKey = booleanPreferencesKey(BASE_HIDDEN_NOTES_KEY + "pin_visibility_key")
    private val tapKey = booleanPreferencesKey(BASE_HIDDEN_NOTES_KEY + "visibility_tap_key")
    private val bioKey = booleanPreferencesKey(BASE_HIDDEN_NOTES_KEY + "biometric_enabled")

    override val state: Flow<PinPrivacy> = datastore.datastore.data.map { prefs ->
        PinPrivacy(
            isVisibleWhenInputProcess = prefs[pinKey] ?: false,
            isVisibleOnKeyboardTap = prefs[tapKey] ?: true,
            isCanUseBiometric = biometricEngine.isAvailable,
            isEnabledBiometric = prefs[bioKey] ?: false
        )
    }

    override suspend fun updateVisibility(isVisible: Boolean) {
        datastore.datastore.edit { prefs -> prefs[pinKey] = isVisible }
    }

    override suspend fun updateKeyTapVisibility(isVisible: Boolean) {
        datastore.datastore.edit { prefs -> prefs[tapKey] = isVisible }
    }

    override suspend fun updateBiometricEnablement(isEnabled: Boolean) {
        datastore.datastore.edit { prefs -> prefs[bioKey] = isEnabled }
    }
}
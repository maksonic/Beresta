package ru.maksonic.beresta.core.system.vibration

import android.os.Build
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import ru.maksonic.beresta.core.system.VibrationPerformer
import ru.maksonic.beresta.data.common.Datastore

/**
 * @Author maksonic on 17.07.2023
 */
class VibrationPerformerCore(private val datastore: Datastore) : VibrationPerformer {
    private val key = booleanPreferencesKey("prefs_app_vibration_key")
    private val _mutableVibrationState = MutableStateFlow(true)
    private val vibrationState = _mutableVibrationState.asStateFlow()

    override val isEnabled = datastore.datastore.data.map { pref ->
        val current = pref[key] ?: true
        _mutableVibrationState.update { current }
        current
    }

    override suspend fun init() {
        isEnabled.collect {
            _mutableVibrationState.update { it }
        }
    }

    override suspend fun updateVibrationState(isEnabled: Boolean) {
        datastore.datastore.edit { pref ->
            pref[key] = isEnabled
            _mutableVibrationState.update { isEnabled }
        }
    }

    override fun keyboardTapVibration(view: View) {
        if (vibrationState.value)
            view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
    }

    override fun toggleTapVibration(view: View, isToggleOn: Boolean) {
        val feedbackConstants = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            if (isToggleOn) HapticFeedbackConstants.TOGGLE_ON else HapticFeedbackConstants.TOGGLE_OFF
        } else {
            HapticFeedbackConstants.KEYBOARD_TAP
        }

        if (vibrationState.value)
            view.performHapticFeedback(feedbackConstants)
    }
}
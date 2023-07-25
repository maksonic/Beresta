package ru.maksonic.beresta.core

import android.view.View
import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 17.07.2023
 */
interface VibrationPerformer {
    val isEnabled: Flow<Boolean>
    suspend fun updateVibrationState(isEnabled: Boolean)

    fun keyboardTapVibration(view: View)
    fun toggleTapVibration(view: View, isEnabled: Boolean)
}
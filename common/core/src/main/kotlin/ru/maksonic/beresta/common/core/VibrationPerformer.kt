package ru.maksonic.beresta.common.core

import android.view.View
import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 14.10.2023
 */
interface VibrationPerformer {
    val isEnabled: Flow<Boolean>
    suspend fun init()
    suspend fun updateVibrationState(isEnabled: Boolean)

    fun keyboardTapVibration(view: View)
    fun toggleTapVibration(view: View, isToggleOn: Boolean)
}
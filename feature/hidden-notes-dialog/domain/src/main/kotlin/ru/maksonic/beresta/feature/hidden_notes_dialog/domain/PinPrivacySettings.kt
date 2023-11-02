package ru.maksonic.beresta.feature.hidden_notes_dialog.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 25.10.2023
 */
interface PinPrivacySettings {
    val state: Flow<PinPrivacy>
    suspend fun updateVisibility(isVisible: Boolean)
    suspend fun updateKeyTapVisibility(isVisible: Boolean)
    suspend fun updateBiometricEnablement(isEnabled: Boolean)
}
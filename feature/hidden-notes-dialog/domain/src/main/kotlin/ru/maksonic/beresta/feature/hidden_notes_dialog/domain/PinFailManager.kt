package ru.maksonic.beresta.feature.hidden_notes_dialog.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 25.10.2023
 */
interface PinFailManager {
    val state: Flow<PinFailInfo>
    suspend fun updateFailCounter(value: Int)
    suspend fun updateCoolDown(isCoolDown: Boolean, endTime: Long)
}
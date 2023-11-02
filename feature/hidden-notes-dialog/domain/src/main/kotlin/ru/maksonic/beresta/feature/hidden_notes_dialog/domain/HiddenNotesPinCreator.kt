package ru.maksonic.beresta.feature.hidden_notes_dialog.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 24.10.2023
 */
interface HiddenNotesPinCreator {
    val state: Flow<Boolean>
    suspend fun createPin(byteArray: ByteArray)
    fun verifyPin(data: ByteArray): Result<Boolean>
    suspend fun deletePin()
}
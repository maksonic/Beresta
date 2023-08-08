package ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui

import java.time.LocalDateTime

/**
 * @Author maksonic on 06.08.2023
 */
data class PinFailInfo(
    val failCount: Int,
    val isCoolDown: Boolean,
    val tick: Int,
    val timestamp: LocalDateTime?
) {
    companion object {
        val INITIAL = PinFailInfo(
            failCount = 0,
            isCoolDown = false,
            tick = 15,
            timestamp = null
        )
    }
}
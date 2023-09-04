package ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

/**
 * @Author maksonic on 06.08.2023
 */
@Stable
@Immutable
data class PinInfo(
    val isCreated: Boolean = false,
    val isCoolDown: Boolean,
    val failCount: Int,
    val endDate: Long,
    val delay: Long = 5L
) {
    companion object {
        val INITIAL = PinInfo(
            isCreated = false,
            isCoolDown = false,
            failCount = 0,
            endDate = 0L,
            delay = 5L
        )
    }
}
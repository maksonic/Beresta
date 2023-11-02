package ru.maksonic.beresta.feature.hidden_notes_dialog.domain

/**
 * @Author maksonic on 06.08.2023
 */
data class PinFailInfo(
    val isValid: Boolean = false,
    val isCoolDown: Boolean,
    val failCount: Int,
    val endDate: Long,
    val delay: Long = 5L
) {
    companion object {
        val INITIAL = PinFailInfo(
            isValid = false,
            isCoolDown = false,
            failCount = 0,
            endDate = 0L,
            delay = 5L
        )
    }
}
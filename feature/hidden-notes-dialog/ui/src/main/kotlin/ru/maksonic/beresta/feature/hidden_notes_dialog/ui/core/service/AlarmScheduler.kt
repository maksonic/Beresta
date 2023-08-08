package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.service

import java.time.LocalDateTime


interface AlarmScheduler {
    fun schedule()
    fun cancel()
}
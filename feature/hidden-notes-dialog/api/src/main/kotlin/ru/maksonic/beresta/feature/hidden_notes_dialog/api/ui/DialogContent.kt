package ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui

/**
 * @Author maksonic on 26.07.2023
 */
enum class DialogContent {
    INITIAL, KEYBOARD, RESET_PIN
}

val DialogContent.isKeyboard get() = this == DialogContent.KEYBOARD
val DialogContent.isReset get() = this == DialogContent.RESET_PIN
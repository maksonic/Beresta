package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.ui

/**
 * @Author maksonic on 26.07.2023
 */
enum class DialogContent {
    INITIAL, KEYBOARD, RESET_PIN, BLOCK_KEYBOARD
}

val DialogContent.isKeyboard get() = this == DialogContent.KEYBOARD
val DialogContent.isReset get() = this == DialogContent.RESET_PIN
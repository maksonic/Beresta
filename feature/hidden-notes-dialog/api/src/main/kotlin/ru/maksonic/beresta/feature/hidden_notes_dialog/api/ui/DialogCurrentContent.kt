package ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui

/**
 * @Author maksonic on 26.07.2023
 */
enum class DialogCurrentContent {
    INITIAL, KEYBOARD
}

val DialogCurrentContent.isKeyboard get() = this == DialogCurrentContent.KEYBOARD
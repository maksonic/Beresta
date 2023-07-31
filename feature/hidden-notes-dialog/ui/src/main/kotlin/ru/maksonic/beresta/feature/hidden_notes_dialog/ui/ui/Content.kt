package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.movableContentOf
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.isKeyboard
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Model
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget.HiddenNotesInformation
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget.SafetyNumericKeyboard
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateContent

/**
 * @Author maksonic on 26.07.2023
 */
@Composable
internal fun Content(
    model: State<Model>,
    send: SendMessage,
    isShakeTitleEffect: State<Boolean>,
    disableShakeEffect: () -> Unit,
    onCloseClicked: () -> Unit
) {

    val keyboard = movableContentOf {
        SafetyNumericKeyboard(
            model = model,
            send = send,
            isShakeTitleEffect = isShakeTitleEffect,
            disableShakeEffect = disableShakeEffect,
            onCloseClicked = onCloseClicked
        )
    }

    if (model.value.isHasPinCode) {
        keyboard()
    } else {
        AnimateContent(model.value.dialogCurrentContent.isKeyboard) {
            if (it) {
                keyboard()
            } else {
                HiddenNotesInformation(send)
            }
        }
    }
}
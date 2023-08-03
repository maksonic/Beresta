package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.DialogContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Model
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget.NumericKeyboardContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget.PinInformationContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget.ResetPinCodeContent
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
    AnimateContent(model.value.dialogContent) {
        when (it) {
            DialogContent.INITIAL -> PinInformationContent(send)
            DialogContent.RESET_PIN -> ResetPinCodeContent(send)
            DialogContent.KEYBOARD -> {
                NumericKeyboardContent(
                    model = model,
                    send = send,
                    isShakeTitleEffect = isShakeTitleEffect,
                    disableShakeEffect = disableShakeEffect,
                    onCloseClicked = onCloseClicked,
                )
            }
        }
    }
}
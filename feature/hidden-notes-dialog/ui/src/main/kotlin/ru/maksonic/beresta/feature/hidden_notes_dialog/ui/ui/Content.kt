package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.DialogContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Model
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget.NumericKeyboardContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget.PinCoolDownContent
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
    var isAnimated by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!isAnimated) {
            delay(50)
            isAnimated = true
        }
    }

    if (isAnimated) {
        AnimateContent(model.value.dialogContent) {
            InnerContent(
                state = it,
                model = model,
                send = send,
                isShakeTitleEffect = isShakeTitleEffect,
                disableShakeEffect = disableShakeEffect,
                onCloseClicked = onCloseClicked
            )
        }
    } else {
        InnerContent(
            state = model.value.dialogContent,
            model = model,
            send = send,
            isShakeTitleEffect = isShakeTitleEffect,
            disableShakeEffect = disableShakeEffect,
            onCloseClicked = onCloseClicked
        )
    }
}

@Composable
private fun InnerContent(
    state: DialogContent,
    model: State<Model>,
    send: SendMessage,
    isShakeTitleEffect: State<Boolean>,
    disableShakeEffect: () -> Unit,
    onCloseClicked: () -> Unit
) {
    when (state) {
        DialogContent.INITIAL -> PinInformationContent(send)
        DialogContent.RESET_PIN -> ResetPinCodeContent(send)
        DialogContent.BLOCK_KEYBOARD -> PinCoolDownContent(model, send)
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
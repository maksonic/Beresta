package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import ru.maksonic.beresta.common.ui_kit.animation.AnimateContent
import ru.maksonic.beresta.common.ui_kit.animation.ShakeController
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.ui.DialogContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.Model
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.Msg
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.biometric.BiometricState
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.widget.NumericKeyboardContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.widget.PinCoolDownContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.widget.PinInformationContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.widget.ResetPinCodeContent

/**
 * @Author maksonic on 26.07.2023
 */
@Composable
internal fun Content(
    model: Model,
    send: Send,
    titleShakeController: ShakeController,
    onCloseClicked: () -> Unit
) {
    var isAnimated by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!isAnimated) {
            delay(50)
            isAnimated = true
        }
    }


    LaunchedEffect(model.isVisibleBiometricDialog) {
        if (model.isVisibleBiometricDialog) {
            send(Msg.Inner.ShowedBiometricDialog(BiometricState.LOGIN))
        }
    }

    if (isAnimated) {
        AnimateContent(model.dialogContent) {
            InnerContent(
                state = it,
                model = model,
                send = send,
                titleShakeController = titleShakeController,
                onCloseClicked = onCloseClicked
            )
        }
    } else {
        InnerContent(
            state = model.dialogContent,
            model = model,
            send = send,
            titleShakeController = titleShakeController,
            onCloseClicked = onCloseClicked
        )
    }
}

@Composable
private fun InnerContent(
    state: DialogContent,
    model: Model,
    send: Send,
    titleShakeController: ShakeController,
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
                titleShakeController = titleShakeController,
                onCloseClicked = onCloseClicked,
            )
        }
    }
}
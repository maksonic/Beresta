package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.DialogContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Model
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Msg
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget.NumericKeyboardContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget.PinInformationContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget.ResetPinCodeContent
import ru.maksonic.beresta.ui.theme.component.TextDesign
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

            DialogContent.BLOCK_KEYBOARD -> {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Red), contentAlignment = Alignment.Center
                ) {
                    val coolDown = remember { mutableIntStateOf(model.value.pinInfo.coolDownDelay) }

                    LaunchedEffect(model.value.pinInfo.coolDownDelay) {

                        repeat(model.value.pinInfo.coolDownDelay) {
                            delay(1000)
                            coolDown.intValue = coolDown.intValue - 1
                            Log.e("AAA", "UI ${coolDown.value}")
                        }.let {
                            send(Msg.Inner.CancelCoolDown)
                        }
                    }
                    /*LaunchedEffect(model.value.pinInfo.coolDownDelay) {
                        do {
                            coolDown.intValue = coolDown.intValue - 1
                        } while (coolDown.intValue == 0)

                        send(Msg.Inner.CancelCoolDown)
                    }*/

                    Text(text = coolDown.intValue.toString(), style = TextDesign.header)
                }
            }
        }
    }
}
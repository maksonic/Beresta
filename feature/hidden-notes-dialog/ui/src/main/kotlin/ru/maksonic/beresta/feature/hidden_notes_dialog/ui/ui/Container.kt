package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.core.system.VibrationManager
import ru.maksonic.beresta.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.DialogContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinFailStatus
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.isKeyboard
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.isReset
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Eff
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.HiddenNotesDialogSandbox
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Msg
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.scrim
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.surface.SurfacePro
import ru.maksonic.beresta.ui.widget.toastShortTime

/**
 * @Author maksonic on 15.07.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
fun Container(
    hideDialog: () -> Unit,
    onSuccessPin: () -> Unit,
    isBlocked: Boolean,
    onBlockedBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    vibrationManager: VibrationManager = koinInject(),
    sandbox: HiddenNotesDialogSandbox = koinViewModel()
) {
    val model = sandbox.model.collectAsStateWithLifecycle()
    val isShakeTitleEffect = remember { mutableStateOf(false) }

    HandleUiEffects(
        effects = sandbox.effects,
        vibrationManager = vibrationManager,
        hideDialog = hideDialog,
        onSuccessPin = onSuccessPin,
        shakeKeyboardTitle = { isShakeTitleEffect.value = true }
    )

    BackHandler {
        when {
            model.value.dialogContent.isKeyboard && !model.value.isHasPinCode -> {
                sandbox.send(Msg.Ui.UpdateDialogContent(DialogContent.INITIAL))
            }

            model.value.dialogContent.isReset -> {
                sandbox.send(Msg.Ui.UpdateDialogContent(DialogContent.KEYBOARD))
            }

            else -> if (isBlocked) onBlockedBackPressed() else sandbox.send(Msg.Ui.CloseDialog)
        }
    }

    LaunchedEffect(Unit) {
        sandbox.send(Msg.Inner.UpdatedScreenCapturePermission(true))
    }

    SurfacePro(color = scrim) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Box(
                modifier
                    .fillMaxWidth()
                    .systemBarsPadding()
                    .padding(dp16)
                    .clip(Shape.cornerExtra)
                    .background(secondaryContainer)
            ) {
                Content(
                    model = model,
                    send = sandbox::send,
                    isShakeTitleEffect = isShakeTitleEffect,
                    disableShakeEffect = { isShakeTitleEffect.value = false },
                    onCloseClicked = {
                        if (isBlocked) onBlockedBackPressed()
                        else sandbox.send(Msg.Ui.CloseDialog)
                    }
                )
            }
        }
    }
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    vibrationManager: VibrationManager,
    hideDialog: () -> Unit,
    shakeKeyboardTitle: () -> Unit,
    onSuccessPin: () -> Unit,
) {
    val context = LocalContext.current
    val failCreationMessage = text.hiddenNotes.hintFailCreationCode
    val failVerificationMessage = text.hiddenNotes.hintFailVerificationCode

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.CloseDialog -> hideDialog()
            is Eff.ShowWrongPinCodeMessage -> {
                val failMessage = when (eff.fail!!) {
                    PinFailStatus.NOT_MATCHED -> failCreationMessage
                    PinFailStatus.NOT_VERIFIED -> failVerificationMessage
                }
                context.toastShortTime(failMessage)
                vibrationManager.vibrateShortOneShot()
                shakeKeyboardTitle()
            }

            is Eff.NavigateToHiddenNotes -> onSuccessPin().run { hideDialog() }
        }
    }
}
package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core

import androidx.activity.compose.BackHandler
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.core.VibrationManager
import ru.maksonic.beresta.common.ui_kit.animation.ShakeController
import ru.maksonic.beresta.common.ui_kit.animation.rememberShakeController
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_kit.toastShortTime
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.scrim
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.ui.DialogContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.ui.PinFailStatus
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.ui.isKeyboard
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.ui.isReset
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.Eff
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.HiddenNotesDialogSandbox
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.Msg
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.biometric.BiometricState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.platform.core.ui.findActivity
import ru.maksonic.beresta.platform.elm.compose.ElmComposableEffectHandler

/**
 * @Author maksonic on 15.07.2023
 */
internal typealias Send = (Msg) -> Unit

@Composable
internal fun Container(
    hideDialog: () -> Unit,
    onSuccessPin: () -> Unit,
    isBlocked: Boolean,
    onBlockedBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    vibrationManager: VibrationManager = koinInject(),
    sandbox: HiddenNotesDialogSandbox = koinViewModel()
) {
    val model by sandbox.model.collectAsStateWithLifecycle()
    val titleShakeController = rememberShakeController()
    val activity = LocalContext.current.findActivity()
    fun prompt(state: BiometricState) = BiometricPrompt(
        activity, biometricCallBack(sandbox::send, state, model.pinFailInfo.isValid)
    )

    val signUpPrompt = PromptInfo.Builder()
        .setTitle(text.hiddenNotes.titleSignUpBiometricDialog)
        .setDescription(text.hiddenNotes.descriptionBiometricDialog)
        .setConfirmationRequired(false)
        .setNegativeButtonText(text.hiddenNotes.btnTitleNegativeBiometricDialog)

    val loginPrompt = PromptInfo.Builder()
        .setTitle(text.hiddenNotes.titleAuthBiometricDialog)
        .setConfirmationRequired(false)
        .setNegativeButtonText(text.shared.btnTitleBack)

    HandleUiEffects(
        effects = sandbox.effects,
        vibrationManager = vibrationManager,
        hideDialog = hideDialog,
        onSuccessPin = onSuccessPin,
        titleShakeController = titleShakeController,
        showBiometricDialog = {
            val prompt = if (it == BiometricState.SIGN_UP) signUpPrompt else loginPrompt
            prompt(it).authenticate(prompt.build())
        }
    )

    BackHandler {
        when {
            model.dialogContent.isKeyboard && !model.pinFailInfo.isValid -> {
                sandbox.send(Msg.Ui.UpdateDialogContent(DialogContent.INITIAL))
            }

            model.dialogContent.isReset -> {
                sandbox.send(Msg.Ui.UpdateDialogContent(DialogContent.KEYBOARD))
            }

            else -> if (isBlocked) onBlockedBackPressed() else sandbox.send(Msg.Ui.ClosedDialog)
        }
    }

    LaunchedEffect(Unit) {
        sandbox.send(Msg.Inner.UpdatedScreenCapturePermission(true))

        if (!model.isFetchedPinInfo) {
            sandbox.send(Msg.Inner.FetchedPinStatusRequest)
        }
    }

    SurfacePro(color = scrim) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Box(
                modifier
                    .fillMaxWidth()
                    .systemBarsPadding()
                    .padding(dp16)
                    .clip(Theme.shape.cornerExtra)
                    .background(secondaryContainer)
            ) {
                Content(
                    model = model,
                    send = sandbox::send,
                    titleShakeController = titleShakeController,
                    onCloseClicked = {
                        if (isBlocked) onBlockedBackPressed()
                        else sandbox.send(Msg.Ui.ClosedDialog)
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
    titleShakeController: ShakeController,
    hideDialog: () -> Unit,
    onSuccessPin: () -> Unit,
    showBiometricDialog: (BiometricState) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val failCreationMessage = text.errorUi.failCreationCode
    val failVerificationMessage = text.errorUi.failVerificationCode

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.CloseDialog -> hideDialog()
            is Eff.ShowWrongPinCodeMessage -> {
                val failMessage = when (eff.fail!!) {
                    PinFailStatus.NOT_MATCHED -> failCreationMessage
                    PinFailStatus.NOT_VERIFIED -> failVerificationMessage
                }
                context.toastShortTime(failMessage)
                titleShakeController.shake()
                scope.launch {
                    vibrationManager.vibrateShortOneShot()
                }
            }

            is Eff.NavigateToHiddenNotes -> onSuccessPin().run { hideDialog() }
            is Eff.ShowEnableBiometricDialog -> {}
            is Eff.ShowBiometricDialog -> showBiometricDialog(eff.key)
        }
    }
}

private fun biometricCallBack(send: Send, biometricState: BiometricState, isValidPin: Boolean) =
    object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            if (biometricState == BiometricState.SIGN_UP) {
                send(Msg.Inner.SuccessPinResult)
            }
        }

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            if (biometricState == BiometricState.SIGN_UP && isValidPin) {
                send(Msg.Inner.OnEnableBiometricAuthClicked)
            } else {
                send(Msg.Inner.SuccessPinResult)
            }
        }
    }

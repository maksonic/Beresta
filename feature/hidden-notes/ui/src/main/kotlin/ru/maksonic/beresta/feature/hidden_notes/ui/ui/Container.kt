package ru.maksonic.beresta.feature.hidden_notes.ui.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.feature.hidden_notes.ui.core.Eff
import ru.maksonic.beresta.feature.hidden_notes.ui.core.HiddenNotesEnterPasswordSandbox
import ru.maksonic.beresta.feature.hidden_notes.ui.core.Msg
import ru.maksonic.beresta.feature.hidden_notes.ui.core.PinFailStatus
import ru.maksonic.beresta.feature.hidden_notes.ui.ui.widget.HiddenNotesInformation
import ru.maksonic.beresta.feature.hidden_notes.ui.ui.widget.SafetyNumericKeyboard
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.scrim
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateContent
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.surface.SurfacePro
import ru.maksonic.beresta.ui.widget.toastShortTime

/**
 * @Author maksonic on 15.07.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
fun Container(
    visibilityState: State<Boolean>,
    updateVisibility: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    onSuccessPin: () -> Unit,
    sandbox: HiddenNotesEnterPasswordSandbox = koinViewModel()
) {
    AnimateFadeInOut(
        visible = visibilityState.value,
        fadeInDuration = Theme.animVelocity.dialogVisibility,
        fadeOutDuration = Theme.animVelocity.dialogVisibility
    ) {
        val model = sandbox.model.collectAsStateWithLifecycle()
        val isKeyboard = remember { mutableStateOf(model.value.isHasPinCode) }

        HandleUiEffects(
            effects = sandbox.effects,
            updateVisibility = updateVisibility,
            onSuccessPin = onSuccessPin
        )

        LaunchedEffect(Unit) {
            sandbox.send(Msg.Inner.UpdatedScreenCapturePermissionState(true))
        }

        BackHandler {
            if (isKeyboard.value) {
                isKeyboard.value = false
            } else {
                sandbox.send(Msg.Ui.CloseDialog)
            }
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

                    val keyboard = movableContentOf {
                        SafetyNumericKeyboard(
                            model = model,
                            send = sandbox::send,
                            onBackClicked = { isKeyboard.value = false },
                            onCloseClicked = {
                                isKeyboard.value = false
                                sandbox.send(Msg.Ui.CloseDialog)
                            }
                        )
                    }

                    if (model.value.isHasPinCode) {
                        keyboard()
                    } else {
                        AnimateContent(isKeyboard.value) {
                            if (it) {
                                keyboard()
                            } else {
                                HiddenNotesInformation(
                                    onCloseClicked = { sandbox.send(Msg.Ui.CloseDialog) },
                                    onAcceptClicked = { isKeyboard.value = true }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    updateVisibility: (Boolean) -> Unit,
    onSuccessPin: () -> Unit,
) {
    val context = LocalContext.current
    val failCreationMessage = text.hiddenNotes.hintFailCreationCode
    val failVerificationMessage = text.hiddenNotes.hintFailVerificationCode

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.CloseDialog -> updateVisibility(false)
            is Eff.ShowWrongPinCodeMessage -> {
                val message = when (eff.fail!!) {
                    PinFailStatus.NOT_MATCHED -> failCreationMessage
                    PinFailStatus.NOT_VERIFIED -> failVerificationMessage
                }
                context.toastShortTime(message)
            }

            is Eff.NavigateToHiddenNotes -> onSuccessPin().run { updateVisibility(false) }
        }
    }
}
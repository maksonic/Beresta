package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.widget

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextDecoration
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.core.VibrationPerformer
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.animation.ShakeController
import ru.maksonic.beresta.common.ui_kit.animation.shake
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.noRippleClick
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.ArrowBack
import ru.maksonic.beresta.common.ui_kit.icons.Backspace
import ru.maksonic.beresta.common.ui_kit.icons.Close
import ru.maksonic.beresta.common.ui_kit.icons.Fingerprint
import ru.maksonic.beresta.common.ui_kit.icons.PreviewOff
import ru.maksonic.beresta.common.ui_kit.icons.PreviewOn
import ru.maksonic.beresta.common.ui_kit.icons.VisibilityOff
import ru.maksonic.beresta.common.ui_kit.icons.VisibilityOn
import ru.maksonic.beresta.common.ui_kit.text.TextTitleLarge
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.colors.onSurfaceVariant
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.provide.dp12
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.ui.DialogContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Send
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.Model
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.Msg
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 15.07.2023
 */
private const val MAX_ITEMS_IN_EACH_ROW = 3

@Composable
internal fun NumericKeyboardContent(
    model: Model,
    send: Send,
    titleShakeController: ShakeController,
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier.padding(bottom = dp16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(model, send, onCloseClicked)

        Title(model, titleShakeController)

        PinInput(model, send)

        Keyboard(model, send)

        if (model.pinFailInfo.isValid) {
            ForgotPinButton { send(Msg.Ui.UpdateDialogContent(DialogContent.RESET_PIN)) }
        }
    }
}

@Composable
private fun TopBar(
    model: Model,
    send: Send,
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(top = dp8, start = dp8, end = dp8)
    ) {
        AnimateFadeInOut(!model.pinFailInfo.isValid) {
            ButtonIcon(icon = AppIcon.ArrowBack) {
                send(Msg.Ui.UpdateDialogContent(DialogContent.INITIAL))
            }
        }

        Spacer(modifier.weight(1f))

        Crossfade(model.pinPrivacy.isVisibleWhenInputProcess, label = "") {
            ButtonIcon(
                icon = if (it) AppIcon.VisibilityOn else AppIcon.VisibilityOff,
                onClick = { send(Msg.Ui.OnPinVisibilityClicked) })
        }

        Crossfade(model.pinPrivacy.isVisibleOnKeyboardTap, label = "") {
            ButtonIcon(
                icon = if (it) AppIcon.PreviewOn else AppIcon.PreviewOff,
                onClick = { send(Msg.Ui.OnKeyTapVisibilityClicked) })
        }

        ButtonIcon(icon = AppIcon.Close, onClick = onCloseClicked)
    }
}

@Composable
private fun Title(model: Model, titleShakeController: ShakeController) {
    val creationPinTitle = with(text.hiddenNotes) {
        if (!model.isCachedFirstInput) hintCreateCode else hintRepeatCode
    }

    val title = with(text.hiddenNotes) {
        if (!model.pinFailInfo.isValid) creationPinTitle else hintVerifyCode
    }

    TextTitleLarge(
        text = title,
        modifier = Modifier
            .shake(titleShakeController)
            .padding(top = dp16)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Keyboard(
    model: Model,
    send: Send,
    modifier: Modifier = Modifier,
    vibrationPerformer: VibrationPerformer = koinInject()
) {
    val numericButtons = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0)

    FlowRow(
        modifier
            .fillMaxWidth()
            .padding(start = dp12, end = dp12)
            .verticalScroll(rememberScrollState()),
        maxItemsInEachRow = MAX_ITEMS_IN_EACH_ROW
    ) {
        numericButtons.forEachIndexed { index, value ->
            when (index) {
                numericButtons.lastIndex -> {
                    ActionButton(
                        onClick = { send(Msg.Ui.OnBackspaceClicked) },
                        icon = AppIcon.Backspace,
                        modifier = modifier.weight(1f),
                        vibrationPerformer = vibrationPerformer,
                        isVisibleTouch = model.pinPrivacy.isVisibleOnKeyboardTap
                    )
                }

                numericButtons.lastIndex - 2 -> {
                    if (model.isVisibleBiometricKeyboardButton) {
                        ActionButton(
                            onClick = { send(Msg.Ui.OnKeyboardShowedBiometricDialog) },
                            icon = AppIcon.Fingerprint,
                            modifier = modifier.weight(1f),
                            vibrationPerformer = vibrationPerformer,
                            isVisibleTouch = model.pinPrivacy.isVisibleOnKeyboardTap
                        )
                    } else {
                        Box(modifier.weight(1f))
                    }
                }

                else -> {
                    NumericButton(
                        value = value,
                        onClick = { send(Msg.Inner.UpdatedInput(value)) },
                        modifier = modifier.weight(1f),
                        vibrationPerformer = vibrationPerformer,
                        isVisibleTouch = model.pinPrivacy.isVisibleOnKeyboardTap
                    )
                }
            }
        }
    }
}

@Composable
private fun NumericButton(
    value: Int,
    onClick: () -> Unit,
    modifier: Modifier,
    vibrationPerformer: VibrationPerformer,
    isVisibleTouch: Boolean
) {
    BaseButton(onClick, modifier, vibrationPerformer, isVisibleTouch) {
        Text(value.toString(), style = TextDesign.titleLarge)
    }
}

@Composable
private fun ActionButton(
    onClick: () -> Unit,
    icon: ImageVector,
    modifier: Modifier,
    vibrationPerformer: VibrationPerformer,
    isVisibleTouch: Boolean
) {
    BaseButton(onClick, modifier, vibrationPerformer, isVisibleTouch) {
        Icon(imageVector = icon, tint = onBackground, contentDescription = "")
    }
}

@Composable
private fun BaseButton(
    onClick: () -> Unit,
    modifier: Modifier,
    vibrationPerformer: VibrationPerformer,
    isVisibleTouch: Boolean,
    content: @Composable () -> Unit
) {
    val screenOrientation = LocalConfiguration.current.orientation
    val btnPortraitHeight = Theme.size.btnPrimaryHeight
    val btnLandscapeHeight = Theme.size.minimumTouchTargetSize
    val btnHeight = remember {
        derivedStateOf {
            if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) btnLandscapeHeight
            else btnPortraitHeight
        }
    }
    val clickModifier = if (isVisibleTouch) Modifier.rippledClick(
        performer = vibrationPerformer,
        role = Role.Button,
        rippleColor = primary,
        onClick = onClick
    )
    else Modifier.noRippleClick(performer = vibrationPerformer, onClick = onClick)

    Box(
        modifier
            .fillMaxWidth()
            .height(btnHeight.value)
            .padding(dp4)
            .clip(Theme.shape.cornerNormal)
            .background(onSurfaceVariant)
            .then(clickModifier),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun ForgotPinButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier
            .padding(top = dp12)
            .clip(Theme.shape.cornerNormal)
            .rippledClick(rippleColor = primary, onClick = onClick)
    ) {
        Text(
            text.hiddenNotes.hintForgetPinCode,
            style = TextDesign.labelMedium.copy(outline),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.padding(dp16)
        )
    }
}
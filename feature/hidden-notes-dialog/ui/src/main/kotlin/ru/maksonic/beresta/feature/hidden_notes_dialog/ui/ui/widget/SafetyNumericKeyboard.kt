package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget

import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.koin.compose.koinInject
import ru.maksonic.beresta.core.system.VibrationPerformer
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.DialogCurrentContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Model
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Msg
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.SendMessage
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSurfaceVariant
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.theme.icons.Backspace
import ru.maksonic.beresta.ui.theme.icons.Close
import ru.maksonic.beresta.ui.widget.DefaultIcon
import ru.maksonic.beresta.ui.widget.button.ClickableIcon
import ru.maksonic.beresta.ui.widget.functional.rippleClickable

/**
 * @Author maksonic on 15.07.2023
 */
private const val MAX_ITEMS_IN_EACH_ROW = 3
private const val TITLE_SHAKE_REPEAT_COUNT = 3
private const val TITLE_SHAKE_DELAY = 60L

@Composable
fun SafetyNumericKeyboard(
    model: State<Model>,
    send: SendMessage,
    isShakeTitleEffect: State<Boolean>,
    disableShakeEffect: () -> Unit,
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier.padding(bottom = dp16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopBar(model, send, onCloseClicked)

        Title(model, isShakeTitleEffect, disableShakeEffect)

        Input(model)

        Keyboard(send)
    }
}

@Composable
private fun TopBar(
    model: State<Model>,
    send: SendMessage,
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(top = dp8, start = dp8, end = dp8)
    ) {
        if (!model.value.isHasPinCode) {
            ClickableIcon(icon = AppIcon.ArrowBack) {
                send(Msg.Ui.UpdateDialogCurrentContent(DialogCurrentContent.INITIAL))
            }
        }

        Spacer(modifier.weight(1f))

        ClickableIcon(icon = AppIcon.Close, action = onCloseClicked)
    }
}

@Composable
private fun Title(
    model: State<Model>,
    isShakeTitleEffect: State<Boolean>,
    disableShakeEffect: () -> Unit
) {
    val hint = rememberUpdatedState(with(text.hiddenNotes) {
        if (!model.value.isHasPinCode) {
            if (model.value.cachedCode.isEmpty()) hintCreateCode else hintRepeatCode
        } else {
            hintVerifyCode
        }
    })

    val isEnabledLeft = remember { mutableStateOf(false) }
    val isEnabledRight = remember { mutableStateOf(false) }
    val startPadding = animateDpAsState(targetValue = if (isEnabledLeft.value) dp8 else 0.dp)
    val endPadding = animateDpAsState(targetValue = if (isEnabledRight.value) dp8 else 0.dp)

    LaunchedEffect(isShakeTitleEffect.value) {
        if (isShakeTitleEffect.value) {
            repeat(TITLE_SHAKE_REPEAT_COUNT) {
                isEnabledLeft.value = true
                delay(TITLE_SHAKE_DELAY)
                isEnabledLeft.value = false
                isEnabledRight.value = true
                delay(TITLE_SHAKE_DELAY)
                isEnabledRight.value = false
            }.let { disableShakeEffect() }
        }
    }

    Text(
        hint.value,
        style = TextDesign.title,
        modifier = Modifier.padding(start = startPadding.value, end = endPadding.value)
    )
}

@Composable
private fun Input(model: State<Model>) {
    Text(
        text = model.value.placeholder,
        style = TextDesign.header,
        letterSpacing = 10.sp,
        modifier = Modifier.padding(top = dp16, bottom = dp16)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Keyboard(
    send: SendMessage,
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
                    BackspaceButton(
                        onClick = { send(Msg.Ui.OnBackspaceClicked) },
                        modifier = modifier.weight(1f),
                        vibrationPerformer = vibrationPerformer,
                    )
                }

                numericButtons.lastIndex - 2 -> { Box(modifier.weight(1f)) }

                else -> {
                    NumericButton(
                        value = value,
                        onClick = { send(Msg.Inner.UpdateInput(value)) },
                        modifier = modifier.weight(1f),
                        vibrationPerformer = vibrationPerformer
                    )
                }
            }

        }
    }
}

@Composable
fun NumericButton(
    value: Int,
    onClick: () -> Unit,
    modifier: Modifier,
    vibrationPerformer: VibrationPerformer,
) {
    BaseButton(onClick, modifier, vibrationPerformer) {
        Text(value.toString(), style = TextDesign.topBar)
    }
}

@Composable
fun BackspaceButton(
    onClick: () -> Unit,
    modifier: Modifier,
    vibrationPerformer: VibrationPerformer,
) {
    BaseButton(onClick, modifier, vibrationPerformer) {
        DefaultIcon(AppIcon.Backspace)
    }
}

@Composable
private fun BaseButton(
    onClick: () -> Unit,
    modifier: Modifier,
    vibrationPerformer: VibrationPerformer,
    content: @Composable () -> Unit
) {
    val screenOrientation = LocalConfiguration.current.orientation
    val btnPortraitHeight = Theme.widgetSize.btnPrimaryHeight
    val btnLandscapeHeight = Theme.widgetSize.minimumTouchTargetSize
    val btnHeight = remember {
        derivedStateOf {
            if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) btnLandscapeHeight
            else btnPortraitHeight
        }
    }

    Box(
        modifier
            .height(btnHeight.value)
            .padding(dp4)
            .clip(Shape.cornerNormal)
            .background(onSurfaceVariant)
            .rippleClickable(
                performer = vibrationPerformer,
                role = Role.Button,
                rippleColor = primary
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
package ru.maksonic.beresta.screen.settings.appearance.ui.widget

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.appearance.core.Msg
import ru.maksonic.beresta.screen.settings.appearance.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.DefaultSliderColors
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.AppAnimationVelocity
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.theme.component.isDisabled
import ru.maksonic.beresta.ui.widget.button.ModalSheetBottomButtonsRow

/**
 * @Author maksonic on 14.07.2023
 */
private const val ANIM_DISABLED = 0F
private const val ANIM_VERY_FAST = 4F
private const val SLIDER_STEP = 3

@Composable
internal fun AnimationsVelocityPickerSheetContent(
    send: SendMessage,
) {
    Column {
        BaseLinesPicker() { marker ->
            val velocity = when (marker) {
                1F -> AppAnimationVelocity.Key.SLOW
                2F -> AppAnimationVelocity.Key.NORMAL
                3F -> AppAnimationVelocity.Key.FAST
                4F -> AppAnimationVelocity.Key.VERY_FAST
                else -> AppAnimationVelocity.Key.DISABLE
            }
            send(Msg.Inner.UpdatedAnimationsVelocity(velocity))
        }

        ModalSheetBottomButtonsRow(
            onLeftClicked = { send(Msg.Ui.OnModalSheetAnimationsVelocityDefaultClicked) },
            onRightClicked = { send(Msg.Ui.OnModalSheetAcceptClicked) }
        )
    }
}

@Composable
private fun BaseLinesPicker(
    modifier: Modifier = Modifier,
    updatePosition: (Float) -> Unit,
) {
    val currentVelocityState = rememberUpdatedState(Theme.animVelocity.current)
    val currentKeyTitle = rememberUpdatedState(
        with(text.settingsAppearance) {
            when (Theme.animVelocity.current) {
                AppAnimationVelocity.Key.SLOW -> hintAnimSlow
                AppAnimationVelocity.Key.NORMAL -> hintAnimNormal
                AppAnimationVelocity.Key.FAST -> hintAnimFast
                AppAnimationVelocity.Key.VERY_FAST -> hintAnimVeryFast
                else -> hintAnimDisabled
            }
        }
    )

    Column(
        modifier
            .fillMaxWidth()
            .padding(start = dp32, end = dp32),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        RotatableBox(currentVelocityState, modifier)

        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = "x0", style = TextDesign.captionNormal)

            Text(text = currentKeyTitle.value, style = TextDesign.title.copy(onSurface))

            Text(text = "x2", style = TextDesign.captionNormal)
        }

        Slider(
            value = currentVelocityState.value.value,
            onValueChange = { updatePosition(it) },
            valueRange = ANIM_DISABLED..ANIM_VERY_FAST,
            steps = SLIDER_STEP,
            colors = DefaultSliderColors,
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RotatableBox(currentVelocityState: State<AppAnimationVelocity.Key>, modifier: Modifier) {
    var currentRotation by remember { mutableFloatStateOf(0f) }
    val rotation = remember { Animatable(currentRotation) }
    val animValue = rememberUpdatedState(
        when (currentVelocityState.value) {
            AppAnimationVelocity.Key.SLOW -> 3000
            AppAnimationVelocity.Key.NORMAL -> 2000
            AppAnimationVelocity.Key.FAST -> 1000
            AppAnimationVelocity.Key.VERY_FAST -> 600
            else -> 1
        }
    )
    var isPlayingCornerAnimation by remember { mutableStateOf(true) }
    val targetCorner = rememberUpdatedState(if (isPlayingCornerAnimation) 50 else 1)
    val infiniteTransition = rememberInfiniteTransition()
    val cornerPercent by infiniteTransition.animateValue(
        initialValue = 1,
        targetValue = targetCorner.value,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(animValue.value, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
    )

    LaunchedEffect(currentVelocityState.value) {
        if (!currentVelocityState.value.isDisabled) {
            isPlayingCornerAnimation = true

            rotation.animateTo(
                targetValue = currentRotation + 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(animValue.value, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            ) {
                currentRotation = value
            }
        } else {
            isPlayingCornerAnimation = false
            if (currentRotation > 0f) {
                rotation.snapTo(0f)
                currentRotation = 0f
            }
        }
    }

    Box(
        modifier
            .padding(top = dp16, bottom = dp32)
            .rotate(rotation.value)
            .clip(RoundedCornerShape(cornerPercent))
            .size(100.dp)
            .background(primary)
    )
}
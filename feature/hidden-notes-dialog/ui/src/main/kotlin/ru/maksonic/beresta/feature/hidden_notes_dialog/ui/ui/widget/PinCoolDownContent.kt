package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.maksonic.beresta.core.SECOND_LONG
import ru.maksonic.beresta.core.system.defaultEpochSecond
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Model
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Msg
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.SendMessage
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.onSecondaryContainer
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp24
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.widget.button.PrimaryButton
import java.time.LocalDateTime

/**
 * @Author maksonic on 29.08.2023
 */
private const val ONE_TICK = 1

@Composable
internal fun PinCoolDownContent(
    model: State<Model>,
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    var ticks by remember {
        mutableIntStateOf(
            model.value.pinInfo.endDate.minus(LocalDateTime.now().defaultEpochSecond).toInt()
        )
    }

    LaunchedEffect(Unit) {
        if (model.value.pinInfo.endDate <= LocalDateTime.now().defaultEpochSecond) {
            send(Msg.Inner.FinishedCoolDown)
        } else {
            if (ticks >= ONE_TICK) {
                repeat(ticks) {
                    delay(SECOND_LONG)
                    ticks -= ONE_TICK
                }.run { send(Msg.Inner.FinishedCoolDown) }
            } else {
                send(Msg.Inner.FinishedCoolDown)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(secondaryContainer), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = text.hiddenNotes.hintCoolDownVerification,
            style = TextDesign.title,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(start = dp16, end = dp16, top = dp24, bottom = dp32)
        )

        Box(
            modifier
                .padding(bottom = dp16)
                .size(150.dp), contentAlignment = Alignment.Center
        ) {
            val animatedProgress = remember {
                Animatable(
                    initialValue = if (ticks >= 1) (ticks.toFloat() / 100) * (100 / model.value.pinInfo.delay.toFloat()) else 1f,
                    typeConverter = Float.VectorConverter,
                    visibilityThreshold = 0.01f,
                    label = "PinCoolDownProgressAnimationLabel"
                )
            }

            LaunchedEffect(Unit) {
                snapshotFlow { 0f }
                    .collect { value ->
                        val duration = if (ticks > 0) ticks * 1000 else 0
                        animatedProgress.animateTo(value, tween(duration, easing = LinearEasing))
                    }
            }

            CircularProgressIndicator(
                progress = animatedProgress.value,
                strokeWidth = 6.dp,
                color = primary,
                trackColor = onSecondaryContainer,
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                modifier = modifier.fillMaxSize()
            )
            Text(text = ticks.toString(), style = TextDesign.displaySmall.copy(primary))
        }

        PrimaryButton(
            action = { send(Msg.Ui.ClosedDialog) },
            title = text.shared.btnTitleClose,
            backgroundColor = surfaceVariant,
            titleColor = onSurface,
            modifier = modifier
                .fillMaxWidth()
                .padding(dp16)
        )
    }
}
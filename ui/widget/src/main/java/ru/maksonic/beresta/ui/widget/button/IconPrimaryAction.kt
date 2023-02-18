package ru.maksonic.beresta.ui.widget.button

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.icons.Add
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.widget.R
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 25.12.2022
 */
@Composable
fun IconPrimaryAction(action: () -> Unit, modifier: Modifier = Modifier) {
    val pressedState = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val sizeScale by animateFloatAsState(if (pressedState.value) 0.85f else 1f)
    Box(
        modifier
            .graphicsLayer(
                scaleX = sizeScale,
                scaleY = sizeScale
            )
            .size(Theme.widgetSize.btnPrimaryHeight)
            .clip(Shape.cornerRound)
            .background(primary)

            .noRippleClickable {
                scope.launch {
                    pressedState.value = true
                    delay(100)
                    action.invoke()
                    pressedState.value = false
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = AppIcon.Add,
            tint = tertiaryContainer,
            modifier = modifier.size(32.dp),
            contentDescription = ""
        )
    }
}
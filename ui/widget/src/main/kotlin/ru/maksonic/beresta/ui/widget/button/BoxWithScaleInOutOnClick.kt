package ru.maksonic.beresta.ui.widget.button

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @Author maksonic on 25.12.2022
 */
private enum class RowClickState {
    CLICKED, UNPRESSED
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoxWithScaleInOutOnClick(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = onClick,
    backgroundColor: State<Color> = remember { mutableStateOf(Color.Transparent) },
    shape: Shape = RoundedCornerShape(0.dp),
    content: @Composable () -> Unit
) {
    val pressedState = remember { mutableStateOf(RowClickState.UNPRESSED) }
    val scope = rememberCoroutineScope()
    val sizeScale by animateFloatAsState(
        if (pressedState.value == RowClickState.CLICKED) 0.85f else 1f, label = ""
    )
    val isEnabledModifier = if (isEnabled) modifier.combinedClickable(
        onClick = {
            scope.launch {
                pressedState.value = RowClickState.CLICKED
                delay(100)
                onClick.invoke()
                pressedState.value = RowClickState.UNPRESSED
            }
        },
        onLongClick = {
            scope.launch {
                pressedState.value = RowClickState.CLICKED
                delay(100)
                onLongClick.invoke()
                pressedState.value = RowClickState.UNPRESSED
            }
        },
        interactionSource = remember { MutableInteractionSource() },
        indication = null
    ) else
        modifier

    Box(
        modifier
            .graphicsLayer(
                scaleX = sizeScale,
                scaleY = sizeScale
            )
            .clip(shape)
            .drawBehind { drawRect(backgroundColor.value) }
            .then(isEnabledModifier),
        contentAlignment = Alignment.Center
    ) {
        content.invoke()
    }
}
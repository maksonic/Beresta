package ru.maksonic.beresta.ui.widget.functional.animation

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.ui.theme.color.onSecondaryContainer
import ru.maksonic.beresta.ui.theme.color.primaryContainer

/**
 * @Author maksonic on 29.05.2023
 */
private const val INITIAL_DELAY = 300
private const val DELAY_DIFFERENCE = 150

@Composable
fun PlaceholderListWidget(
    placeholdersCount: Int,
    isColumn: Boolean = true,
    modifier: Modifier = Modifier,
    placeholderContent: @Composable (animateColor: Animatable<Color, AnimationVector4D>) -> Unit
) {
    val delayList = List(placeholdersCount) { INITIAL_DELAY }
        .runningReduce { acc, i -> (acc + i) - DELAY_DIFFERENCE }

    val placeholderContent = remember { movableContentOf {
        repeat(placeholdersCount) {
            PlaceholderWidget(
                enterDelay = delayList[it],
                exitDelay = delayList.reversed()[it],
                placeholderContent = { animateColor -> placeholderContent(animateColor) }
            )
        }
    }}

    if (isColumn)
        Column(modifier) { placeholderContent() }
    else
        Row(modifier) { placeholderContent() }
}

@Composable
private fun PlaceholderWidget(
    enterDelay: Int,
    exitDelay: Int,
    placeholderContent: @Composable (animateColor: Animatable<Color, AnimationVector4D>) -> Unit
) {
    val startColor = primaryContainer
    val endColor = onSecondaryContainer
    val animateColor = remember { Animatable(startColor) }

    LaunchedEffect(Unit) {
        while (true) {
            animateColor.animateTo(
                endColor, animationSpec = tween(enterDelay, easing = LinearEasing)
            )
            animateColor.animateTo(
                startColor, animationSpec = tween(exitDelay, easing = LinearEasing)
            )
        }
    }

    Box {
        placeholderContent(animateColor)
    }
}

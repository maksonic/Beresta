package ru.maksonic.beresta.ui.widget.functional.animation

import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.Dp
import ru.maksonic.beresta.ui.theme.Theme

/**
 * @Author maksonic on 28.05.2023
 */

@Composable
fun animateDp(
    state: Dp,
    duration: Int = Theme.animSpeed.common,
    label: String = "DpAnimation",
    finishedListener: ((Dp) -> Unit)? = null
): State<Dp> {
    return animateValueAsState(
        state,
        Dp.VectorConverter,
        animationSpec = tween(duration),
        label = label,
        finishedListener = finishedListener
    )
}
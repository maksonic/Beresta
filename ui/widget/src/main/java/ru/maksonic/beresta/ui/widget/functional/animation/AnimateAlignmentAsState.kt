package ru.maksonic.beresta.ui.widget.functional.animation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment

/**
 * @Author maksonic on 21.11.2022
 */
@Composable
fun animateAlignmentAsState(
    targetAlignment: Alignment,
): State<Alignment> {
    val biased = targetAlignment as BiasAlignment
    val horizontal by animateFloatAsState(biased.horizontalBias)
    val vertical by animateFloatAsState(biased.verticalBias)

    return remember { derivedStateOf { BiasAlignment(horizontal, vertical) } }
}

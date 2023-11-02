package ru.maksonic.beresta.common.ui_kit.animation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
    val horizontal by animateFloatAsState(biased.horizontalBias, label = "")
    val vertical by animateFloatAsState(biased.verticalBias, label = "")

    return remember { derivedStateOf { BiasAlignment(horizontal, vertical) } }
}

package ru.maksonic.beresta.ui.theme.color

import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.ui.theme.component.TonalElevationToken

/**
 * @Author maksonic on 08.07.2023
 */
val DefaultSliderColors @Composable get() = SliderDefaults.colors(
    thumbColor = primary,
    activeTickColor = onPrimary,
    activeTrackColor = primary,
    inactiveTickColor = onSurfaceVariant,
    inactiveTrackColor = TonalElevationToken.Level5
)
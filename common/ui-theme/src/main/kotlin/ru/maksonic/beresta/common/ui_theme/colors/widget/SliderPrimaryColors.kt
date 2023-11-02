package ru.maksonic.beresta.common.ui_theme.colors.widget

import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.common.ui_theme.colors.onPrimary
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.provide.TonalElevation

/**
 * @Author maksonic on 21.10.2023
 */
val SliderPrimaryColors @Composable get() = SliderDefaults.colors(
    thumbColor = primary,
    activeTickColor = onPrimary,
    activeTrackColor = primary,
    inactiveTickColor = onPrimary,
    inactiveTrackColor = TonalElevation.Level5,
)
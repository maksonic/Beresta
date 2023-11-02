package ru.maksonic.beresta.common.ui_kit.switch

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import ru.maksonic.beresta.common.core.VibrationPerformer
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.surface
import ru.maksonic.beresta.common.ui_theme.provide.TonalElevation
import ru.maksonic.beresta.common.ui_theme.provide.dp16

/**
 * @Author maksonic on 21.10.2023
 */
@Composable
fun TogglePrimary(
    isEnabledToggle: Boolean,
    onToggleClicked: () -> Unit,
    vibrationPerformer: VibrationPerformer? = null
) {
    val view = LocalView.current

    Switch(
        checked = isEnabledToggle,
        onCheckedChange = {
            onToggleClicked().let {
                vibrationPerformer?.toggleTapVibration(view, isEnabledToggle)
            }
        },
        modifier = Modifier.padding(end = dp16),
        colors = SwitchColors(
            checkedThumbColor = surface,
            checkedTrackColor = primary,
            checkedBorderColor = primary,
            checkedIconColor = Color.Red,
            uncheckedThumbColor = outline,
            uncheckedTrackColor = TonalElevation.Level2,
            uncheckedBorderColor = outline,
            uncheckedIconColor = Color.Black,
            disabledCheckedThumbColor = Color.Black,
            disabledCheckedTrackColor = Color.Black,
            disabledCheckedBorderColor = Color.Black,
            disabledCheckedIconColor = Color.Black,
            disabledUncheckedThumbColor = Color.Black,
            disabledUncheckedTrackColor = Color.Black,
            disabledUncheckedBorderColor = Color.Black,
            disabledUncheckedIconColor = Color.Black
        )
    )
}
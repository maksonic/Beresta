package ru.maksonic.beresta.ui.widget.button

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import ru.maksonic.beresta.core.system.VibrationPerformer
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.component.TonalElevationToken
import ru.maksonic.beresta.ui.theme.component.dp16

/**
 * @Author maksonic on 17.07.2023
 */
@Composable
fun PrimaryToggle(
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
            uncheckedTrackColor = TonalElevationToken.Level2,
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
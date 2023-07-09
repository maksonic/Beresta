package ru.maksonic.beresta.ui.widget.button.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.TonalElevationToken
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.functional.rippleClickable

/**
 * @Author maksonic on 07.07.2023
 */
private const val SETTING_TITLE_MAX_LINES = 1
private const val SETTING_DESCRIPTION_MAX_LINES = 3

enum class RightPart {
    NOTHING, CURRENT_VALUE, TOGGLE
}

data class SettingItem(
    val title: String,
    val prefixIcon: ImageVector? = null,
    val descriptionHint: String = "",
    val valueHint: String = "",
    val rightPart: RightPart = RightPart.NOTHING,
    val isEnabledToggle: Boolean = false,
    val onClick: () -> Unit = {},
    val onToggleClicked: () -> Unit = onClick,
)

data class SettingComponentItem(val title: String, val items: List<SettingItem>)


@Composable
fun SettingClickableItem(setting: SettingItem, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = Theme.widgetSize.minimumTouchTargetSize)
            .rippleClickable(rippleColor = primary) { setting.onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (setting.prefixIcon != null) {
            Icon(
                imageVector = setting.prefixIcon,
                tint = onBackground,
                contentDescription = "",
                modifier = modifier.padding(start = dp16)
            )
        }

        Column(
            modifier
                .padding(start = dp16, end = dp16, top = dp12, bottom = dp12)
                .weight(1f)
        ) {
            Text(
                text = setting.title,
                style = TextDesign.title.copy(color = onBackground),
                maxLines = SETTING_TITLE_MAX_LINES,
                overflow = TextOverflow.Ellipsis
            )
            if (setting.descriptionHint.isNotBlank()) {
                Text(
                    text = setting.descriptionHint,
                    style = TextDesign.captionNormal.copy(color = outline),
                    maxLines = SETTING_DESCRIPTION_MAX_LINES,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.padding(top = dp8)
                )
            }
        }

        when (setting.rightPart) {
            RightPart.NOTHING -> {}
            RightPart.CURRENT_VALUE -> SettingValueHint(setting.valueHint, modifier)
            RightPart.TOGGLE -> Toggle(setting.isEnabledToggle, setting.onToggleClicked)
        }
    }
}

@Composable
private fun SettingValueHint(hint: String, modifier: Modifier) {
    Text(
        text = hint,
        style = TextDesign.bodyPrimary.copy(color = outline),
        modifier = modifier.padding(end = dp16),
    )
}

@Composable
private fun Toggle(isEnabledToggle: Boolean, onToggleClicked: () -> Unit) {
    Switch(
        checked = isEnabledToggle,
        onCheckedChange = { onToggleClicked() },
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
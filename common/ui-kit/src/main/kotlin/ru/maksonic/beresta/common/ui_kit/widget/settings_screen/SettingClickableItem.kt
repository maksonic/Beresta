package ru.maksonic.beresta.common.ui_kit.widget.settings_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextOverflow
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_kit.switch.TogglePrimary
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.core.VibrationPerformer
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.provide.dp12
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign

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
    val rightPart: RightPart = RightPart.NOTHING,
    val prefixIcon: ImageVector? = null,
    val descriptionHint: String = "",
    val valueHint: String = "",
    val isEnabledToggle: Boolean = false,
    val onClick: () -> Unit = {},
    val onToggleClicked: () -> Unit = onClick,
)

data class SettingComponentItem(val title: String, val items: List<SettingItem>)


@Composable
fun SettingClickableItem(
    setting: SettingItem,
    modifier: Modifier = Modifier,
    vibrationPerformer: VibrationPerformer = koinInject()
) {
    val view = LocalView.current

    Row(
        modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = Theme.size.minimumTouchTargetSize)
            .rippledClick(rippleColor = primary) {
                setting
                    .onClick()
                    .let {
                        if (setting.rightPart == RightPart.TOGGLE) {
                            vibrationPerformer.toggleTapVibration(
                                view, isToggleOn = setting.isEnabledToggle
                            )
                        }
                    }
            },
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
                style = TextDesign.bodyLarge.copy(color = onBackground),
                maxLines = SETTING_TITLE_MAX_LINES,
                overflow = TextOverflow.Ellipsis
            )
            if (setting.descriptionHint.isNotBlank()) {
                Text(
                    text = setting.descriptionHint,
                    style = TextDesign.labelMedium.copy(color = outline),
                    maxLines = SETTING_DESCRIPTION_MAX_LINES,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.padding(top = dp8)
                )
            }
        }

        when (setting.rightPart) {
            RightPart.NOTHING -> {}
            RightPart.CURRENT_VALUE -> SettingValueHint(setting.valueHint, modifier)
            RightPart.TOGGLE -> {
                TogglePrimary(setting.isEnabledToggle, setting.onToggleClicked, vibrationPerformer)
            }
        }
    }
}

@Composable
private fun SettingValueHint(hint: String, modifier: Modifier) {
    Text(
        text = hint,
        style = TextDesign.labelSmall.copy(color = outline),
        modifier = modifier.padding(end = dp16),
    )
}
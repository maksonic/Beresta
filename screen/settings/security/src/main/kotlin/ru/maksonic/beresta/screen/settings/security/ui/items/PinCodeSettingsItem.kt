package ru.maksonic.beresta.screen.settings.security.ui.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.PreviewOff
import ru.maksonic.beresta.common.ui_kit.icons.PreviewOn
import ru.maksonic.beresta.common.ui_kit.icons.VisibilityOff
import ru.maksonic.beresta.common.ui_kit.icons.VisibilityOn
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.RightPart
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingCategoryContainer
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingClickableItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingTextTitle
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.security.core.Model
import ru.maksonic.beresta.screen.settings.security.core.Msg
import ru.maksonic.beresta.screen.settings.security.ui.Send

/**
 * @Author maksonic on 03.08.2023
 */
@Composable
internal fun PinCodeSettingsItem(model: Model, send: Send) {
    val pinIcon = rememberUpdatedState(
        if (model.pinPrivacy.isVisibleWhenInputProcess) AppIcon.VisibilityOn else AppIcon.VisibilityOff
    )

    val keyTapIcon = rememberUpdatedState(
        if (model.pinPrivacy.isVisibleOnKeyboardTap) AppIcon.PreviewOn else AppIcon.PreviewOff
    )

    val settings = listOf(
        SettingItem(
            title = text.settingsSecurity.itemPinVisibility,
            prefixIcon = pinIcon.value,
            rightPart = RightPart.TOGGLE,
            isEnabledToggle = model.pinPrivacy.isVisibleWhenInputProcess,
            onClick = { send(Msg.Ui.OnPinVisibilityClicked) }
        ),
        SettingItem(
            title = text.settingsSecurity.itemKeyTapVisibility,
            descriptionHint = text.settingsSecurity.descriptionKeyTapVisibility,
            prefixIcon = keyTapIcon.value,
            rightPart = RightPart.TOGGLE,
            isEnabledToggle = model.pinPrivacy.isVisibleOnKeyboardTap,
            onClick = { send(Msg.Ui.OnKeyTapVisibilityClicked) }
        ),
    )

    SettingCategoryContainer {
        SettingTextTitle(title = text.settingsSecurity.hintPinCode)

        settings.forEach { setting ->
            SettingClickableItem(setting)
        }
    }
}

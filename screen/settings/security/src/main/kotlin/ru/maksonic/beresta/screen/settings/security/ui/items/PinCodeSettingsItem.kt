package ru.maksonic.beresta.screen.settings.security.ui.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.security.core.Model
import ru.maksonic.beresta.screen.settings.security.core.Msg
import ru.maksonic.beresta.screen.settings.security.ui.SendMessage
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.PreviewOff
import ru.maksonic.beresta.ui.theme.icons.PreviewOn
import ru.maksonic.beresta.ui.theme.icons.VisibilityOff
import ru.maksonic.beresta.ui.theme.icons.VisibilityOn
import ru.maksonic.beresta.ui.widget.button.settings.RightPart
import ru.maksonic.beresta.ui.widget.button.settings.SettingClickableItem
import ru.maksonic.beresta.ui.widget.button.settings.SettingItem
import ru.maksonic.beresta.ui.widget.surface.SettingContainer
import ru.maksonic.beresta.ui.widget.text.SettingTitle

/**
 * @Author maksonic on 03.08.2023
 */
@Composable
internal fun PinCodeSettingsItem(model: State<Model>, send: SendMessage) {
    val pinIcon = rememberUpdatedState(
        if (model.value.pinSecure.isVisiblePin) AppIcon.VisibilityOn else AppIcon.VisibilityOff
    )

    val keyTapIcon = rememberUpdatedState(
        if (model.value.pinSecure.isVisibleOnKeyboardTap) AppIcon.PreviewOn else AppIcon.PreviewOff
    )

    val settings = listOf(
        SettingItem(
            title = text.settingsSecurity.itemPinVisibility,
            prefixIcon = pinIcon.value,
            rightPart = RightPart.TOGGLE,
            isEnabledToggle = model.value.pinSecure.isVisiblePin,
            onClick = { send(Msg.Ui.OnPinVisibilityClicked) }
        ),
        SettingItem(
            title = text.settingsSecurity.itemKeyTapVisibility,
            descriptionHint = text.settingsSecurity.descriptionKeyTapVisibility,
            prefixIcon = keyTapIcon.value,
            rightPart = RightPart.TOGGLE,
            isEnabledToggle = model.value.pinSecure.isVisibleOnKeyboardTap,
            onClick = { send(Msg.Ui.OnKeyTapVisibilityClicked) }
        ),
    )

    SettingContainer {
        SettingTitle(title = text.settingsSecurity.hintPinCode)

        settings.forEach { setting ->
            SettingClickableItem(setting)
        }
    }
}

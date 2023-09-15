package ru.maksonic.beresta.screen.settings.notifications.ui.widget.items

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.notifications.core.Model
import ru.maksonic.beresta.screen.settings.notifications.core.Msg
import ru.maksonic.beresta.screen.settings.notifications.ui.SendMessage
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.notifications.Vibration
import ru.maksonic.beresta.ui.widget.button.settings.RightPart
import ru.maksonic.beresta.ui.widget.button.settings.SettingClickableItem
import ru.maksonic.beresta.ui.widget.button.settings.SettingItem
import ru.maksonic.beresta.ui.widget.surface.SettingContainer
import ru.maksonic.beresta.ui.widget.text.SettingTitle

/**
 * @Author maksonic on 15.09.2023
 */
@Composable
fun SoundsItem(model: Model, send: SendMessage) {
    SettingContainer {

        SettingTitle(title = text.settingsNotifications.titleSounds)

        settings(model, send).forEach { setting ->
            SettingClickableItem(setting)
        }
    }
}

@Composable
private fun settings(model: Model, send: SendMessage) = listOf(
    SettingItem(
        title = text.settingsNotifications.itemVibration,
        rightPart = RightPart.TOGGLE,
        prefixIcon = AppIcon.Vibration,
        isEnabledToggle = model.isEnabledVibration,
        onClick = { send(Msg.Ui.OnVibrationItemClicked) })
)
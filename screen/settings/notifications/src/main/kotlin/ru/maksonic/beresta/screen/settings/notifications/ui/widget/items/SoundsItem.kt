package ru.maksonic.beresta.screen.settings.notifications.ui.widget.items

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.notifications.Vibration
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.RightPart
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingCategoryContainer
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingClickableItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingTextTitle
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.notifications.core.Model
import ru.maksonic.beresta.screen.settings.notifications.core.Msg
import ru.maksonic.beresta.screen.settings.notifications.ui.Send

/**
 * @Author maksonic on 15.09.2023
 */
@Composable
fun SoundsItem(model: Model, send: Send) {
    SettingCategoryContainer {

        SettingTextTitle(title = text.settingsNotifications.titleSounds)

        settings(model, send).forEach { setting ->
            SettingClickableItem(setting)
        }
    }
}

@Composable
private fun settings(model: Model, send: Send) = listOf(
    SettingItem(
        title = text.settingsNotifications.itemVibration,
        rightPart = RightPart.TOGGLE,
        prefixIcon = AppIcon.Vibration,
        isEnabledToggle = model.isEnabledVibration,
        onClick = { send(Msg.Ui.OnVibrationItemClicked) })
)
package ru.maksonic.beresta.screen.settings.appearance.ui.widget.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Speed
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.RightPart
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingCategoryContainer
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingClickableItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingTextTitle
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.appearance.core.Msg
import ru.maksonic.beresta.screen.settings.appearance.ui.Send

/**
 * @Author maksonic on 14.07.2023
 */
@Composable
fun AnimationsSettingItem(send: Send, currentVelocityTitle: State<String>) {
    SettingCategoryContainer {

        SettingTextTitle(title = text.settingsAppearance.titleAnimations)

        settings(send, currentVelocityTitle).forEach { setting ->
            SettingClickableItem(setting)
        }
    }
}

@Composable
private fun settings(send: Send, currentVelocityTitle: State<String>) = listOf(
    SettingItem(
        title = text.settingsAppearance.itemAnimVelocity,
        rightPart = RightPart.CURRENT_VALUE,
        prefixIcon = AppIcon.Speed,
        valueHint = currentVelocityTitle.value,
        onClick = { send(Msg.Ui.OnAnimationsVelocityClicked) })
)
package ru.maksonic.beresta.screen.settings.appearance.ui.widget.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.appearance.core.Msg
import ru.maksonic.beresta.screen.settings.appearance.ui.SendMessage
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Speed
import ru.maksonic.beresta.ui.widget.button.settings.RightPart
import ru.maksonic.beresta.ui.widget.button.settings.SettingClickableItem
import ru.maksonic.beresta.ui.widget.button.settings.SettingItem
import ru.maksonic.beresta.ui.widget.surface.SettingContainer
import ru.maksonic.beresta.ui.widget.text.SettingTitle

/**
 * @Author maksonic on 14.07.2023
 */
@Composable
fun AnimationsSettingItem(send: SendMessage, currentVelocityTitle: State<String>) {
    SettingContainer {

        SettingTitle(title = text.settingsAppearance.titleAnimations)

        settings(send, currentVelocityTitle).forEach { setting ->
            SettingClickableItem(setting)
        }
    }
}

@Composable
private fun settings(send: SendMessage, currentVelocityTitle: State<String>) = listOf(
    SettingItem(
        rightPart = RightPart.CURRENT_VALUE,
        title = text.settingsAppearance.itemAnimVelocity,
        prefixIcon = AppIcon.Speed,
        valueHint = currentVelocityTitle.value,
        onClick = { send(Msg.Ui.OnAnimationsVelocityClicked) })
)
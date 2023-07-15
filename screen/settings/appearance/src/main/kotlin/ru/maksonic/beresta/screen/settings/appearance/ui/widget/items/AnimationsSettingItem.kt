package ru.maksonic.beresta.screen.settings.appearance.ui.widget.items

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.appearance.core.Msg
import ru.maksonic.beresta.screen.settings.appearance.ui.SendMessage
import ru.maksonic.beresta.ui.widget.button.settings.SettingClickableItem
import ru.maksonic.beresta.ui.widget.button.settings.SettingItem
import ru.maksonic.beresta.ui.widget.surface.SettingContainer
import ru.maksonic.beresta.ui.widget.text.SettingTitle

/**
 * @Author maksonic on 14.07.2023
 */
@Composable
fun AnimationsSettingItem(send: SendMessage) {
    SettingContainer {

        SettingTitle(title = text.settingsAppearance.titleAnimations)

        settings(send).forEach { setting ->
            SettingClickableItem(setting)
        }
    }
}

@Composable
private fun settings(send: SendMessage) = listOf(
    SettingItem(
        title = text.settingsAppearance.itemAnimVelocity,
        onClick = { send(Msg.Ui.OnAnimationsVelocityClicked) })
)
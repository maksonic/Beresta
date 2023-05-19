package ru.maksonic.beresta.screen.settings.ui.widget.item

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.Msg
import ru.maksonic.beresta.screen.settings.ui.SendMessage
import ru.maksonic.beresta.screen.settings.ui.widget.BaseSettingComponent
import ru.maksonic.beresta.screen.settings.ui.widget.SettingComponentItem
import ru.maksonic.beresta.screen.settings.ui.widget.SettingItem
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.UserAccount

/**
 * @Author maksonic on 20.02.2023
 */
@Composable
internal fun AccountSettingsItem(send: SendMessage) {
    val settings = listOf(SettingItem(
        title = text.settings.itemChangeAccount,
        prefixIcon = AppIcon.UserAccount,
        onClick = { send(Msg.Ui.OnUserAccountClicked) }
    ))
    val component = SettingComponentItem(title = text.settings.titleAccount, items = settings)

    BaseSettingComponent(component)
}
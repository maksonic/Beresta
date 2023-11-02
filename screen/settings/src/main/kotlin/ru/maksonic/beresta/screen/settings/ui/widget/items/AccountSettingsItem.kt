package ru.maksonic.beresta.screen.settings.ui.widget.items

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.UserAccount
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingComponentItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingItem
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.core.Msg
import ru.maksonic.beresta.screen.settings.ui.Send
import ru.maksonic.beresta.screen.settings.ui.widget.BaseSettingComponent

/**
 * @Author maksonic on 20.02.2023
 */
@Composable
internal fun AccountSettingsItem(send: Send) {
    val settings = listOf(SettingItem(
        title = text.settings.itemChangeAccount,
        prefixIcon = AppIcon.UserAccount,
        onClick = { send(Msg.Ui.OnUserAccountClicked) }
    ))
    val component = SettingComponentItem(title = text.settings.titleAccount, items = settings)

    BaseSettingComponent(component)
}
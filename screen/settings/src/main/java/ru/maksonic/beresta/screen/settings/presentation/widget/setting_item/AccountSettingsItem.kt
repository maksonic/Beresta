package ru.maksonic.beresta.screen.settings.presentation.widget.setting_item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.screen.settings.presentation.SendMessage
import ru.maksonic.beresta.screen.settings.Msg
import ru.maksonic.beresta.screen.settings.presentation.widget.SettingClickableItem
import ru.maksonic.beresta.screen.settings.presentation.widget.SettingTitle
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.UserAccount

/**
 * @Author maksonic on 20.02.2023
 */
@Composable
internal fun AccountSettingsItem(send: SendMessage, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth()) {
        SettingTitle(text.settings.titleAccount)
        SettingClickableItem(
            icon = AppIcon.UserAccount,
            title = text.settings.itemChangeAccount,
            action = { send(Msg.Ui.OnShowSelectLanguageSheetClicked) }
        )
    }
}
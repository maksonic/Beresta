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
import ru.maksonic.beresta.ui.theme.icons.Handshake
import ru.maksonic.beresta.ui.theme.icons.Info
import ru.maksonic.beresta.ui.theme.icons.PrivacyPolicy
import ru.maksonic.beresta.ui.theme.icons.Send

/**
 * @Author maksonic on 20.02.2023
 */
@Composable
fun SupportSettingsItem(send: SendMessage, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth()) {
        SettingTitle(text.settings.titleSupport)

        SettingClickableItem(
            icon = AppIcon.Send,
            title = text.settings.itemWriteMessage,
            action = { send(Msg.Ui.OnShowSelectLanguageSheetClicked) }
        )

        SettingClickableItem(
            icon = AppIcon.PrivacyPolicy,
            title = text.settings.itemPrivacy,
            action = { send(Msg.Ui.OnShowSelectLanguageSheetClicked) }
        )
        SettingClickableItem(
            icon = AppIcon.Handshake,
            title = text.settings.itemTermsOfUse,
            action = { send(Msg.Ui.OnShowSelectLanguageSheetClicked) }
        )
        SettingClickableItem(
            icon = AppIcon.Info,
            title = text.settings.itemAboutApp,
            action = { send(Msg.Ui.OnShowSelectLanguageSheetClicked) }
        )
    }
}
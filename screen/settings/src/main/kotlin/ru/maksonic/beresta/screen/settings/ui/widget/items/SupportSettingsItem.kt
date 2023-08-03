package ru.maksonic.beresta.screen.settings.ui.widget.items

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.core.Msg
import ru.maksonic.beresta.screen.settings.ui.SendMessage
import ru.maksonic.beresta.screen.settings.ui.widget.BaseSettingComponent
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Handshake
import ru.maksonic.beresta.ui.theme.icons.Info
import ru.maksonic.beresta.ui.theme.icons.PrivacyPolicy
import ru.maksonic.beresta.ui.theme.icons.Send
import ru.maksonic.beresta.ui.widget.button.settings.SettingComponentItem
import ru.maksonic.beresta.ui.widget.button.settings.SettingItem

/**
 * @Author maksonic on 20.02.2023
 */
@Composable
internal fun SupportSettingsItem(send: SendMessage) {
    val settings = listOf(
        SettingItem(
            title = text.settings.itemWriteMessage, prefixIcon = AppIcon.Send,
            onClick = { send(Msg.Ui.OnWriteEmailClicked) }
        ),
        SettingItem(
            title = text.settings.itemPrivacy, prefixIcon = AppIcon.PrivacyPolicy,
            onClick = { send(Msg.Ui.OnPrivacyPolicyClicked) }
        ),
        SettingItem(
            title = text.settings.itemTermsOfUse, prefixIcon = AppIcon.Handshake,
            onClick = { send(Msg.Ui.OnUserAgreementClicked) }
        ),
        SettingItem(
            title = text.settings.itemAboutApp, prefixIcon = AppIcon.Info,
            onClick = { send(Msg.Ui.OnAboutAppClicked) }
        ),
    )
    val component = SettingComponentItem(title = text.settings.titleSupport, items = settings)

    BaseSettingComponent(component)
}
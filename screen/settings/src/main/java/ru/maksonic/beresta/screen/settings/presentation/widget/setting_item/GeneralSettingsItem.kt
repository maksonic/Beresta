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
import ru.maksonic.beresta.ui.theme.icons.Language
import ru.maksonic.beresta.ui.theme.icons.SwitchTheme

/**
 * @Author maksonic on 20.02.2023
 */
@Composable
internal fun GeneralSettingsItem(
    send: SendMessage,
    themeTint: String,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth()) {
        SettingTitle(text.settings.titleGeneral)
        SettingClickableItem(
            icon = AppIcon.Language,
            title = text.settings.itemSwitchLanguage,
            hint = text.langTitle,
            action = { send(Msg.Ui.OnShowSelectLanguageSheetClicked) }
        )
        SettingClickableItem(
            icon = AppIcon.SwitchTheme,
            title = text.settings.itemSwitchTheme,
            hint = themeTint,
            action = { send(Msg.Ui.OnShowSelectThemeSheetClicked) }
        )
    }
}

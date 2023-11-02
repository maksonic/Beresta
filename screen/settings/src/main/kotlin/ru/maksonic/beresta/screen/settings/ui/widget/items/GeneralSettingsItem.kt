package ru.maksonic.beresta.screen.settings.ui.widget.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Appearance
import ru.maksonic.beresta.common.ui_kit.icons.Language
import ru.maksonic.beresta.common.ui_kit.icons.Security
import ru.maksonic.beresta.common.ui_kit.icons.ThemeContrast
import ru.maksonic.beresta.common.ui_kit.icons.ThemeLight
import ru.maksonic.beresta.common.ui_kit.icons.ThemeNight
import ru.maksonic.beresta.common.ui_kit.icons.notifications.Notifications
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.core.Msg
import ru.maksonic.beresta.screen.settings.ui.Send
import ru.maksonic.beresta.screen.settings.ui.widget.BaseSettingComponent
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.RightPart
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingComponentItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingItem
import ru.maksonic.beresta.common.ui_theme.AppThemeUi

/**
 * @Author maksonic on 20.02.2023
 */
@Composable
internal fun GeneralSettingsItem(send: Send, theme: AppThemeUi, isDarkTheme: Boolean) {
    val themeHint = rememberUpdatedState(
        when (theme) {
            AppThemeUi.SYSTEM -> text.settings.titleThemeSystem
            AppThemeUi.DAY -> text.settings.titleThemeLight
            AppThemeUi.NIGHT -> text.settings.themeTitleNight
            AppThemeUi.DARK -> text.settings.themeTitleDark
        }
    )

    val themePrefixIcon = rememberUpdatedState(
        when (theme) {
            AppThemeUi.SYSTEM -> if (isDarkTheme) AppIcon.ThemeNight else AppIcon.ThemeLight
            AppThemeUi.DAY -> AppIcon.ThemeLight
            AppThemeUi.NIGHT -> AppIcon.ThemeNight
            AppThemeUi.DARK -> AppIcon.ThemeContrast
        }
    )

    val settings = listOf(
        SettingItem(
            title = text.settings.itemSwitchLanguage,
            prefixIcon = AppIcon.Language,
            valueHint = text.langTitle,
            rightPart = RightPart.CURRENT_VALUE,
            onClick = { send(Msg.Ui.OnPickLanguageClicked) }
        ),
        SettingItem(
            title = text.settings.itemSwitchTheme,
            prefixIcon = themePrefixIcon.value,
            valueHint = themeHint.value,
            rightPart = RightPart.CURRENT_VALUE,
            onClick = { send(Msg.Ui.OnPickThemeClicked) }
        ),
        SettingItem(
            title = text.settingsAppearance.topBarTitle,
            prefixIcon = AppIcon.Appearance,
            onClick = { send(Msg.Ui.OnAppearanceClicked) }
        ),
        SettingItem(
            title = text.settingsNotifications.topBarTitle,
            prefixIcon = AppIcon.Notifications,
            onClick = { send(Msg.Ui.OnNotificationsClicked) }
        ),
        SettingItem(
            title = text.settingsSecurity.topBarTitle,
            prefixIcon = AppIcon.Security,
            onClick = { send(Msg.Ui.OnSecurityClicked) }
        )
    )

    val component = SettingComponentItem(title = text.settings.titleGeneral, items = settings)

    BaseSettingComponent(component)
}

package ru.maksonic.beresta.screen.settings.ui.widget.item

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.Msg
import ru.maksonic.beresta.screen.settings.ui.SendMessage
import ru.maksonic.beresta.screen.settings.ui.widget.BaseSettingComponent
import ru.maksonic.beresta.screen.settings.ui.widget.SettingComponentItem
import ru.maksonic.beresta.screen.settings.ui.widget.SettingItem
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Appearance
import ru.maksonic.beresta.ui.theme.icons.Language
import ru.maksonic.beresta.ui.theme.icons.ThemeContrast
import ru.maksonic.beresta.ui.theme.icons.ThemeLight
import ru.maksonic.beresta.ui.theme.icons.ThemeNight
import ru.maksonic.beresta.ui.theme.icons.UserAccount

/**
 * @Author maksonic on 20.02.2023
 */
@Composable
internal fun GeneralSettingsItem(send: SendMessage, theme: AppTheme, isDarkTheme: Boolean) {
    val themeHint = when (theme) {
        AppTheme.SYSTEM -> text.settings.titleThemeSystem
        AppTheme.LIGHT -> text.settings.titleThemeLight
        AppTheme.DARK -> text.settings.themeTitleNight
        AppTheme.HIGH_CONTRAST -> text.settings.themeTitleHighContrast
    }

    val themePrefixIcon = when (theme) {
        AppTheme.SYSTEM -> if (isDarkTheme) AppIcon.ThemeNight else AppIcon.ThemeLight
        AppTheme.LIGHT -> AppIcon.ThemeLight
        AppTheme.DARK -> AppIcon.ThemeNight
        AppTheme.HIGH_CONTRAST -> AppIcon.ThemeContrast
    }

    val settings = listOf(
        SettingItem(
            title = text.settings.itemSwitchLanguage,
            prefixIcon = AppIcon.Language,
            postfixHint = text.langTitle,
            onClick = { send(Msg.Ui.OnPickLanguageClicked) }
        ),
        SettingItem(
            title = text.settings.itemSwitchTheme,
            prefixIcon = themePrefixIcon,
            postfixHint = themeHint,
            onClick = { send(Msg.Ui.OnPickThemeClicked) }
        ),
        SettingItem(
            title = text.settings.itemAppearance,
            prefixIcon = AppIcon.Appearance,
            onClick = { send(Msg.Ui.OnAppearanceClicked) }
        )
    )
    val component = SettingComponentItem(title = text.settings.titleGeneral, items = settings)

    BaseSettingComponent(component)
}

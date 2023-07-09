package ru.maksonic.beresta.screen.settings.ui.widget.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.Msg
import ru.maksonic.beresta.screen.settings.ui.SendMessage
import ru.maksonic.beresta.screen.settings.ui.widget.BaseSettingComponent
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Appearance
import ru.maksonic.beresta.ui.theme.icons.Language
import ru.maksonic.beresta.ui.theme.icons.ThemeContrast
import ru.maksonic.beresta.ui.theme.icons.ThemeLight
import ru.maksonic.beresta.ui.theme.icons.ThemeNight
import ru.maksonic.beresta.ui.widget.button.settings.RightPart
import ru.maksonic.beresta.ui.widget.button.settings.SettingComponentItem
import ru.maksonic.beresta.ui.widget.button.settings.SettingItem

/**
 * @Author maksonic on 20.02.2023
 */
@Composable
internal fun GeneralSettingsItem(send: SendMessage, theme: AppTheme, isDarkTheme: Boolean) {
    val themeHint = rememberUpdatedState(
        when (theme) {
            AppTheme.SYSTEM -> text.settings.titleThemeSystem
            AppTheme.LIGHT -> text.settings.titleThemeLight
            AppTheme.DARK -> text.settings.themeTitleNight
            AppTheme.HIGH_CONTRAST -> text.settings.themeTitleHighContrast
        }
    )

    val themePrefixIcon = rememberUpdatedState(
        when (theme) {
            AppTheme.SYSTEM -> if (isDarkTheme) AppIcon.ThemeNight else AppIcon.ThemeLight
            AppTheme.LIGHT -> AppIcon.ThemeLight
            AppTheme.DARK -> AppIcon.ThemeNight
            AppTheme.HIGH_CONTRAST -> AppIcon.ThemeContrast
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
            title = text.settings.itemAppearance,
            prefixIcon = AppIcon.Appearance,
            onClick = { send(Msg.Ui.OnAppearanceClicked) }
        )
    )

    val component = SettingComponentItem(title = text.settings.titleGeneral, items = settings)

    BaseSettingComponent(component)
}

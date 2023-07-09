package ru.maksonic.beresta.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.core.ElmCommand
import ru.maksonic.beresta.elm.core.ElmEffect
import ru.maksonic.beresta.elm.core.ElmMessage
import ru.maksonic.beresta.elm.core.ElmModel
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import ru.maksonic.beresta.language_engine.shell.provider.BerestaLanguage
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.theme.color.PaletteStore
import ru.maksonic.beresta.ui.theme.component.AppDarkMode

/**
 * @Author maksonic on 18.02.2023
 */
@Stable
@Immutable
data class Model(
    val languageProvider: BerestaLanguage,
    val currentLanguage: AppLanguage,
    val currentTheme: AppTheme,
    val currentPalette: AppThemePalette,
    val themePalette: PaletteStore,
    val darkMode: AppDarkMode,
) : ElmModel {
    companion object {
        val Initial = Model(
            languageProvider = BerestaLanguage.Empty,
            currentLanguage = AppLanguage.RUSSIAN,
            currentTheme = AppTheme.SYSTEM,
            currentPalette = AppThemePalette.BLUE,
            themePalette = PaletteStore.Default,
            darkMode = AppDarkMode.Disabled
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Inner : Msg() {
        data class FetchedTheme(val theme: Pair<AppTheme, Boolean>) : Inner()
        data class FetchedThemePalette(val palette: PaletteStore) : Inner()
        data class FetchedLanguageProvider(val provider: BerestaLanguage) : Inner()
        data class UpdatedThemeDarkModeValue(val isDark: Boolean) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchThemeFromDataStore : Cmd()
    object FetchThemePaletteFromDataStore : Cmd()
    object FetchAppLangProvider : Cmd()
    data class UpdateDarkModeInDatastore(val isDarkMode: Boolean): Cmd()
}

sealed class Eff : ElmEffect
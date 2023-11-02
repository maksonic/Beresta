package ru.maksonic.beresta.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.common.ui_theme.AppAnimationVelocity
import ru.maksonic.beresta.common.ui_theme.AppDarkMode
import ru.maksonic.beresta.common.ui_theme.AppThemePaletteUi
import ru.maksonic.beresta.common.ui_theme.AppThemeUi
import ru.maksonic.beresta.common.ui_theme.ThemePalettesUiContainer
import ru.maksonic.beresta.common.ui_theme.ThemeUiContainer
import ru.maksonic.beresta.language_engine.shell.provider.AppLangUi
import ru.maksonic.beresta.language_engine.shell.provider.LanguageModel
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 18.02.2023
 */
@Stable
@Immutable
data class Model(
    val currentTheme: AppThemeUi,
    val currentPalette: AppThemePaletteUi,
    val paletteContainer: ThemePalettesUiContainer,
    val darkMode: AppDarkMode,
    val currentLanguage: AppLangUi,
    val languageProvider: LanguageModel,
    val animationVelocity: AppAnimationVelocity.Key,
) : ElmModel {
    companion object {
        val Initial = Model(
            currentTheme = AppThemeUi.SYSTEM,
            currentPalette = AppThemePaletteUi.BLUE,
            paletteContainer = ThemePalettesUiContainer.Default,
            darkMode = AppDarkMode.Disabled,
            currentLanguage = AppLangUi.RUSSIAN,
            languageProvider = LanguageModel.Default,
            animationVelocity = AppAnimationVelocity.Key.NORMAL
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Inner : Msg() {
        data class FetchedThemeContainer(val data: ThemeUiContainer) : Inner()
        data class FetchedLanguageProvider(val provider: LanguageModel) : Inner()
        data class FetchedAnimationsVelocity(val key: AppAnimationVelocity.Key) : Inner()
        data class UpdatedThemeDarkModeValue(val isDark: Boolean) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchAppTheme : Cmd()
    data object FetchAppLangProvider : Cmd()
    data object FetchAnimationVelocity : Cmd()
    data class UpdateThemeDarkMode(val isDarkMode: Boolean): Cmd()
}

sealed class Eff : ElmEffect
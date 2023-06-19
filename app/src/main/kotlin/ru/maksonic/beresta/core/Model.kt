package ru.maksonic.beresta.core

import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import ru.maksonic.beresta.language_engine.shell.provider.BerestaLanguage
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.PaletteStore

/**
 * @Author maksonic on 18.02.2023
 */
data class Model(
    val theme: AppTheme,
    val themePalette: PaletteStore,
    val language: AppLanguage,
    val languageProvider: BerestaLanguage
) : ElmModel {

    companion object {
        val Initial = Model(
            theme = AppTheme.SYSTEM,
            themePalette = PaletteStore.Default,
            language = AppLanguage.RUSSIAN,
            languageProvider = BerestaLanguage()
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {

    }

    sealed class Inner : Msg() {
        data class SetAppLanguage(val language: AppLanguage) : Inner()
        data class SetAppTheme(val theme: AppTheme) : Inner()
        data class SetAppThemePalette(val palette: PaletteStore) : Inner()
        data class FetchedLanguageForProvide(val lang: BerestaLanguage) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object ReadLanguageFromDataStore : Cmd()
    object ReadThemeFromDataStore : Cmd()
    object ReadThemePaletteFromDataStore : Cmd()
    object FetchAppLanguage : Cmd()
}

sealed class Eff : ElmEffect {

}
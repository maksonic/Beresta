package ru.maksonic.beresta.core

import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.language_selector.api.provider.AppLanguage
import ru.maksonic.beresta.feature.language_selector.api.provider.BerestaLanguage
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 18.02.2023
 */
data class Model(
    val theme: AppTheme = AppTheme.SYSTEM,
    val language: AppLanguage = AppLanguage.RUSSIAN,
    val languageProvider: BerestaLanguage = BerestaLanguage()
): ElmModel

sealed class Msg: ElmMessage {
    sealed class Ui : Msg() {

    }

    sealed class Inner : Msg() {
        data class SetAppLanguage(val language: AppLanguage): Inner()
        data class SetAppTheme(val theme: AppTheme): Inner()
        data class FetchedLanguageForProvide(val lang: BerestaLanguage): Inner()
    }
}

sealed class Cmd : ElmCommand {
    object ReadLanguageFromDataStore : Cmd()
    object ReadThemeFromDataStore : Cmd()
    object FetchAppLanguage : Cmd()
}

sealed class Eff : ElmEffect {

}
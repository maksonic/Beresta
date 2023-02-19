package ru.maksonic.beresta.core

import kotlinx.coroutines.flow.collectLatest
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.language_selector.api.provider.LanguageProvider
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorApi

/**
 * @Author maksonic on 18.02.2023
 */
class MainProgram(
    private val themeSelector: ThemeSelectorApi,
    private val languageSelector: LanguageSelectorApi.Lang,
    private val languageProvider: LanguageProvider,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.ReadLanguageFromDataStore -> readLanguageFromDatastore(consumer)
            is Cmd.ReadThemeFromDataStore -> readThemeFromDatastore(consumer)
            is Cmd.FetchAppLanguage -> fetchAppLanguage(consumer)
        }
    }

    private suspend fun readLanguageFromDatastore(consumer: (Msg) -> Unit) {
        languageSelector.currentLanguage.collectLatest { savedAppLanguage ->
            consumer(Msg.Inner.SetAppLanguage(savedAppLanguage))
        }
    }

    private suspend fun readThemeFromDatastore(consumer: (Msg) -> Unit) {
        themeSelector.currentTheme.collectLatest { savedAppTheme ->
            consumer(Msg.Inner.SetAppTheme(savedAppTheme))
        }
    }

    private suspend fun fetchAppLanguage(consumer: (Msg) -> Unit) {
        languageSelector.currentLanguage.collectLatest { appLang ->
            languageProvider.provideLanguage(appLang).collect { lang ->
                consumer(Msg.Inner.FetchedLanguageForProvide(lang))
            }
        }
    }
}
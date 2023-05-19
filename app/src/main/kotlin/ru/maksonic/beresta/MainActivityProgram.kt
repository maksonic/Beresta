package ru.maksonic.beresta

import kotlinx.coroutines.flow.collectLatest
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi
import ru.maksonic.beresta.language_engine.shell.provider.LanguageProvider

/**
 * @Author maksonic on 18.02.2023
 */
class MainActivityProgram(
    private val theme: ThemePickerApi.Theme,
    private val palette: ThemePickerApi.Palette,
    private val language: LanguageEngineApi,
    private val languageProvider: LanguageProvider
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.ReadLanguageFromDataStore -> readLanguageFromDatastore(consumer)
            is Cmd.ReadThemeFromDataStore -> readThemeFromDatastore(consumer)
            is Cmd.FetchAppLanguage -> fetchAppLanguage(consumer)
            is Cmd.ReadThemePaletteFromDataStore -> readThemePaletteFromDatastore(consumer)
        }
    }

    private suspend fun readLanguageFromDatastore(consumer: (Msg) -> Unit) {
        language.current.collectLatest { savedAppLanguage ->
            consumer(Msg.Inner.SetAppLanguage(savedAppLanguage))
        }
    }

    private suspend fun readThemeFromDatastore(consumer: (Msg) -> Unit) {
        theme.current.collectLatest { savedAppTheme ->
            consumer(Msg.Inner.SetAppTheme(savedAppTheme))
        }
    }

    private suspend fun fetchAppLanguage(consumer: (Msg) -> Unit) {
        language.current.collectLatest { appLang ->
            languageProvider.provideLanguage(appLang).collect { lang ->
                consumer(Msg.Inner.FetchedLanguageForProvide(lang))
            }
        }
    }

    private suspend fun readThemePaletteFromDatastore(consumer: (Msg) -> Unit) {
        palette.current.collectLatest { savedLightAndDarkPalette ->
            consumer(Msg.Inner.SetAppThemePalette(savedLightAndDarkPalette))
        }
    }
}
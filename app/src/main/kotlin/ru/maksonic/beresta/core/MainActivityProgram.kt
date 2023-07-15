package ru.maksonic.beresta.core

import kotlinx.coroutines.flow.collectLatest
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi
import ru.maksonic.beresta.language_engine.shell.provider.LanguageProvider
import ru.maksonic.beresta.screen.settings.appearance.core.AnimationVelocity

/**
 * @Author maksonic on 18.02.2023
 */
class MainActivityProgram(
    private val themeFeatureApi: ThemePickerApi.Feature.Theme,
    private val paletteFeatureApi: ThemePickerApi.Feature.Palette,
    private val languageEngineApi: LanguageEngineApi,
    private val languageProvider: LanguageProvider,
    private val animationVelocity: AnimationVelocity,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchThemeFromDataStore -> fetchThemeFromDatastore(consumer)
            is Cmd.FetchThemePaletteFromDataStore -> fetchThemePaletteFromDatastore(consumer)
            is Cmd.FetchAppLangProvider -> fetchAppLangProvider(consumer)
            is Cmd.UpdateDarkModeInDatastore -> updateDarkModeValue(cmd.isDarkMode)
            is Cmd.FetchAnimationVelocity -> fetchAnimationVelocity(consumer)
        }
    }

    private suspend fun fetchThemeFromDatastore(consumer: (Msg) -> Unit) =
        themeFeatureApi.current.collect { current ->
            consumer(Msg.Inner.FetchedTheme(current))
        }

    private suspend fun fetchThemePaletteFromDatastore(consumer: (Msg) -> Unit) =
        paletteFeatureApi.current.collectLatest { savedLightAndDarkPalette ->
            consumer(Msg.Inner.FetchedThemePalette(savedLightAndDarkPalette))
        }

    private suspend fun fetchAppLangProvider(consumer: (Msg) -> Unit) =
        languageEngineApi.current.collectLatest { language ->
            languageProvider.provideLanguage(language).collect { provider ->
                consumer(Msg.Inner.FetchedLanguageProvider(provider))
            }
        }

    private suspend fun updateDarkModeValue(isDarkMode: Boolean) {
        themeFeatureApi.updateDarkMode(isDarkMode)
    }

    private suspend fun fetchAnimationVelocity(consumer: (Msg) -> Unit) =
        animationVelocity.currentCacheVelocity.collect {
            consumer(Msg.Inner.FetchedAnimationsVelocity(it.current))
        }
}
package ru.maksonic.beresta.core

import kotlinx.coroutines.flow.collectLatest
import ru.maksonic.beresta.common.ui_theme.AppThemeUi
import ru.maksonic.beresta.common.ui_theme.ThemeUiContainer
import ru.maksonic.beresta.feature.app_lang.domain.mapper.AppLangMapper
import ru.maksonic.beresta.feature.app_lang.domain.usecase.FetchAppLangUseCase
import ru.maksonic.beresta.feature.app_theme.domain.mapper.AppThemeContainerMapper
import ru.maksonic.beresta.feature.app_theme.domain.usecase.AppThemeInteractor
import ru.maksonic.beresta.feature.app_theme.domain.usecase.FetchAppThemeContainerUseCase
import ru.maksonic.beresta.feature.ui.language_picker.api.LanguageUi
import ru.maksonic.beresta.language_engine.shell.provider.AppLangUi
import ru.maksonic.beresta.language_engine.shell.provider.LanguageProvider
import ru.maksonic.beresta.platform.core.ui.AnimationVelocity
import ru.maksonic.beresta.platform.elm.core.ElmProgram

/**
 * @Author maksonic on 18.02.2023
 */
class MainActivityProgram(
    private val fetchAppThemeContainerUseCase: FetchAppThemeContainerUseCase,
    private val appThemeInteractor: AppThemeInteractor,
    private val themeMapper: AppThemeContainerMapper<ThemeUiContainer, AppThemeUi>,
    private val animationVelocityApi: AnimationVelocity,
    private val fetchAppLangUseCase: FetchAppLangUseCase,
    private val languageMapper: AppLangMapper<LanguageUi, AppLangUi>,
    private val languageProvider: LanguageProvider
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchAppTheme -> fetchAppTheme(consumer)
            is Cmd.FetchAppLangProvider -> fetchAppLangProvider(consumer)
            is Cmd.FetchAnimationVelocity -> fetchAnimationVelocity(consumer)
            is Cmd.UpdateThemeDarkMode -> updateThemeDarkMode(cmd.isDarkMode)
        }
    }

    private suspend fun fetchAppTheme(consumer: (Msg) -> Unit) =
        fetchAppThemeContainerUseCase().collect { container ->
            consumer(Msg.Inner.FetchedThemeContainer(themeMapper.mapTo(container)))
        }

    private suspend fun fetchAppLangProvider(consumer: (Msg) -> Unit) {
        fetchAppLangUseCase().collectLatest { languageDomain ->
            val language = languageMapper.onlyThemeToUi(languageDomain)
            languageProvider.provideLanguage(language).collectLatest { provider ->
                consumer(Msg.Inner.FetchedLanguageProvider(provider))
            }
        }
    }

    private suspend fun fetchAnimationVelocity(consumer: (Msg) -> Unit) =
        animationVelocityApi.current.collect {
            consumer(Msg.Inner.FetchedAnimationsVelocity(it.current))
        }

    private suspend fun updateThemeDarkMode(isDarkMode: Boolean) =
        appThemeInteractor.updateDarkMode(isDarkMode)
}
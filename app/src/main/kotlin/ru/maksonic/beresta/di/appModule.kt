package ru.maksonic.beresta.di

import android.app.Activity
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.core.CoroutineDispatchers
import ru.maksonic.beresta.common.ui_theme.AppThemePaletteUi
import ru.maksonic.beresta.common.ui_theme.AppThemeUi
import ru.maksonic.beresta.common.ui_theme.ThemeUiContainer
import ru.maksonic.beresta.core.MainActivityProgram
import ru.maksonic.beresta.core.MainActivitySandbox
import ru.maksonic.beresta.feature.app_lang.domain.mapper.AppLangMapper
import ru.maksonic.beresta.feature.app_theme.domain.mapper.AppThemeContainerMapper
import ru.maksonic.beresta.feature.app_theme.domain.mapper.AppThemePaletteMapper
import ru.maksonic.beresta.feature.ui.language_picker.api.LanguageUi
import ru.maksonic.beresta.language_engine.shell.provider.AppLangUi
import ru.maksonic.beresta.mapper.AppLangMapperImplApp
import ru.maksonic.beresta.mapper.AppThemeContainerMapperImpl
import ru.maksonic.beresta.mapper.AppThemePaletteMapperImpl

/**
 * @Author maksonic on 18.06.2023
 */
internal val appModule = module {
    factory { (activity: Activity) -> activity.window }

    factory<AppLangMapper<LanguageUi, AppLangUi>> { AppLangMapperImplApp() }
    factory<AppThemeContainerMapper<ThemeUiContainer, AppThemeUi>> { AppThemeContainerMapperImpl() }
    factory<AppThemePaletteMapper<AppThemePaletteUi>> { AppThemePaletteMapperImpl() }
    single(named(CoroutineDispatchers.IO)) { Dispatchers.IO }
    single(named(CoroutineDispatchers.DEFAULT)) { Dispatchers.Default }
    single(named(CoroutineDispatchers.MAIN)) { Dispatchers.Main }
    single {
        MainActivityProgram(
            fetchAppThemeContainerUseCase = get(),
            appThemeInteractor = get(),
            themeMapper = get(),
            animationVelocityApi = get(),
            fetchAppLangUseCase = get(),
            languageMapper = get(),
            languageProvider = get(),
        )
    }
    viewModel { MainActivitySandbox(mainActivityProgram = get()) }
}
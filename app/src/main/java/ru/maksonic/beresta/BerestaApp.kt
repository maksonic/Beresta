package ru.maksonic.beresta

import android.app.Application
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.core.CoroutineDispatchers
import ru.maksonic.beresta.core.MainActivitySandbox
import ru.maksonic.beresta.core.MainProgram
import ru.maksonic.beresta.core.converter.AssetsReader
import ru.maksonic.beresta.core.converter.JsonConverter
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.language_selector.api.provider.LanguageProvider
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi
import ru.maksonic.beresta.feature.language_selector.core.LanguageJsonToDataConverter
import ru.maksonic.beresta.feature.language_selector.core.LanguageProviderImpl
import ru.maksonic.beresta.feature.language_selector.core.LanguageSelectorCore
import ru.maksonic.beresta.feature.language_selector.core.LanguageSelectorViewModel
import ru.maksonic.beresta.feature.language_selector.core.ui.SelectAppLanguageSheet
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.onboarding.core.data.OnboardingVisibilityDatastore
import ru.maksonic.beresta.feature.onboarding.core.data.OnboardingRepository
import ru.maksonic.beresta.feature.onboarding.core.presentation.OnboardingSandbox
import ru.maksonic.beresta.feature.onboarding.core.presentation.Program
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.OnboardingScreen
import ru.maksonic.beresta.feature.splash_screen.api.SplashApi
import ru.maksonic.beresta.feature.splash_screen.core.BerestaSplashScreen
import ru.maksonic.beresta.feature.splash_screen.core.SplashViewModel
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorApi
import ru.maksonic.beresta.feature.theme_selector.core.ThemeSelectorCore
import ru.maksonic.beresta.navigation.graph_builder.FeatureApiStore
import ru.maksonic.beresta.navigation.graph_builder.GraphBuilder
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator
import ru.maksonic.beresta.screen.settings.core.SettingsProgram
import ru.maksonic.beresta.screen.settings.core.SettingsSandbox

/**
 * @Author maksonic on 15.12.2022
 */
class BerestaApp : Application() {

    //General modules
    private val appModule = module {
        single {
            MainProgram(
                themeSelector = get(),
                languageSelector = get(),
                languageProvider = get()
            )
        }
        viewModel { MainActivitySandbox(mainActivityProgram = get()) }
    }

    private val coreModule = module {
        single(named(CoroutineDispatchers.IO)) { Dispatchers.IO }
        single(named(CoroutineDispatchers.DEFAULT)) { Dispatchers.Default }
        single(named(CoroutineDispatchers.MAIN)) { Dispatchers.Main }
        single { Json { ignoreUnknownKeys = true } }
        single { Gson() }
        single<AssetsReader> { AssetsReader.Reader(androidContext()) }
        single<JsonConverter> { JsonConverter.Converter(assetsReader = get()) }
    }

    private val dataModule = module {
        single<Datastore> { Datastore.Core(androidContext()) }
    }

    private val navigationModule = module {
        single { FeatureApiStore(splash = get(), onboarding = get()) }
        single<GraphBuilder> { GraphBuilder.Builder(navigator = get(), apiStore = get()) }
        single { AppNavigator() }
    }

    //Feature modules
    private val splashScreenModule = module {
        single<SplashApi> { BerestaSplashScreen() }
        viewModel { SplashViewModel(onboardingVisibility = get()) }
    }

    private val onboardingFeatureModule = module {
        single<OnboardingApi.Visibility> { OnboardingVisibilityDatastore(datastore = get()) }
        single<OnboardingApi.Ui> { OnboardingScreen() }
        single { OnboardingRepository }
        single { Program(repository = get(), onboardingVisibility = get()) }
        viewModel { OnboardingSandbox(program = get()) }
    }

    private val themeSelectorFeatureModule = module {
        single<ThemeSelectorApi> { ThemeSelectorCore(datastore = get()) }
    }

    private val languageSelectorFeatureModule = module {
        viewModel { LanguageSelectorViewModel(selector = get()) }
        single<LanguageSelectorApi.Ui> { SelectAppLanguageSheet() }
        single<LanguageSelectorApi.Lang> { LanguageSelectorCore(datastore = get()) }
        single<LanguageProvider> { LanguageProviderImpl(langConverter = get()) }
        single {
            LanguageJsonToDataConverter(
                gson = get(),
                jsonConverter = get(),
                dispatcher = get(named(CoroutineDispatchers.IO))
            )
        }
    }

    private val settingsModule = module {
        single { SettingsProgram(themeSelector = get()) }
        viewModel { SettingsSandbox(program = get()) }
    }

    private val modules = listOf(
        appModule,
        settingsModule,
        coreModule,
        dataModule,
        splashScreenModule,
        navigationModule,
        onboardingFeatureModule,
        themeSelectorFeatureModule,
        languageSelectorFeatureModule

    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BerestaApp)
            modules(modules)
        }
    }
}
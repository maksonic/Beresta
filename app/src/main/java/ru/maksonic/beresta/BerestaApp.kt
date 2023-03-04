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
import ru.maksonic.beresta.core.MainActivityProgram
import ru.maksonic.beresta.core.MainActivitySandbox
import ru.maksonic.beresta.core.ResourceProvider
import ru.maksonic.beresta.core.converter.AssetsReader
import ru.maksonic.beresta.core.converter.JsonConverter
import ru.maksonic.beresta.core.domain.DateFormatter
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.data.database.databaseModule
import ru.maksonic.beresta.data.notes.NotesRepositoryImpl
import ru.maksonic.beresta.data.notes.cache.NoteCacheMapper
import ru.maksonic.beresta.data.notes.cache.NotesCacheDataSource
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.edit_note.core.fab.core.AddNoteSandbox
import ru.maksonic.beresta.feature.edit_note.core.fab.ui.AddNoteFabWidget
import ru.maksonic.beresta.feature.edit_note.core.screen.core.EditNoteSandbox
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi
import ru.maksonic.beresta.feature.language_selector.api.provider.LanguageProvider
import ru.maksonic.beresta.feature.language_selector.core.LanguageJsonToDataConverter
import ru.maksonic.beresta.feature.language_selector.core.LanguageProviderImpl
import ru.maksonic.beresta.feature.language_selector.core.LanguageSelectorCore
import ru.maksonic.beresta.feature.language_selector.core.presentation.LanguageSelectorViewModel
import ru.maksonic.beresta.feature.language_selector.core.presentation.ui.SelectAppLanguageSheet
import ru.maksonic.beresta.feature.notes_list.api.NotesListApi
import ru.maksonic.beresta.feature.notes_list.api.domain.NotesRepository
import ru.maksonic.beresta.feature.notes_list.api.domain.usecase.FetchNoteByIdUseCase
import ru.maksonic.beresta.feature.notes_list.api.domain.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.core.presentation.NotesListSandbox
import ru.maksonic.beresta.feature.notes_list.core.presentation.ui.NotesListWidget
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.onboarding.core.data.OnboardingRepository
import ru.maksonic.beresta.feature.onboarding.core.data.OnboardingVisibilityDatastore
import ru.maksonic.beresta.feature.onboarding.core.presentation.OnboardingSandbox
import ru.maksonic.beresta.feature.onboarding.core.presentation.Program
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.OnboardingScreen
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.core.presentation.SearchBarSandbox
import ru.maksonic.beresta.feature.search_bar.core.presentation.ui.SearchBarWidget
import ru.maksonic.beresta.feature.splash_screen.api.SplashApi
import ru.maksonic.beresta.feature.splash_screen.core.BerestaSplashScreen
import ru.maksonic.beresta.feature.splash_screen.core.SplashViewModel
import ru.maksonic.beresta.feature.theme_selector.api.SystemThemeCheckerApi
import ru.maksonic.beresta.feature.theme_selector.api.ThemePaletteSelectorApi
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorApi
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorUiApi
import ru.maksonic.beresta.feature.theme_selector.core.SystemThemeCheckerCore
import ru.maksonic.beresta.feature.theme_selector.core.ThemePaletteSelectorCore
import ru.maksonic.beresta.feature.theme_selector.core.ThemeSelectorCore
import ru.maksonic.beresta.feature.theme_selector.core.presentation.ThemeSelectorViewModel
import ru.maksonic.beresta.feature.theme_selector.core.presentation.ui.ThemeSelectorBottomSheet
import ru.maksonic.beresta.navigation.graph_builder.FeatureApiStore
import ru.maksonic.beresta.navigation.graph_builder.GraphBuilder
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator
import ru.maksonic.beresta.screen.main.presentation.core.MainProgram
import ru.maksonic.beresta.screen.main.presentation.core.MainSandbox
import ru.maksonic.beresta.screen.settings.SettingsProgram
import ru.maksonic.beresta.screen.settings.SettingsSandbox

/**
 * @Author maksonic on 15.12.2022
 */
class BerestaApp : Application() {

    //General modules
    private val appModule = module {
        single {
            MainActivityProgram(
                themeSelector = get(),
                paletteSelector = get(),
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
        single<AssetsReader> { AssetsReader.Core(androidContext()) }
        single<JsonConverter> { JsonConverter.Core(assetsReader = get()) }
        single<ResourceProvider> { ResourceProvider.Core(androidContext()) }
        single { DateFormatter }
    }

    private val dataModule = module {
        single<Datastore> { Datastore.Core(androidContext()) }
    }

    private val navigationModule = module {
        single { FeatureApiStore(splash = get(), onboarding = get()) }
        single<GraphBuilder> { GraphBuilder.Core(navigator = get(), apiStore = get()) }
        single { AppNavigator() }
    }

    //Feature modules
    private val splashScreenModule = module {
        single<SplashApi> { BerestaSplashScreen() }
        viewModel { SplashViewModel(onboardingVisibility = get()) }
    }

    private val mainScreenModule = module {
        single { MainProgram(fetchingUseCase = get(), mapper = get()) }
        viewModel { MainSandbox(mainProgram = get()) }
    }

    private val onboardingFeatureModule = module {
        single<OnboardingApi.Visibility> { OnboardingVisibilityDatastore(datastore = get()) }
        single<OnboardingApi.Ui> { OnboardingScreen() }
        single { OnboardingRepository }
        single { Program(repository = get(), onboardingVisibility = get()) }
        viewModel { OnboardingSandbox(program = get()) }
    }

    private val themeSelectorFeatureModule = module {
        viewModel {
            ThemeSelectorViewModel(
                themeSelector = get(),
                paletteSelector = get(),
                themeChecker = get()
            )
        }
        single<SystemThemeCheckerApi> { SystemThemeCheckerCore() }
        single<ThemeSelectorApi> { ThemeSelectorCore(datastore = get()) }
        single<ThemePaletteSelectorApi> { ThemePaletteSelectorCore(datastore = get()) }
        single<ThemeSelectorUiApi> { ThemeSelectorBottomSheet() }
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

    private val notesListFeatureModule = module {
        single<NotesListApi.Ui> { NotesListWidget() }
        single { NoteUiMapper(dateFormatter = get()) }
        viewModel { NotesListSandbox() }

    }
    private val notesListFeatureDataModule = module {
        single { NoteCacheMapper() }
        single {
            NotesCacheDataSource(
                noteDao = get(),
                dispatcher = get(named(CoroutineDispatchers.IO))
            )
        }
        single<NotesRepository> { NotesRepositoryImpl(cache = get(), mapper = get()) }
        single { FetchNotesUseCase(repository = get()) }
        single { FetchNoteByIdUseCase(repository = get()) }
    }

    private val searchBarFeatureModule = module {
        viewModel { SearchBarSandbox() }
        single<SearchBarApi.Ui> { SearchBarWidget() }
    }

    private val editNoteFeatureModule = module {
        viewModel { EditNoteSandbox() }
        viewModel { AddNoteSandbox() }
        single<EditNoteApi.Ui> { AddNoteFabWidget() }
    }

    private val modules = listOf(
        appModule,
        settingsModule,
        coreModule,
        dataModule,
        databaseModule,
        splashScreenModule,
        mainScreenModule,
        navigationModule,
        onboardingFeatureModule,
        themeSelectorFeatureModule,
        languageSelectorFeatureModule,
        notesListFeatureModule,
        notesListFeatureDataModule,
        searchBarFeatureModule,
        editNoteFeatureModule
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
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
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.data.database.databaseModule
import ru.maksonic.beresta.data.notes.NotesRepositoryImpl
import ru.maksonic.beresta.data.notes.cache.NoteCacheMapper
import ru.maksonic.beresta.data.notes.cache.NotesCacheDataSource
import ru.maksonic.beresta.data.notes_folders.NotesFoldersRepositoryImpl
import ru.maksonic.beresta.data.notes_folders.cache.FoldersCacheDataSource
import ru.maksonic.beresta.data.notes_folders.cache.NoteFolderCacheMapper
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.edit_note.api.domain.RefactorNoteInteractor
import ru.maksonic.beresta.feature.edit_note.core.fab.core.AddNoteSandbox
import ru.maksonic.beresta.feature.edit_note.core.fab.ui.AddNoteFabWidget
import ru.maksonic.beresta.feature.edit_note.core.screen.core.EditNoteProgram
import ru.maksonic.beresta.feature.edit_note.core.screen.core.EditNoteSandbox
import ru.maksonic.beresta.feature.folders_list.api.domain.FetchFolderByIdUseCase
import ru.maksonic.beresta.feature.folders_list.api.domain.FetchFoldersListUseCase
import ru.maksonic.beresta.feature.folders_list.api.domain.NotesFoldersInteractor
import ru.maksonic.beresta.feature.folders_list.api.domain.NotesFoldersRepository
import ru.maksonic.beresta.feature.folders_list.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.folders_list.api.ui.NoteFolderToChipMapper
import ru.maksonic.beresta.feature.folders_list.core.FoldersListUiCore
import ru.maksonic.beresta.feature.folders_list.core.dialog.core.CreateNewFolderDialogSandbox
import ru.maksonic.beresta.feature.folders_list.core.dialog.core.NewFolderDialogProgram
import ru.maksonic.beresta.feature.folders_list.core.screen.core.FoldersListProgram
import ru.maksonic.beresta.feature.folders_list.core.screen.core.FoldersScreenSandbox
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi
import ru.maksonic.beresta.feature.language_selector.api.provider.LanguageProvider
import ru.maksonic.beresta.feature.language_selector.core.LanguageJsonToDataConverter
import ru.maksonic.beresta.feature.language_selector.core.LanguageProviderImpl
import ru.maksonic.beresta.feature.language_selector.core.LanguageSelectorCore
import ru.maksonic.beresta.feature.language_selector.core.presentation.LanguageSelectorViewModel
import ru.maksonic.beresta.feature.language_selector.core.presentation.ui.SelectAppLanguageSheet
import ru.maksonic.beresta.feature.note_wallpaper_selector.api.NoteWallpaperSelectorApi
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.data.WallpapersRepository
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.NoteWallpaperSelectorUiCore
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.core.WallpaperSelectorProgram
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.core.WallpaperSelectorSandbox
import ru.maksonic.beresta.feature.notes_list.api.domain.NoteDateFormatter
import ru.maksonic.beresta.feature.notes_list.api.domain.NotesRepository
import ru.maksonic.beresta.feature.notes_list.api.domain.usecase.FetchNoteByIdUseCase
import ru.maksonic.beresta.feature.notes_list.api.domain.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.notes_list.core.presentation.ui.NotesListUiCore
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.onboarding.core.data.OnboardingVisibilityDatastore
import ru.maksonic.beresta.feature.onboarding.core.presentation.OnboardingSandbox
import ru.maksonic.beresta.feature.onboarding.core.presentation.OnboardingProgram
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.OnboardingScreen
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.core.presentation.SearchBarSandbox
import ru.maksonic.beresta.feature.search_bar.core.presentation.ui.SearchBarWidget
import ru.maksonic.beresta.feature.selected_items_counter_panel.api.SelectedItemsPanelUiApi
import ru.maksonic.beresta.feature.selected_items_counter_panel.core.SelectedItemsPanelUiCore
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
                languageProvider = get(),
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
    }

    private val dataModule = module {
        single<Datastore> { Datastore.Core(androidContext()) }
    }

    private val navigationModule = module {
        single { FeatureApiStore(splash = get(), onboarding = get(), foldersList = get()) }
        single<GraphBuilder> { GraphBuilder.Core(navigator = get(), apiStore = get()) }
        single { AppNavigator() }
    }

    //Feature modules
    private val splashScreenModule = module {
        single<SplashApi> { BerestaSplashScreen() }
        viewModel { SplashViewModel(onboardingVisibility = get()) }
    }

    private val mainScreenModule = module {
        single {
            MainProgram(
                fetchNotesUseCase = get(),
                fetchFoldersUseCase = get(),
                notesInteractor = get(),
                notesMapper = get(),
                foldersMapper = get(),
                dateFormatter = get(),
                languageSelector = get()
            )
        }
        viewModel { MainSandbox(mainProgram = get()) }
    }

    private val onboardingFeatureModule = module {
        single<OnboardingApi.Visibility> { OnboardingVisibilityDatastore(datastore = get()) }
        single<OnboardingApi.Ui> { OnboardingScreen() }
        single { OnboardingProgram(onboardingVisibility = get()) }
        viewModel { OnboardingSandbox(onboardingProgram = get()) }
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
        single<NoteDateFormatter> { NoteDateFormatter.Core() }
        single<NotesListApi.Ui> { NotesListUiCore() }
        single { NoteUiMapper() }
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
        single<RefactorNoteInteractor> { RefactorNoteInteractor.Impl(repository = get()) }
        single {
            EditNoteProgram(
                interactor = get(),
                fetchNoteByIdUseCase = get(),
                mapper = get(),
                navigator = get()
            )
        }
        viewModel { EditNoteSandbox(program = get()) }
        viewModel { AddNoteSandbox() }
        single<EditNoteApi.Ui> { AddNoteFabWidget() }
    }

    private val noteWallpaperSelectorModule = module {
        single { WallpapersRepository }
        single { WallpaperSelectorProgram(repository = get()) }
        viewModel { WallpaperSelectorSandbox(program = get()) }
        single<NoteWallpaperSelectorApi> { NoteWallpaperSelectorUiCore() }
    }

    private val foldersListFeatureModule = module {
        single { NoteFolderCacheMapper() }
        single {
            FoldersCacheDataSource(
                folderDao = get(),
                dispatcher = get(named(CoroutineDispatchers.IO))
            )
        }
        single { NoteCacheMapper() }
        single<NotesFoldersRepository> { NotesFoldersRepositoryImpl(cache = get(), mapper = get()) }
        single<NotesFoldersInteractor> { NotesFoldersInteractor.Impl(repository = get()) }
        single { FetchFoldersListUseCase(repository = get()) }
        single { FetchFolderByIdUseCase(repository = get()) }
        single { NoteFolderToChipMapper() }
        //dialog
        single {
            NewFolderDialogProgram(
                fetchFolderByIdUseCase = get(),
                interactor = get(),
                mapper = get()
            )
        }
        viewModel { CreateNewFolderDialogSandbox(program = get()) }
        //screen
        single {
            FoldersListProgram(
                fetchFoldersUseCase = get(),
                mapper = get(),
                foldersInteractor = get()
            )
        }
        viewModel { FoldersScreenSandbox(program = get()) }
        single<FoldersListApi.Ui> { FoldersListUiCore() }
    }

    private val selectedItemsPanelFeatureModule = module {
        single<SelectedItemsPanelUiApi> { SelectedItemsPanelUiCore() }
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
        editNoteFeatureModule,
        noteWallpaperSelectorModule,
        foldersListFeatureModule,
        selectedItemsPanelFeatureModule
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
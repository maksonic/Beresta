package ru.maksonic.beresta

import android.app.Application
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.core.CoroutineDispatchers
import ru.maksonic.beresta.core.MutableSharedState
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.data.database.databaseModule
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelSharedState
import ru.maksonic.beresta.feature.botom_panel.api.PanelSharedState
import ru.maksonic.beresta.feature.botom_panel.ui.BottomPanelWidget
import ru.maksonic.beresta.feature.notes_list.api.NotesListFeature
import ru.maksonic.beresta.feature.notes_list.data.NotesRepositoryImpl
import ru.maksonic.beresta.feature.notes_list.data.cache.NoteCacheMapper
import ru.maksonic.beresta.feature.notes_list.data.cache.NotesCacheSource
import ru.maksonic.beresta.feature.notes_list.domain.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes_list.domain.NotesRepository
import ru.maksonic.beresta.feature.notes_list.ui.NotesListScreen
import ru.maksonic.beresta.feature.notes_list.ui.core.BottomPanelActionProgram
import ru.maksonic.beresta.feature.notes_list.ui.core.NotesListProgram
import ru.maksonic.beresta.feature.notes_list.ui.core.NotesListSandbox
import ru.maksonic.beresta.feature.onboarding.data.OnboardingRepository
import ru.maksonic.beresta.feature.onboarding.data.OnboardingVisibilityDatastore
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingVisibility
import ru.maksonic.beresta.feature.onboarding.domain.Repository
import ru.maksonic.beresta.feature.onboarding.ui.core.OnboardingSandbox
import ru.maksonic.beresta.feature.onboarding.ui.core.Program
import ru.maksonic.beresta.feature.splash_screen.SplashViewModel
import ru.maksonic.beresta.feature.tasks_list.api.TasksListFeature
import ru.maksonic.beresta.feature.tasks_list.ui.TasksListScreen
import ru.maksonic.beresta.feature.theme_selector.ThemeSelector
import ru.maksonic.beresta.navigation.graph_builder.GraphBuilder
import ru.maksonic.beresta.navigation.router.AppNavigator
import ru.maksonic.beresta.screen.main.ui.core.MainNavigationProgram
import ru.maksonic.beresta.screen.main.ui.core.MainSandbox

/**
 * @Author maksonic on 15.12.2022
 */
class BerestaApp : Application() {

    private val appModule = module {
        single<Datastore> { Datastore.Core(androidContext()) }
        viewModelOf(::MainViewModel)
    }

    private val coreModule = module {
        single(named(CoroutineDispatchers.IO)) { Dispatchers.IO }
        single(named(CoroutineDispatchers.DEFAULT)) { Dispatchers.Default }
        single(named(CoroutineDispatchers.MAIN)) { Dispatchers.Main }
    }

    private val navigationModule = module {
        single<GraphBuilder> { GraphBuilder.Builder() }
        single { AppNavigator() }
    }

    private val themeSelectorFeatureModule = module {
        single<ThemeSelector> { ThemeSelector.Feature(datastore = get()) }
    }

    private val splashScreenModule = module {
        viewModel { SplashViewModel(navigator = get(), isOnboarding = get()) }
    }

    private val onboardingFeatureModule = module {
        single { Program(repository = get(), onboardingVisibility = get(), navigator = get()) }
        viewModel { OnboardingSandbox(program = get()) }
    }

    private val onboardingDataModule = module {
        single<OnboardingVisibility> { OnboardingVisibilityDatastore(datastore = get()) }
        single<Repository> { OnboardingRepository }
    }

    private val mainScreenModule = module {
        single { MainNavigationProgram(navigator = get()) }
        viewModel {
            MainSandbox(
                navigationProgram = get(),
                notesListFeature = get(),
                tasksListFeature = get(),
                bottomPanelFeature = get()
            )
        }
    }

    private val notesListFeatureModule = module {
        single<NotesListFeature> { NotesListScreen() }
        single { NotesListProgram() }
        single { BottomPanelActionProgram(feature = get()) }
        viewModel {
            NotesListSandbox(
                notesListProgram = get(),
                bottomPanelActionProgram = get(),
                bottomPanelFeature = get()
            )
        }
    }

    private val notesListFeatureDataModule = module {
        single { NoteCacheMapper() }
        single {
            NotesCacheSource(
                noteDao = get(),
                dispatcher = get(named(CoroutineDispatchers.IO))
            )
        }
        single<NotesRepository> { NotesRepositoryImpl(cache = get(), mapper = get()) }
        single { FetchNotesUseCase(repository = get()) }
    }

    private val tasksListFeatureModule = module {
        single<TasksListFeature> { TasksListScreen() }
    }

    private val bottomPanelModule = module {
        single { BottomPanelSharedState() }
        single<BottomPanelFeature> { BottomPanelWidget(panelSharedState = get()) }
    }

    private val modules = listOf(
        appModule,
        splashScreenModule,
        navigationModule,
        databaseModule,
        onboardingDataModule,
        onboardingFeatureModule,
        mainScreenModule,
        notesListFeatureDataModule,
        notesListFeatureModule,
        tasksListFeatureModule,
        themeSelectorFeatureModule,
        coreModule,
        bottomPanelModule
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
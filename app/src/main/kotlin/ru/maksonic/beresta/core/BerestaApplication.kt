package ru.maksonic.beresta.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.maksonic.beresta.common.coroutine_dispatchers.coroutineDispatchersModule
import ru.maksonic.beresta.common.json_converter.jsonConverterModule
import ru.maksonic.beresta.data.common.di.dataCommonModule
import ru.maksonic.beresta.data.database.di.databaseModule
import ru.maksonic.beresta.data.notes_folders.di.notesFoldersDataModule
import ru.maksonic.beresta.data.notes.di.notesListDataModule
import ru.maksonic.beresta.di.appModule
import ru.maksonic.beresta.feature.edit_note.core.di.editNoteFeatureModule
import ru.maksonic.beresta.feature.language_picker.core.di.languagePickerApiFeatureModule
import ru.maksonic.beresta.feature.notes.folders.core.di.notesFoldersFeatureModel
import ru.maksonic.beresta.feature.notes.list.core.di.notesListFeatureModel
import ru.maksonic.beresta.feature.onboarding.core.di.onboardingProgramFeatureModule
import ru.maksonic.beresta.feature.search_bar.core.di.topSearchBarFeatureModule
import ru.maksonic.beresta.feature.splash_screen.core.di.splashScreenFeatureModule
import ru.maksonic.beresta.feature.theme_picker.core.di.themePickerFeatureModule
import ru.maksonic.beresta.feature.top_bar_counter.core.di.topBarCounterFeatureModule
import ru.maksonic.beresta.language_engine.core.di.languageEngineModule
import ru.maksonic.beresta.navigation.graph_builder.di.navigationModule
import ru.maksonic.beresta.screen.main.di.mainScreenModule
import ru.maksonic.beresta.screen.settings.di.settingsModule
import ru.maksonic.beresta.screen.trash_list.folders.di.trashFoldersModule
import ru.maksonic.beresta.screen.trash_list.notes.di.trashNotesModule

/**
 * @Author maksonic on 22.04.2023
 */
class BerestaApplication : Application() {
    private val modules = listOf(
        appModule,
        coroutineDispatchersModule,
        navigationModule,
        settingsModule,
        jsonConverterModule,
        languageEngineModule,
        dataCommonModule,
        databaseModule,
        splashScreenFeatureModule,
        onboardingProgramFeatureModule,
        languagePickerApiFeatureModule,
        themePickerFeatureModule,
        notesListFeatureModel,
        notesListDataModule,
        mainScreenModule,
        editNoteFeatureModule,
        topSearchBarFeatureModule,
        topBarCounterFeatureModule,
        notesFoldersFeatureModel,
        notesFoldersDataModule,
        trashNotesModule,
        trashFoldersModule,
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BerestaApplication)
            modules(modules)
        }
    }
}
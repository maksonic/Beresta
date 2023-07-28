package ru.maksonic.beresta.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.maksonic.beresta.common.coroutine_dispatchers.coroutineDispatchersModule
import ru.maksonic.beresta.common.json_converter.jsonConverterModule
import ru.maksonic.beresta.core.di.coreModule
import ru.maksonic.beresta.data.common.di.dataCommonModule
import ru.maksonic.beresta.data.database.di.databaseModule
import ru.maksonic.beresta.data.folders.di.foldersDataModule
import ru.maksonic.beresta.data.notes.di.notesDataModule
import ru.maksonic.beresta.di.appModule
import ru.maksonic.beresta.feature.edit_note.ui.di.editNoteUiFeatureModule
import ru.maksonic.beresta.feature.folders_chips.core.di.foldersChipsRowCoreFeatureModule
import ru.maksonic.beresta.feature.folders_chips.ui.di.foldersChipsRowUiFeatureModule
import ru.maksonic.beresta.feature.hidden_notes_dialog.core.di.hiddenNotesCoreFeatureModule
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.di.hiddenNotesUiFeatureModule
import ru.maksonic.beresta.feature.language_picker.core.di.languagePickerCoreFeatureModule
import ru.maksonic.beresta.feature.language_picker.ui.di.languagePickerUiFeatureModule
import ru.maksonic.beresta.feature.notes.core.di.notesCoreFeatureModule
import ru.maksonic.beresta.feature.notes.ui.di.notesUiFeatureModule
import ru.maksonic.beresta.feature.onboarding.core.di.onboardingCoreFeatureModule
import ru.maksonic.beresta.feature.onboarding.ui.di.onboardingUiFeatureModule
import ru.maksonic.beresta.feature.search_bar.ui.di.topSearchBarUiFeatureModule
import ru.maksonic.beresta.feature.sorting_sheet.core.di.listSortCoreFeatureModule
import ru.maksonic.beresta.feature.sorting_sheet.ui.di.sortingSheetUiFeatureModule
import ru.maksonic.beresta.feature.splash_screen.ui.di.splashScreenCoreFeatureModule
import ru.maksonic.beresta.feature.theme_picker.core.di.themePickerCoreFeatureModule
import ru.maksonic.beresta.feature.theme_picker.ui.di.themePickerUiFeatureModule
import ru.maksonic.beresta.feature.top_bar_counter.core.di.topBarCounterUiFeatureModule
import ru.maksonic.beresta.language_engine.core.di.languageEngineModule
import ru.maksonic.beresta.navigation.graph_builder.di.navigationModule
import ru.maksonic.beresta.screen.folders.di.foldersScreenModule
import ru.maksonic.beresta.screen.hidden_notes.di.hiddenNotesScreenModule
import ru.maksonic.beresta.screen.main.di.mainScreenModule
import ru.maksonic.beresta.screen.settings.appearance.di.settingsAppearanceScreenModule
import ru.maksonic.beresta.screen.settings.di.settingsScreenModule
import ru.maksonic.beresta.screen.trash_list.folders.di.trashFoldersScreenModule
import ru.maksonic.beresta.screen.trash_list.notes.di.trashNotesScreenModule

/**
 * @Author maksonic on 22.04.2023
 */
class BerestaApplication : Application() {
    private val modules = listOf(
        //core
        appModule,
        coreModule,
        coroutineDispatchersModule,
        jsonConverterModule,
        languageEngineModule,
        navigationModule,
        //data
        dataCommonModule,
        databaseModule,
        notesDataModule,
        foldersDataModule,
        //screens
        mainScreenModule,
        settingsScreenModule,
        settingsAppearanceScreenModule,
        foldersScreenModule,
        trashNotesScreenModule,
        trashFoldersScreenModule,
        hiddenNotesScreenModule,
        //features
        themePickerUiFeatureModule,
        themePickerCoreFeatureModule,
        languagePickerUiFeatureModule,
        languagePickerCoreFeatureModule,
        splashScreenCoreFeatureModule,
        onboardingCoreFeatureModule,
        onboardingUiFeatureModule,
        notesUiFeatureModule,
        notesCoreFeatureModule,
        editNoteUiFeatureModule,
        topSearchBarUiFeatureModule,
        topBarCounterUiFeatureModule,
        foldersChipsRowUiFeatureModule,
        foldersChipsRowCoreFeatureModule,
        sortingSheetUiFeatureModule,
        listSortCoreFeatureModule,
        hiddenNotesCoreFeatureModule,
        hiddenNotesUiFeatureModule
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
package ru.maksonic.beresta.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.maksonic.beresta.database.di.databaseModule
import ru.maksonic.beresta.di.appModule
import ru.maksonic.beresta.feature.app_lang.data.di.appLangDataFeatureModule
import ru.maksonic.beresta.feature.app_theme.data.di.appThemeDataFeatureModule
import ru.maksonic.beresta.feature.folders_list.data.di.foldersListDataModule
import ru.maksonic.beresta.feature.folders_list.data.di.foldersListLocalDataModule
import ru.maksonic.beresta.feature.folders_list.ui.core.di.foldersListUiFeatureModule
import ru.maksonic.beresta.feature.hidden_notes_dialog.data.di.hiddenNotesDialogDataFeatureModule
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.di.hiddenNotesDialogUiFeatureModule
import ru.maksonic.beresta.feature.marker_color_picker.data.di.markerColorPickerFeatureDataModule
import ru.maksonic.beresta.feature.marker_color_picker.ui.core.di.markerColorPickerFeatureUiModule
import ru.maksonic.beresta.feature.notes_list.data.di.notesListDataModule
import ru.maksonic.beresta.feature.notes_list.data.di.notesListLocalDataModule
import ru.maksonic.beresta.feature.notes_list.ui.core.di.notesListUiFeatureModule
import ru.maksonic.beresta.feature.onboarding.data.di.onboardingDataFeatureModule
import ru.maksonic.beresta.feature.onboarding.ui.core.onboardingUiFeatureModule
import ru.maksonic.beresta.feature.sorting_sheet.data.di.sortingSheetDataFeatureModule
import ru.maksonic.beresta.feature.sorting_sheet.ui.core.di.sortingSheetUiFeatureModule
import ru.maksonic.beresta.feature.tags_list.data.di.tagsListDataFeatureModule
import ru.maksonic.beresta.feature.tags_list.ui.core.di.tagsListUiFeatureModule
import ru.maksonic.beresta.feature.ui.add_folder_dialog.core.di.addFolderDialogUiFeatureModule
import ru.maksonic.beresta.feature.ui.edit_note.core.di.editNoteUiFeatureModule
import ru.maksonic.beresta.feature.ui.language_picker.core.di.languagePickerUiFeatureModule
import ru.maksonic.beresta.feature.ui.search_bar.core.di.searchBarUiFeatureModule
import ru.maksonic.beresta.feature.ui.theme_picker.core.di.themePickerUiFeatureModule
import ru.maksonic.beresta.feature.wallpaper_picker.data.di.wallpaperPickerDataFeatureModule
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.di.wallpaperPickerUiFeatureModule
import ru.maksonic.beresta.language_engine.core.di.languageEngineModule
import ru.maksonic.beresta.navigation.graph_builder.di.navigationModule
import ru.maksonic.beresta.platform.core.di.platformCoreModule
import ru.maksonic.beresta.screen.folders.di.foldersScreenModule
import ru.maksonic.beresta.screen.hidden_notes.di.hiddenNotesScreenModule
import ru.maksonic.beresta.screen.main.di.mainScreenModule
import ru.maksonic.beresta.screen.settings.appearance.di.settingsAppearanceScreenModule
import ru.maksonic.beresta.screen.settings.di.settingsScreenModule
import ru.maksonic.beresta.screen.settings.notifications.di.settingsNotificationsScreenModule
import ru.maksonic.beresta.screen.settings.security.di.settingsSecurityScreenModule
import ru.maksonic.beresta.screen.splash.di.splashScreenModule
import ru.maksonic.beresta.screen.trash.folders.di.trashFoldersScreenModule
import ru.maksonic.beresta.screen.trash.notes.di.trashNotesScreenModule

/**
 * @Author maksonic on 27.09.2023
 */
class BerestaApplication : Application() {
    private val modules = listOf(
        appModule,
        databaseModule,
        platformCoreModule,
        languageEngineModule,
        navigationModule,
        // Screen
        hiddenNotesScreenModule,
        foldersScreenModule,
        mainScreenModule,
        settingsScreenModule,
        settingsAppearanceScreenModule,
        settingsSecurityScreenModule,
        settingsNotificationsScreenModule,
        splashScreenModule,
        trashFoldersScreenModule,
        trashNotesScreenModule,
        // Feature
        addFolderDialogUiFeatureModule,
        appThemeDataFeatureModule,
        appLangDataFeatureModule,
        editNoteUiFeatureModule,
        foldersListDataModule,
        foldersListLocalDataModule,
        foldersListUiFeatureModule,
        hiddenNotesDialogDataFeatureModule,
        hiddenNotesDialogUiFeatureModule,
        languagePickerUiFeatureModule,
        markerColorPickerFeatureDataModule,
        markerColorPickerFeatureUiModule,
        notesListDataModule,
        notesListLocalDataModule,
        notesListUiFeatureModule,
        onboardingDataFeatureModule,
        onboardingUiFeatureModule,
        tagsListDataFeatureModule,
        tagsListUiFeatureModule,
        themePickerUiFeatureModule,
        searchBarUiFeatureModule,
        sortingSheetDataFeatureModule,
        sortingSheetUiFeatureModule,
        wallpaperPickerDataFeatureModule,
        wallpaperPickerUiFeatureModule
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
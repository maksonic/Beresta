package ru.maksonic.beresta.language_engine.shell.provider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import ru.maksonic.beresta.language_engine.shell.LanguageContainer
import ru.maksonic.beresta.language_engine.shell.components.LangComponentEditor
import ru.maksonic.beresta.language_engine.shell.components.LangComponentHiddenNotes
import ru.maksonic.beresta.language_engine.shell.components.LangComponentShared
import ru.maksonic.beresta.language_engine.shell.components.LangComponentSortSheet
import ru.maksonic.beresta.language_engine.shell.components.LangComponentTrash
import ru.maksonic.beresta.language_engine.shell.components.LangComponentUiError
import ru.maksonic.beresta.language_engine.shell.components.LangComponentsFolders
import ru.maksonic.beresta.language_engine.shell.components.TranslatedLanguage
import ru.maksonic.beresta.language_engine.shell.components.settings.LangComponentSettings
import ru.maksonic.beresta.language_engine.shell.components.settings.LangComponentSettingsAppearance
import ru.maksonic.beresta.language_engine.shell.components.settings.LangComponentSettingsNotifications
import ru.maksonic.beresta.language_engine.shell.components.settings.LangComponentSettingsSecurity

/**
 * @Author maksonic on 16.02.2023
 */
enum class AppLangUi(val title: String) {
    RUSSIAN(title = "\uD83C\uDDF7\uD83C\uDDFA  Русский"),
    ENGLISH(title = "\uD83C\uDDEC\uD83C\uDDE7  English"),
    CHINESE(title = "\uD83C\uDDE8\uD83C\uDDF3  简体中文"),
    CHINESE_TR(title = "\uD83C\uDDE8\uD83C\uDDF3  繁体中文"),
}

val LocalAppLanguage = staticCompositionLocalOf<LanguageModel> {
    error("No language provided")
}

val text: LanguageModel @Composable get() = LocalAppLanguage.current

data class LanguageModel(
    val langTitle: String = "",
    val translatedLanguage: TranslatedLanguage,
    val editor: LangComponentEditor,
    val errorUi: LangComponentUiError,
    val folders: LangComponentsFolders,
    val hiddenNotes: LangComponentHiddenNotes,
    val settings: LangComponentSettings,
    val settingsAppearance: LangComponentSettingsAppearance,
    val settingsNotifications: LangComponentSettingsNotifications,
    val settingsSecurity: LangComponentSettingsSecurity,
    val shared: LangComponentShared,
    val sortingSheet: LangComponentSortSheet,
    val trash: LangComponentTrash,
) {
    companion object {
        val Default = LanguageModel(
            langTitle = "English",
            translatedLanguage = TranslatedLanguage.Default,
            editor = LangComponentEditor.Default,
            errorUi = LangComponentUiError.Default,
            folders = LangComponentsFolders.Default,
            hiddenNotes = LangComponentHiddenNotes.Default,
            settings = LangComponentSettings.Default,
            settingsAppearance = LangComponentSettingsAppearance.Default,
            settingsNotifications = LangComponentSettingsNotifications.Default,
            settingsSecurity = LangComponentSettingsSecurity.Default,
            shared = LangComponentShared.Default,
            sortingSheet = LangComponentSortSheet.Default,
            trash = LangComponentTrash.Default
        )

        fun russian(data: LanguageContainer): LanguageModel = with(data.russian) {
            LanguageModel(
                langTitle = langTitle,
                translatedLanguage = translatedLanguage,
                editor = editor,
                errorUi = errorUi,
                folders = folders,
                hiddenNotes = hiddenNotes,
                settings = settings,
                settingsAppearance = settingsAppearance,
                settingsNotifications = settingsNotifications,
                settingsSecurity = settingsSecurity,
                shared = shared,
                sortingSheet = sortingSheet,
                trash = trash
            )
        }

        fun english(data: LanguageContainer): LanguageModel = with(data.english) {
            LanguageModel(
                langTitle = langTitle,
                translatedLanguage = translatedLanguage,
                editor = editor,
                errorUi = errorUi,
                folders = folders,
                hiddenNotes = hiddenNotes,
                settings = settings,
                settingsAppearance = settingsAppearance,
                settingsNotifications = settingsNotifications,
                settingsSecurity = settingsSecurity,
                shared = shared,
                sortingSheet = sortingSheet,
                trash = trash
            )
        }

        fun chinese(data: LanguageContainer): LanguageModel = with(data.chinese) {
            LanguageModel(
                langTitle = langTitle,
                translatedLanguage = translatedLanguage,
                editor = editor,
                errorUi = errorUi,
                folders = folders,
                hiddenNotes = hiddenNotes,
                settings = settings,
                settingsAppearance = settingsAppearance,
                settingsNotifications = settingsNotifications,
                settingsSecurity = settingsSecurity,
                shared = shared,
                sortingSheet = sortingSheet,
                trash = trash
            )
        }

        fun chineseTr(data: LanguageContainer): LanguageModel = with(data.chineseTr) {
            LanguageModel(
                langTitle = langTitle,
                translatedLanguage = translatedLanguage,
                editor = editor,
                errorUi = errorUi,
                folders = folders,
                hiddenNotes = hiddenNotes,
                settings = settings,
                settingsAppearance = settingsAppearance,
                settingsNotifications = settingsNotifications,
                settingsSecurity = settingsSecurity,
                shared = shared,
                sortingSheet = sortingSheet,
                trash = trash
            )
        }
    }
}
package ru.maksonic.beresta.language_engine.shell.languages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.maksonic.beresta.language_engine.shell.components.LangComponentEditor
import ru.maksonic.beresta.language_engine.shell.components.LangComponentHiddenNotes
import ru.maksonic.beresta.language_engine.shell.components.LangComponentShared
import ru.maksonic.beresta.language_engine.shell.components.LangComponentSortSheet
import ru.maksonic.beresta.language_engine.shell.components.LangComponentTags
import ru.maksonic.beresta.language_engine.shell.components.LangComponentTrash
import ru.maksonic.beresta.language_engine.shell.components.LangComponentUiError
import ru.maksonic.beresta.language_engine.shell.components.LangComponentsFolders
import ru.maksonic.beresta.language_engine.shell.components.TranslatedLanguage
import ru.maksonic.beresta.language_engine.shell.components.settings.LangComponentSettings
import ru.maksonic.beresta.language_engine.shell.components.settings.LangComponentSettingsAppearance
import ru.maksonic.beresta.language_engine.shell.components.settings.LangComponentSettingsNotifications
import ru.maksonic.beresta.language_engine.shell.components.settings.LangComponentSettingsSecurity

/**
 * @Author maksonic on 17.02.2023
 */
@Serializable
data class Chinese(
    @SerialName("language_title") val langTitle: String = "简体中文",
    @SerialName("translated_language") val translatedLanguage: TranslatedLanguage,
    @SerialName("editor") val editor: LangComponentEditor,
    @SerialName("error_ui") val errorUi: LangComponentUiError,
    @SerialName("folders") val folders: LangComponentsFolders,
    @SerialName("hidden_notes") val hiddenNotes: LangComponentHiddenNotes,
    @SerialName("shared") val shared: LangComponentShared,
    @SerialName("settings") val settings: LangComponentSettings,
    @SerialName("settings_appearance") val settingsAppearance: LangComponentSettingsAppearance,
    @SerialName("settings_notifications") val settingsNotifications: LangComponentSettingsNotifications,
    @SerialName("settings_security") val settingsSecurity: LangComponentSettingsSecurity,
    @SerialName("sorting_sheet") val sortingSheet: LangComponentSortSheet,
    @SerialName("tags") val tags: LangComponentTags,
    @SerialName("trash") val trash: LangComponentTrash
)
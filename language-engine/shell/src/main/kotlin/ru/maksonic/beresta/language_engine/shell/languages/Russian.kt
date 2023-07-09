package ru.maksonic.beresta.language_engine.shell.languages

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import ru.maksonic.beresta.language_engine.shell.components.LangEditorData
import ru.maksonic.beresta.language_engine.shell.components.LangFoldersListData
import ru.maksonic.beresta.language_engine.shell.components.LangOnboardingData
import ru.maksonic.beresta.language_engine.shell.components.settings.LangSettingsScreenData
import ru.maksonic.beresta.language_engine.shell.components.LangSharedData
import ru.maksonic.beresta.language_engine.shell.components.LangSortNotesSheetData
import ru.maksonic.beresta.language_engine.shell.components.LangTrashData
import ru.maksonic.beresta.language_engine.shell.components.TranslatedLanguage
import ru.maksonic.beresta.language_engine.shell.components.settings.LangSettingsAppearanceScreenData

/**
 * @Author maksonic on 17.02.2023
 */
@Serializable
data class Russian(
    @SerializedName("language_title") val langTitle: String = "Русский",
    @SerializedName("translated_language") val translatedLanguage: TranslatedLanguage,
    @SerializedName("onboarding") val langOnboardingData: LangOnboardingData,
    @SerializedName("shared") val shared: LangSharedData,
    @SerializedName("settings") val settings: LangSettingsScreenData,
    @SerializedName("settings_appearance") val settingsAppearance: LangSettingsAppearanceScreenData,
    @SerializedName("editor") val editor: LangEditorData,
    @SerializedName("folders") val folders: LangFoldersListData,
    @SerializedName("trash") val trash: LangTrashData,
    @SerializedName("sort_notes_sheet") val sortNotesSheet: LangSortNotesSheetData
)
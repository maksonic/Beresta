package ru.maksonic.beresta.feature.language_selector.api.languages

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import ru.maksonic.beresta.feature.language_selector.api.components.*

/**
 * @Author maksonic on 21.02.2023
 */
@Serializable
data class ChineseTr(
    @SerializedName("language_title") val langTitle: String = "繁体中文",
    @SerializedName("translated_language") val translatedLanguage: TranslatedLanguage,
    @SerializedName("onboarding") val langOnboardingData: LangOnboardingData,
    @SerializedName("shared") val shared: LangSharedData,
    @SerializedName("settings") val settings: LangSettingsScreenData,
    @SerializedName("editor") val editor: LangEditorData
)
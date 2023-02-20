package ru.maksonic.beresta.feature.language_selector.api.languages

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import ru.maksonic.beresta.feature.language_selector.api.components.LangSharedData
import ru.maksonic.beresta.feature.language_selector.api.components.LangOnboardingData
import ru.maksonic.beresta.feature.language_selector.api.components.LangSettingsScreenData
import ru.maksonic.beresta.feature.language_selector.api.components.TranslatedLanguage

/**
 * @Author maksonic on 17.02.2023
 */
@Serializable
data class English(
    @SerializedName("language_title") val langTitle: String = "English",
    @SerializedName("translated_language") val translatedLanguage: TranslatedLanguage,
    @SerializedName("onboarding") val langOnboardingData: LangOnboardingData,
    @SerializedName("shared") val shared: LangSharedData,
    @SerializedName("settings") val settings: LangSettingsScreenData
)
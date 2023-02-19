package ru.maksonic.beresta.feature.language_selector.api.languages

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import ru.maksonic.beresta.feature.language_selector.api.components.LanguageSheetTextData
import ru.maksonic.beresta.feature.language_selector.api.components.OnboardingTextData

/**
 * @Author maksonic on 17.02.2023
 */
@Serializable
data class English(
    @SerializedName("onboarding")
    val onboardingTextData: OnboardingTextData,
    @SerializedName("select_language_sheet")
    val langSheet: LanguageSheetTextData
)
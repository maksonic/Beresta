package ru.maksonic.beresta.feature.language_selector.api.provider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import ru.maksonic.beresta.feature.language_selector.api.components.LanguageSheetTextData
import ru.maksonic.beresta.feature.language_selector.api.components.OnboardingDataItem
import ru.maksonic.beresta.feature.language_selector.api.components.OnboardingTextData

/**
 * @Author maksonic on 16.02.2023
 */
private const val ONBOARDINGS_COUNT = 4

enum class AppLanguage(val title: String) {
    RUSSIAN(title = "\uD83C\uDDF7\uD83C\uDDFA  Русский"),
    ENGLISH(title = "\uD83C\uDDEC\uD83C\uDDE7  English"),
    CHINES(title = "\uD83C\uDDE8\uD83C\uDDF3  中国人")
}

val LocalBerestaLanguage = staticCompositionLocalOf<BerestaLanguage> {
    error("No language provided")
}

data class BerestaLanguage(
    val onboardingTextData: OnboardingTextData = OnboardingTextData(
        data = Array(ONBOARDINGS_COUNT) { OnboardingDataItem()}
    ),
    val languageSheetTextData: LanguageSheetTextData = LanguageSheetTextData()
)

val text: BerestaLanguage @Composable get() = LocalBerestaLanguage.current
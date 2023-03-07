package ru.maksonic.beresta.feature.language_selector.api.provider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import ru.maksonic.beresta.feature.language_selector.api.components.*

/**
 * @Author maksonic on 16.02.2023
 */
private const val ONBOARDINGS_COUNT = 4

enum class AppLanguage(val title: String) {
    RUSSIAN(title = "\uD83C\uDDF7\uD83C\uDDFA  Русский"),
    ENGLISH(title = "\uD83C\uDDEC\uD83C\uDDE7  English"),
    CHINESE(title = "\uD83C\uDDE8\uD83C\uDDF3  简体中文"),
    CHINESE_TR(title = "\uD83C\uDDE8\uD83C\uDDF3  繁体中文"),
}

val LocalBerestaLanguage = staticCompositionLocalOf<BerestaLanguage> {
    error("No language provided")
}

data class BerestaLanguage(
    val langTitle: String = "",
    val translatedLanguage: TranslatedLanguage = TranslatedLanguage(),
    val onboarding: LangOnboardingData = LangOnboardingData(
        data = Array(ONBOARDINGS_COUNT) { OnboardingDataItem()}
    ),
    val shared: LangSharedData = LangSharedData(),
    val settings: LangSettingsScreenData = LangSettingsScreenData(),
    val editNote: LangEditorData = LangEditorData()
)

val text: BerestaLanguage @Composable get() = LocalBerestaLanguage.current
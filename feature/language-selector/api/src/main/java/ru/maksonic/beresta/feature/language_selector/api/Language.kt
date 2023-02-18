package ru.maksonic.beresta.feature.language_selector.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * @Author maksonic on 16.02.2023
 */
enum class AppLanguage {
    RUSSIAN, ENGLISH, CHINES
}

val LocalBerestaLanguage = staticCompositionLocalOf<BerestaLanguage> {
    error("No language provided")
}

data class BerestaLanguage(
    val onboarding: OnboardingScreenLang,
    val languageSheet: LanguageSheet
)

data class OnboardingScreenLang(
    val onboardingNextBtnTitle: String,
    val onboardingSyncBtnTitle: String,
    val onboardingNotSyncBtnTitle: String,
)

data class LanguageSheet(
    val saveLang: String,
    val cancel: String
)

fun provideLanguage(language: AppLanguage): BerestaLanguage {

    val onboardingScreenLang = OnboardingScreenLang(
        onboardingNextBtnTitle = when (language) {
            AppLanguage.RUSSIAN -> "Продолжить"
            AppLanguage.ENGLISH -> "Next"
            AppLanguage.CHINES -> "继续"
        },
        onboardingSyncBtnTitle = when (language) {
            AppLanguage.RUSSIAN -> "Синхронизировать"
            AppLanguage.ENGLISH -> "Synchronize"
            AppLanguage.CHINES -> "同步"
        },
        onboardingNotSyncBtnTitle = when (language) {
            AppLanguage.RUSSIAN -> "Не сейчас"
            AppLanguage.ENGLISH -> "Not now"
            AppLanguage.CHINES -> "现在不要"
        },
    )
    val languageSheet = LanguageSheet(
        saveLang = when (language) {
            AppLanguage.RUSSIAN -> "Сохранить"
            AppLanguage.ENGLISH -> "Save"
            AppLanguage.CHINES -> "节省"
        },
        cancel = when (language) {
            AppLanguage.RUSSIAN -> "Отменить"
            AppLanguage.ENGLISH -> "Cancel"
            AppLanguage.CHINES -> "取消"
        },
    )

    return BerestaLanguage(onboarding = onboardingScreenLang, languageSheet = languageSheet)
}


val text: BerestaLanguage @Composable get() = LocalBerestaLanguage.current
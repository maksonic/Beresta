package ru.maksonic.beresta.feature.language_selector.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.maksonic.beresta.feature.language_selector.api.AppLanguage
import ru.maksonic.beresta.feature.language_selector.api.BerestaLanguage
import ru.maksonic.beresta.feature.language_selector.api.LanguageProvider
import ru.maksonic.beresta.feature.language_selector.api.OnboardingScreenLang

/**
 * @Author maksonic on 17.02.2023
 */
class LanguageProviderImpl() : LanguageProvider {
    private val _data: MutableStateFlow<BerestaLanguage?> = MutableStateFlow(null)
    private val data = _data.asStateFlow()



    override fun provideLanguage(currentLanguage: AppLanguage): AppLanguage {

        val onboardingScreenLang = OnboardingScreenLang(
            onboardingNextBtnTitle = when (currentLanguage) {
                AppLanguage.RUSSIAN -> "Продолжить"
                AppLanguage.ENGLISH -> "Next"
                AppLanguage.CHINES -> "继续"
            },
            onboardingSyncBtnTitle = when (currentLanguage) {
                AppLanguage.RUSSIAN -> "Синхронизировать"
                AppLanguage.ENGLISH -> "Synchronize"
                AppLanguage.CHINES -> "同步"
            },
            onboardingNotSyncBtnTitle = when (currentLanguage) {
                AppLanguage.RUSSIAN -> "Не сейчас"
                AppLanguage.ENGLISH -> "Not now"
                AppLanguage.CHINES -> "现在不要"
            },
        )
        return AppLanguage.ENGLISH
    }
}
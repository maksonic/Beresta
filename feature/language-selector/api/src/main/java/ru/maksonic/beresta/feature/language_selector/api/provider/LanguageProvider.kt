package ru.maksonic.beresta.feature.language_selector.api.provider

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 17.02.2023
 */
interface LanguageProvider {
    suspend fun provideLanguage(currentLanguage: AppLanguage): Flow<BerestaLanguage>
}
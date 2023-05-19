package ru.maksonic.beresta.language_engine.shell.provider

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 17.02.2023
 */
interface LanguageProvider {
    suspend fun provideLanguage(currentLanguage: AppLanguage): Flow<BerestaLanguage>
}
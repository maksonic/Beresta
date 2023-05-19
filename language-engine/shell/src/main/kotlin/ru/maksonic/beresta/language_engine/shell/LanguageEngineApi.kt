package ru.maksonic.beresta.language_engine.shell

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage

/**
 * @Author maksonic on 22.04.2023
 */
interface LanguageEngineApi {
    suspend fun setLanguage(lang: AppLanguage)
    val current: Flow<AppLanguage>
}
package ru.maksonic.beresta.feature.app_lang.data

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.feature.app_lang.domain.AppLangDomain
import ru.maksonic.beresta.feature.app_lang.domain.AppLangRepository
import ru.maksonic.beresta.feature.app_lang.domain.usecase.FetchAppLangUseCase

/**
 * @Author maksonic on 15.10.2023
 */
class FetchAppLangUseCaseImpl(private val repository: AppLangRepository) : FetchAppLangUseCase {
    override fun invoke(): Flow<AppLangDomain> = repository.fetchAppCurrentLang()
}
package ru.maksonic.beresta.feature.app_lang.data

import ru.maksonic.beresta.feature.app_lang.domain.AppLangDomain
import ru.maksonic.beresta.feature.app_lang.domain.AppLangRepository
import ru.maksonic.beresta.feature.app_lang.domain.usecase.ChangeAppLangUseCase

/**
 * @Author maksonic on 15.10.2023
 */
class ChangeAppLangUseCaseImpl(private val repository: AppLangRepository) : ChangeAppLangUseCase {
    override suspend fun invoke(args: AppLangDomain) = repository.setLanguage(args)
}
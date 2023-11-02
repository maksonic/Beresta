package ru.maksonic.beresta.feature.app_lang.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.common.domain.UseCase
import ru.maksonic.beresta.feature.app_lang.domain.AppLangDomain

/**
 * @Author maksonic on 15.10.2023
 */
interface FetchAppLangUseCase : UseCase.Default<Flow<AppLangDomain>>
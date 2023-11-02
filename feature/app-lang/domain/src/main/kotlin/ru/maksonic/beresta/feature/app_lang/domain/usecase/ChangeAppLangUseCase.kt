package ru.maksonic.beresta.feature.app_lang.domain.usecase

import ru.maksonic.beresta.common.domain.UseCase
import ru.maksonic.beresta.feature.app_lang.domain.AppLangDomain

/**
 * @Author maksonic on 15.10.2023
 */
interface ChangeAppLangUseCase : UseCase.WithArgs.Async<Unit, AppLangDomain>
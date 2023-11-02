package ru.maksonic.beresta.feature.app_lang.domain.mapper

import ru.maksonic.beresta.common.core.Mapper
import ru.maksonic.beresta.feature.app_lang.domain.AppLangDomain
import ru.maksonic.beresta.feature.app_lang.domain.LanguageDomain

/**
 * @Author maksonic on 15.10.2023
 */
interface AppLangMapper<LanguageUi, AppLangUi> : Mapper<LanguageDomain, LanguageUi> {
    fun onlyThemeToUi(domain: AppLangDomain): AppLangUi
    fun onlyThemeToDomain(ui: AppLangUi): AppLangDomain
}
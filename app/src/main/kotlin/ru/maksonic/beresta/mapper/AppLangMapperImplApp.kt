package ru.maksonic.beresta.mapper

import ru.maksonic.beresta.feature.app_lang.domain.AppLangDomain
import ru.maksonic.beresta.feature.app_lang.domain.LanguageDomain
import ru.maksonic.beresta.feature.app_lang.domain.mapper.AppLangMapper
import ru.maksonic.beresta.feature.ui.language_picker.api.LanguageUi
import ru.maksonic.beresta.language_engine.shell.provider.AppLangUi

/**
 * @Author maksonic on 15.10.2023
 */
class AppLangMapperImplApp : AppLangMapper<LanguageUi, AppLangUi> {
    override fun mapTo(i: LanguageDomain) = LanguageUi(
        id = i.id, language = onlyThemeToUi(i.language), hint = i.hint
    )

    override fun mapFrom(o: LanguageUi) = LanguageDomain(
        id = o.id, language = onlyThemeToDomain(o.language), hint = o.hint
    )

    override fun onlyThemeToUi(domain: AppLangDomain) = AppLangUi.valueOf(domain.name)

    override fun onlyThemeToDomain(ui: AppLangUi) = AppLangDomain.valueOf(ui.name)
}

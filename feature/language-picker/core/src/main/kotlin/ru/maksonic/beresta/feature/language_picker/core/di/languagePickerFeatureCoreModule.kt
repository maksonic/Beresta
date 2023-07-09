package ru.maksonic.beresta.feature.language_picker.core.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.language_picker.api.LanguagePickerApi
import ru.maksonic.beresta.feature.language_picker.core.LanguagePickerFeatureCore

/**
 * @Author maksonic on 20.06.2023
 */
val languagePickerCoreFeatureModule = module {
    single<LanguagePickerApi.Feature> { LanguagePickerFeatureCore(datastore = get()) }
}
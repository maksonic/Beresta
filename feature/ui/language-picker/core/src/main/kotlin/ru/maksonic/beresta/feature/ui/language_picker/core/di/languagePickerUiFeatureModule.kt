package ru.maksonic.beresta.feature.ui.language_picker.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.ui.language_picker.api.LanguagePickerUiApi
import ru.maksonic.beresta.feature.ui.language_picker.core.LanguagePickerUiCore
import ru.maksonic.beresta.feature.ui.language_picker.core.main.LanguagePickerViewModel

/**
 * @Author maksonic on 28.09.2023
 */
val languagePickerUiFeatureModule = module {
    single<LanguagePickerUiApi> { LanguagePickerUiCore() }
    viewModel { LanguagePickerViewModel(repository = get(), mapper = get()) }
}
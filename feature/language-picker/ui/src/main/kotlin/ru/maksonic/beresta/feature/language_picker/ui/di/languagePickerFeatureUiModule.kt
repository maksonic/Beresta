package ru.maksonic.beresta.feature.language_picker.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.language_picker.api.LanguagePickerApi
import ru.maksonic.beresta.feature.language_picker.ui.LanguagePickerSheetContent
import ru.maksonic.beresta.feature.language_picker.ui.LanguagePickerViewModel

/**
 * @Author maksonic on 20.06.2023
 */
val languagePickerUiFeatureModule = module {
    single<LanguagePickerApi.Ui> { LanguagePickerSheetContent() }
    viewModel { LanguagePickerViewModel(langEngine = get()) }
}
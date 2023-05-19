package ru.maksonic.beresta.feature.language_picker.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.language_picker.api.LanguagePickerApi
import ru.maksonic.beresta.feature.language_picker.core.LanguagePickerViewModel
import ru.maksonic.beresta.feature.language_picker.core.ui.LanguagePickerBottomSheetBottomSheet

/**
 * @Author maksonic on 24.04.2023
 */
val languagePickerApiFeatureModule = module {
    single<LanguagePickerApi.Ui> { LanguagePickerBottomSheetBottomSheet() }
    viewModel { LanguagePickerViewModel(langEngine = get()) }
}
package ru.maksonic.beresta.feature.theme_picker.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.feature.theme_picker.ui.ThemePickerSheetContent
import ru.maksonic.beresta.feature.theme_picker.ui.core.ThemePickerProgram
import ru.maksonic.beresta.feature.theme_picker.ui.core.ThemePickerSandbox

/**
 * @Author maksonic on 19.06.2023
 */
val themePickerUiFeatureModule = module {
    single<ThemePickerApi.Ui> { ThemePickerSheetContent() }
    single { ThemePickerProgram(themeFeatureApi = get(), paletteFeatureApi = get()) }
    viewModel {
        ThemePickerSandbox(program = get())
    }
}
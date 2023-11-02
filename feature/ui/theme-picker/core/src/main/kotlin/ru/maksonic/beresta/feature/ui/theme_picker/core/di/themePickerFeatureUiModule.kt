package ru.maksonic.beresta.feature.ui.theme_picker.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.ui.theme_picker.api.ThemePickerUiApi
import ru.maksonic.beresta.feature.ui.theme_picker.core.ThemePickerUiCore
import ru.maksonic.beresta.feature.ui.theme_picker.core.main.ThemePickerProgram
import ru.maksonic.beresta.feature.ui.theme_picker.core.main.ThemePickerSandbox

/**
 * @Author maksonic on 19.06.2023
 */
val themePickerUiFeatureModule = module {
    single<ThemePickerUiApi> { ThemePickerUiCore() }
    single {
        ThemePickerProgram(
            fetchAppThemeContainerUseCase = get(),
            appThemeInteractor = get(),
            themeMapper = get(),
            paletteMapper = get()
        )
    }
    viewModel { ThemePickerSandbox(program = get()) }
}
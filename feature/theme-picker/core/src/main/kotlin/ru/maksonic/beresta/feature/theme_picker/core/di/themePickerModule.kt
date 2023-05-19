package ru.maksonic.beresta.feature.theme_picker.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.feature.theme_picker.core.SystemDarkModeChecker
import ru.maksonic.beresta.feature.theme_picker.core.ThemePaletteStore
import ru.maksonic.beresta.feature.theme_picker.core.ThemePickerStore
import ru.maksonic.beresta.feature.theme_picker.core.ThemePickerViewModel
import ru.maksonic.beresta.feature.theme_picker.core.ui.ThemePickerBottomSheet

/**
 * @Author maksonic on 24.04.2023
 */
val themePickerFeatureModule = module {
    single<ThemePickerApi.Theme> { ThemePickerStore(datastore = get()) }
    single<ThemePickerApi.Palette> { ThemePaletteStore(datastore = get()) }
    single<ThemePickerApi.DarkModeChecker> { SystemDarkModeChecker() }
    single<ThemePickerApi.Ui> { ThemePickerBottomSheet() }
    viewModel {
        ThemePickerViewModel(themeApi = get(), paletteApi = get(), darkModeChecker = get()
        )
    }
}
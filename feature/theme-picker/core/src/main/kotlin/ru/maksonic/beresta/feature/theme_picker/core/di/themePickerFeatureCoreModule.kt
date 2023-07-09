package ru.maksonic.beresta.feature.theme_picker.core.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.feature.theme_picker.core.PalettePickerFeatureCore
import ru.maksonic.beresta.feature.theme_picker.core.ThemePickerFeatureCore

/**
 * @Author maksonic on 19.06.2023
 */
val themePickerCoreFeatureModule = module {
    single<ThemePickerApi.Feature.Theme> { ThemePickerFeatureCore(datastore = get()) }
    single<ThemePickerApi.Feature.Palette> { PalettePickerFeatureCore(datastore = get()) }
}
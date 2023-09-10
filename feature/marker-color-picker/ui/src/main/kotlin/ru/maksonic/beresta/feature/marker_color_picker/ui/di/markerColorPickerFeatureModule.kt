package ru.maksonic.beresta.feature.marker_color_picker.ui.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.marker_color_picker.api.MarkerColorPickerApi
import ru.maksonic.beresta.feature.marker_color_picker.ui.MarkerColorPickerDialog

/**
 * @Author maksonic on 09.09.2023
 */
val markerColorPickerFeatureModule = module {
    single<MarkerColorPickerApi.Ui> { MarkerColorPickerDialog() }
}
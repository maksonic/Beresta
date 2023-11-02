package ru.maksonic.beresta.feature.marker_color_picker.ui.core.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.marker_color_picker.ui.api.MarkerPickerUiApi
import ru.maksonic.beresta.feature.marker_color_picker.ui.core.MarkerPickerUiCore

/**
 * @Author maksonic on 29.10.2023
 */
val markerColorPickerFeatureUiModule = module {
    factory<MarkerPickerUiApi> { MarkerPickerUiCore() }
}
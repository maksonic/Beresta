package ru.maksonic.beresta.feature.marker_color_picker.data.di

import org.koin.dsl.module
import ru.maksonic.beresta.common.ui_theme.colors.ColorContainer
import ru.maksonic.beresta.feature.marker_color_picker.data.MarkerColorsRepository
import ru.maksonic.beresta.feature.marker_color_picker.data.FetchMarkerColorsUseCaseImpl
import ru.maksonic.beresta.feature.marker_color_picker.data.FindMarkerColorByIdUseCaseImpl
import ru.maksonic.beresta.feature.marker_color_picker.domain.FetchMarkerColorsUseCase
import ru.maksonic.beresta.feature.marker_color_picker.domain.FindMarkerColorByIdUseCase

/**
 * @Author maksonic on 29.10.2023
 */
val markerColorPickerFeatureDataModule = module {
    factory { MarkerColorsRepository() }
    factory<FetchMarkerColorsUseCase<ColorContainer>> {
        FetchMarkerColorsUseCaseImpl(markerColorsRepository = get())
    }
    factory<FindMarkerColorByIdUseCase<ColorContainer>> {
        FindMarkerColorByIdUseCaseImpl(markerColorsRepository = get())
    }
}
package ru.maksonic.beresta.feature.marker_color_picker.data

import ru.maksonic.beresta.common.ui_theme.colors.ColorContainer
import ru.maksonic.beresta.feature.marker_color_picker.domain.FetchMarkerColorsUseCase

/**
 * @Author maksonic on 29.10.2023
 */
class FetchMarkerColorsUseCaseImpl(
    private val markerColorsRepository: MarkerColorsRepository
) : FetchMarkerColorsUseCase<ColorContainer> {
    override fun invoke(): List<ColorContainer> = markerColorsRepository.markerColors
}
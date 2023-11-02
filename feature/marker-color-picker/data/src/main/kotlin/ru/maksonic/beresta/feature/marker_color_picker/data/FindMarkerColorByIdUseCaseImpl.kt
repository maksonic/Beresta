package ru.maksonic.beresta.feature.marker_color_picker.data

import ru.maksonic.beresta.common.ui_theme.colors.ColorContainer
import ru.maksonic.beresta.feature.marker_color_picker.domain.FindMarkerColorByIdUseCase

/**
 * @Author maksonic on 29.10.2023
 */
class FindMarkerColorByIdUseCaseImpl(
    private val markerColorsRepository: MarkerColorsRepository
) : FindMarkerColorByIdUseCase<ColorContainer> {
    override fun invoke(args: Long): ColorContainer =
        markerColorsRepository.markerColors.find { it.colorId == args } ?: ColorContainer.Default
}
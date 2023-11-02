package ru.maksonic.beresta.feature.marker_color_picker.domain

import ru.maksonic.beresta.common.domain.UseCase

/**
 * @Author maksonic on 29.10.2023
 */
interface FindMarkerColorByIdUseCase<T> : UseCase.WithArgs<T, Long>
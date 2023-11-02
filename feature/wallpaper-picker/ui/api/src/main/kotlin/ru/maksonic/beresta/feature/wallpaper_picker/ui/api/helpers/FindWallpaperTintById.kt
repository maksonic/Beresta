package ru.maksonic.beresta.feature.wallpaper_picker.ui.api.helpers

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.TintColor

/**
 * @Author maksonic on 31.10.2023
 */
fun List<TintColor<Color>>.findById(id: Long) = this.find { it.id == id }

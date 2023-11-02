package ru.maksonic.beresta.feature.wallpaper_picker.domain.helpers

import ru.maksonic.beresta.feature.wallpaper_picker.domain.BackgroundColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.TintColor


/**
 * @Author maksonic on 24.09.2023
 */
typealias TextureStyle<T> = List<Pair<TintColor<T>, BackgroundColor<T>>>
package ru.maksonic.beresta.common.core.ui


/**
 * @Author maksonic on 21.11.2023
 */
interface ColorConverter<T> {
    fun hexToColor(colorString: String): T
}
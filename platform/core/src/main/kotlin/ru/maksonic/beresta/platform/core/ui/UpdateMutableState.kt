package ru.maksonic.beresta.platform.core.ui

import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.MutableState

/**
 * @Author maksonic on 10.09.2023
 */
fun <T> MutableState<T>.update(updated: T) {
    this.value = updated
}
fun MutableLongState.update(updated: Long) {
    this.longValue = updated
}
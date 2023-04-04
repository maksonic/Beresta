package ru.maksonic.beresta.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * @Author maksonic on 30.03.2023
 */
abstract class SharedUiState<T>(initial: T) {
    private val mutableState = MutableStateFlow(initial)
    val state = mutableState.asStateFlow()

    fun updateState(newValue: T) {
        mutableState.update { newValue }
    }

    fun updateState(updateBlock: (T) -> T) {
        val newState = updateBlock(mutableState.value)
        mutableState.value = newState
    }
}
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

    fun update(newValue: T) {
        mutableState.update { newValue }
    }

    fun update(updateBlock: (T) -> T) {
        val newState = updateBlock(mutableState.value)
        mutableState.update { newState }
    }

    fun updateAndGet(updateBlock: (T) -> T): SharedUiState<T> {
        val newState = updateBlock(mutableState.value)
        mutableState.update { newState }
        return this
    }
}
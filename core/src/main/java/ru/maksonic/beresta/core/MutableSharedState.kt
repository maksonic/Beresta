package ru.maksonic.beresta.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.sync.withLock

/**
 * @Author maksonic on 19.01.2023
 */
abstract class MutableSharedState<T>(initial: T) {
    val mutableState: MutableStateFlow<T> = MutableStateFlow(initial)
    val state: StateFlow<T> = mutableState.asStateFlow()

    fun update(updateBlock: (T) -> T): MutableSharedState<T>  {
        val newState = updateBlock(mutableState.value)
        mutableState.update { newState }
        return this
    }
}
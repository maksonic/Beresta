package ru.maksonic.beresta.elm.core

/**
 * @Author maksonic on 24.06.2023
 */
interface ElmProgram<M : ElmMessage, C : ElmCommand> {
    suspend fun executeProgram(cmd: C, consumer: (M) -> Unit)
}
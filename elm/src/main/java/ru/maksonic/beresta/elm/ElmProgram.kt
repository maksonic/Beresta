package ru.maksonic.beresta.elm

/**
 * @Author maksonic on 15.12.2022
 */
interface ElmProgram<M : ElmMessage, C : ElmCommand> {
    suspend fun executeProgram(cmd: C, consumer: (M) -> Unit)
}
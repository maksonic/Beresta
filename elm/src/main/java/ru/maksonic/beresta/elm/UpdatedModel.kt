package ru.maksonic.beresta.elm

import java.io.Serializable

/**
 * @Author maksonic on 15.12.2022
 */
data class UpdatedModel<out A: ElmModel, out B: Set<ElmCommand>, out C: Set<ElmEffect>>(
    val model: A,
    val commands: B? = null,
    val effects: C? = null
) : Serializable {
    override fun toString(): String = "($model, $commands, $effects)"
}
package ru.maksonic.easypayments.common.core.elm

import java.io.Serializable


/**
 * @Author maksonic on 24.06.2023
 */
data class ElmUpdate<out A: ElmModel, out B: Set<ElmCommand>, out C: Set<ElmEffect>>(
    val model: A,
    val commands: B? = null,
    val effects: C? = null
) : Serializable {
    override fun toString(): String = "($model, $commands, $effects)"
}
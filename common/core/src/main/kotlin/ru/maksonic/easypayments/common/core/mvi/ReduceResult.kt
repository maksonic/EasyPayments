package ru.maksonic.easypayments.common.core.mvi

import java.io.Serializable

/**
 * @Author maksonic on 27.11.2023
 */
data class ReduceResult<out A: UiState, out B: Set<UiCommand>, out C: Set<UiEffect>>(
    val model: A,
    val commands: B? = null,
    val effects: C? = null
) : Serializable {
    override fun toString(): String = "($model, $commands, $effects)"
}
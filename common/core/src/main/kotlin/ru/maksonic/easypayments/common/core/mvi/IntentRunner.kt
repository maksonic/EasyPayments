package ru.maksonic.easypayments.common.core.mvi

/**
 * @Author maksonic on 27.11.2023
 */
interface IntentRunner<Intent : UiIntent> {
    fun run(intent: Intent)
}
package ru.maksonic.easypayments.common.core.mvi

/**
 * @Author maksonic on 27.11.2023
 */
interface CommandHandler<M : UiIntent, C : UiCommand> {
    suspend fun handleCommand(cmd: C, consumer: (M) -> Unit)
}
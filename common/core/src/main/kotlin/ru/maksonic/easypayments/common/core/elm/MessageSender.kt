package ru.maksonic.easypayments.common.core.elm

/**
 * @Author maksonic on 24.06.2023
 */
interface MessageSender<M : ElmMessage> {
    fun send(msg: M)
}
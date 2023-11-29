package ru.maksonic.easypayments.feature.payments.ui.core

import ru.maksonic.easypayments.common.core.elm.ElmProgram

/**
 * @Author maksonic on 29.11.2023
 */
class PaymentsProgram : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.StartAuth -> {}
        }
    }
}
package ru.maksonic.easypayments.feature.ui.core

import ru.maksonic.easypayments.common.core.elm.ElmProgram

/**
 * @Author maksonic on 28.11.2023
 */
class AuthProgram : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchOnboardings
        }
    }
}
package ru.maksonic.easypayments.feature.settings.core

import ru.maksonic.easypayments.common.core.elm.ElmProgram

/**
 * @Author maksonic on 29.11.2023
 */
class SettingsProgram : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.LogOut -> {}
        }
    }
}
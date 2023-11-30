package ru.maksonic.easypayments.feature.settings.core

import ru.maksonic.easypayments.common.core.elm.ElmProgram
import ru.maksonic.easypayments.feature.domain.AuthLogOutUseCase
import ru.maksonic.easypayments.feature.domain.CheckTokenValidityUseCase
import ru.maksonic.easypayments.feature.domain.TokenStatus
import ru.maksonic.easypayments.feature.domain.isValid

/**
 * @Author maksonic on 29.11.2023
 */
class SettingsProgram(
    private val logOutUseCase: AuthLogOutUseCase,
    private val checkTokenValidityUseCase: CheckTokenValidityUseCase
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.LogOut -> logOutUseCase()
            is Cmd.CheckTokenValidity -> checkToken(consumer)
        }
    }

    private suspend fun checkToken(consumer: (Msg) -> Unit) = checkTokenValidityUseCase().collect {
        when (it) {
            is TokenStatus.Valid -> consumer(Msg.Inner.FetchedTokenStatus(it.isValid))
            is TokenStatus.Invalid -> consumer(Msg.Inner.FetchedTokenStatus(false))
        }
    }
}
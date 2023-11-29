package ru.maksonic.easypayments.feature.settings.core

import ru.maksonic.easypayments.common.core.elm.ElmProgram
import ru.maksonic.easypayments.common.ui.ResourceProvider
import ru.maksonic.easypayments.feature.domain.AuthLogOutUseCase
import ru.maksonic.easypayments.feature.domain.CheckTokenValidityUseCase
import ru.maksonic.easypayments.feature.domain.isValid
import ru.maksonic.easypayments.feature.settings.R

/**
 * @Author maksonic on 29.11.2023
 */
class SettingsProgram(
    private val logOutUseCase: AuthLogOutUseCase,
    private val checkTokenValidityUseCase: CheckTokenValidityUseCase,
    private val resourceProvider: ResourceProvider
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.LogOut -> logOut(consumer)
            is Cmd.CheckTokenValidity -> checkToken(consumer)
        }
    }

    private fun logOut(consumer: (Msg) -> Unit) = logOutUseCase()
        .onSuccess { consumer(Msg.Inner.SuccessfulLogOut) }
        .onFailure {
            consumer(
                Msg.Inner.ShowedLogOutError(
                    resourceProvider.getString(R.string.error_log_out)
                )
            )
        }

    private fun checkToken(consumer: (Msg) -> Unit) = checkTokenValidityUseCase()
        .onSuccess { consumer(Msg.Inner.FetchedTokenStatus(it.isValid)) }
        .onFailure { consumer(Msg.Inner.FetchedTokenStatus(false)) }
}
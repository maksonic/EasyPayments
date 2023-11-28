package ru.maksonic.easypayments.feature.ui.core

import ru.maksonic.easypayments.common.core.elm.ElmCommand
import ru.maksonic.easypayments.common.core.elm.ElmEffect
import ru.maksonic.easypayments.common.core.elm.ElmMessage
import ru.maksonic.easypayments.common.core.elm.ElmModel

/**
 * @Author maksonic on 27.11.2023
 */

data class Model(
    val email: String,
    val password: String,
    val emailState: VerificationEmailState,
    val passwordState: VerificationPasswordState,

    ) : ElmModel {
    companion object {
        val Initial = Model(
            email = "",
            password = "",
            emailState = VerificationEmailState.Idle,
            passwordState = VerificationPasswordState.Idle
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnAuthBtnClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class UpdatedEmailInput(val value: String) : Inner()
        data class UpdatedPasswordInput(val value: String) : Inner()
        data class AuthResult(val string: String) : Inner()
        data class InputsVerificationResult(
            val emailState: VerificationEmailState,
            val passwordState: VerificationPasswordState
        ) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data class StartAuth(val email: String, val password: String) : Cmd()
    data class VerifyInputs(val email: String, val password: String) : Cmd()
}

sealed class Eff : ElmEffect
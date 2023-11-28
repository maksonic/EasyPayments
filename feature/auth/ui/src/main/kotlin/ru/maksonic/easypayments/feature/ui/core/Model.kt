package ru.maksonic.easypayments.feature.ui.core

import ru.maksonic.easypayments.common.core.elm.ElmCommand
import ru.maksonic.easypayments.common.core.elm.ElmEffect
import ru.maksonic.easypayments.common.core.elm.ElmMessage
import ru.maksonic.easypayments.common.core.elm.ElmModel

/**
 * @Author maksonic on 27.11.2023
 */

data class Model(
    val isVisibleLoader: Boolean,
    val username: String,
    val password: String,
    val usernameState: VerificationUsernameState,
    val passwordState: VerificationPasswordState,

    ) : ElmModel {
    companion object {
        val Initial = Model(
            isVisibleLoader = false,
            username = "",
            password = "",
            usernameState = VerificationUsernameState.Idle,
            passwordState = VerificationPasswordState.Idle
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnAuthBtnClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class UpdatedUsernameInput(val value: String) : Inner()
        data class UpdatedPasswordInput(val value: String) : Inner()
        data class AuthResult(val string: String) : Inner()
        data class InputsVerificationResult(
            val usernameState: VerificationUsernameState,
            val passwordState: VerificationPasswordState
        ) : Inner()

        data object FetchedValidTokenStatus : Inner()
        data class FetchedInvalidTokenStatus(val cause: String) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data class StartAuth(val username: String, val password: String) : Cmd()
    data class VerifyInputs(val username: String, val password: String) : Cmd()
}

sealed class Eff : ElmEffect {
    data object HideKeyboard : Eff()
    data object NavigateToPayments : Eff()
    data class ShowTokenFailToast(val message: String) : Eff()
}
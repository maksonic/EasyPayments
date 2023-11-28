package ru.maksonic.easypayments.feature.ui.core

import ru.maksonic.easypayments.common.core.elm.ElmUpdate
import ru.maksonic.easypayments.common.core.elm.Sandbox

/**
 * @Author maksonic on 27.11.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class AuthSandbox(program: AuthProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Ui.OnAuthBtnClicked -> onAuthBtnClicked(model)
        is Msg.Inner.UpdatedEmailInput -> updatedEmailInput(model, msg)
        is Msg.Inner.UpdatedPasswordInput -> updatedPasswordInput(model, msg)
        is Msg.Inner.AuthResult -> ElmUpdate(model)
        is Msg.Inner.InputsVerificationResult -> inputsVerificationResult(model, msg)
    }

    private fun onAuthBtnClicked(model: Model): Update =
        ElmUpdate(model, commands = setOf(Cmd.VerifyInputs(model.email, model.password)))

    private fun updatedEmailInput(model: Model, msg: Msg.Inner.UpdatedEmailInput): Update {
        val isNotIdle = !model.emailState.isIdle && model.email != msg.value
        val emailState = if (isNotIdle) VerificationEmailState.Idle else model.emailState

        return ElmUpdate(model.copy(email = msg.value, emailState = emailState))
    }

    private fun updatedPasswordInput(model: Model, msg: Msg.Inner.UpdatedPasswordInput): Update {
        val isNotIdle = !model.passwordState.isIdle && model.password != msg.value
        val passwordState = if (isNotIdle) VerificationPasswordState.Idle else model.passwordState

        return ElmUpdate(model.copy(password = msg.value, passwordState = passwordState))
    }

    private fun inputsVerificationResult(
        model: Model,
        msg: Msg.Inner.InputsVerificationResult
    ): Update {
        val isValid = msg.emailState.isValid && msg.passwordState.isValid
        val authCmd = if (isValid) setOf(Cmd.StartAuth(model.email, model.password)) else emptySet()

        return ElmUpdate(
            model = model.copy(emailState = msg.emailState, passwordState = msg.passwordState),
            commands = authCmd
        )
    }
}
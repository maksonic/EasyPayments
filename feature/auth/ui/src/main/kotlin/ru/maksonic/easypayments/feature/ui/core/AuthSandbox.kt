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
        is Msg.Inner.UpdatedUsernameInput -> updatedUsernameInput(model, msg)
        is Msg.Inner.UpdatedPasswordInput -> updatedPasswordInput(model, msg)
        is Msg.Inner.AuthResult -> ElmUpdate(model)
        is Msg.Inner.InputsVerificationResult -> inputsVerificationResult(model, msg)
        is Msg.Inner.FetchedValidTokenStatus -> fetchedValidToken(model)
        is Msg.Inner.FetchedInvalidTokenStatus -> fetchedInvalidToken(model, msg)

    }

    private fun onAuthBtnClicked(model: Model): Update = ElmUpdate(
        model = model.copy(isVisibleLoader = true),
        commands = setOf(Cmd.VerifyInputs(model.username, model.password)),
        effects = setOf(Eff.HideKeyboard)
    )

    private fun updatedUsernameInput(model: Model, msg: Msg.Inner.UpdatedUsernameInput): Update {
        val isNotIdle = !model.usernameState.isIdle && model.username != msg.value
        val nameState = if (isNotIdle) VerificationUsernameState.Idle else model.usernameState

        return ElmUpdate(model.copy(username = msg.value, usernameState = nameState))
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
        val isValid = msg.usernameState.isValid && msg.passwordState.isValid
        val authCmd =
            if (isValid) setOf(Cmd.StartAuth(model.username, model.password)) else emptySet()

        val keyboardEffect = if (isValid) setOf(Eff.HideKeyboard) else emptySet()

        return ElmUpdate(
            model = model.copy(
                isVisibleLoader = isValid,
                usernameState = msg.usernameState,
                passwordState = msg.passwordState
            ),
            commands = authCmd,
            effects = keyboardEffect
        )
    }

    private fun fetchedValidToken(model: Model): Update =
        ElmUpdate(model.copy(isVisibleLoader = false), effects = setOf(Eff.NavigateToPayments))

    private fun fetchedInvalidToken(
        model: Model,
        msg: Msg.Inner.FetchedInvalidTokenStatus
    ): Update = ElmUpdate(
        model = model.copy(isVisibleLoader = false),
        effects = setOf(Eff.ShowTokenFailToast(msg.cause))
    )
}
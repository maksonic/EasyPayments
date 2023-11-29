package ru.maksonic.easypayments.feature.settings.core

import ru.maksonic.easypayments.common.core.elm.ElmUpdate
import ru.maksonic.easypayments.common.core.elm.Sandbox

/**
 * @Author maksonic on 29.11.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class SettingsSandbox(program: SettingsProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.CheckTokenValidity),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Ui.OnTopBarBackClicked -> onTopBarBackClicked(model)
        is Msg.Ui.OnLogOutClicked -> onLogOutClicked(model)
        is Msg.Inner.FetchedTokenStatus -> fetchedTokenStatus(model, msg)
        is Msg.Inner.SuccessfulLogOut -> successfulLogOut(model)
        is Msg.Inner.ShowedLogOutError -> showedLogOutError(model, msg)
        is Msg.Ui.OnAuthClicked -> onAuthClicked(model)
    }

    private fun onTopBarBackClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateBack))

    private fun onLogOutClicked(model: Model): Update =
        ElmUpdate(model, commands = setOf(Cmd.LogOut))

    private fun fetchedTokenStatus(model: Model, msg: Msg.Inner.FetchedTokenStatus): Update =
        ElmUpdate(model.copy(isLogOutBtn = msg.isValid))

    private fun successfulLogOut(model: Model): Update =
        ElmUpdate(model.copy(isLogOutBtn = false), effects = setOf(Eff.ShowLogOutSuccessToast))

    private fun showedLogOutError(model: Model, msg: Msg.Inner.ShowedLogOutError): Update =
        ElmUpdate(model, effects = setOf(Eff.ShowLogOutErrorToast(msg.cause)))

    private fun onAuthClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateToAuth))
}
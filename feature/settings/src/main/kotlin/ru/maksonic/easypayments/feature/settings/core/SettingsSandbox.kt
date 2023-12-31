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
        is Msg.Ui.OnShowLogOutDialogClicked -> onShowLogOutDialogClicked(model)
        is Msg.Ui.OnLogOutClicked -> onLogOutClicked(model)
        is Msg.Inner.FetchedTokenStatus -> fetchedTokenStatus(model, msg)
        is Msg.Ui.OnAuthClicked -> onAuthClicked(model)
        is Msg.Ui.OnDeclineLogOutDialogClicked -> onDeclineLogOutDialogClicked(model)
    }

    private fun onTopBarBackClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateBack))

    private fun onShowLogOutDialogClicked(model: Model): Update =
        ElmUpdate(model.copy(isVisibleLogOutDialog = true))

    private fun onLogOutClicked(model: Model): Update = ElmUpdate(
        model = model.copy(isLogOutBtn = false, isVisibleLogOutDialog = false),
        commands = setOf(Cmd.LogOut),
        effects = setOf(Eff.ShowLogOutSuccessToast)
    )

    private fun fetchedTokenStatus(model: Model, msg: Msg.Inner.FetchedTokenStatus): Update =
        ElmUpdate(model.copy(isLogOutBtn = msg.isValid))

    private fun onAuthClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateToAuth))

    private fun onDeclineLogOutDialogClicked(model: Model): Update =
        ElmUpdate(model.copy(isVisibleLogOutDialog = false))
}
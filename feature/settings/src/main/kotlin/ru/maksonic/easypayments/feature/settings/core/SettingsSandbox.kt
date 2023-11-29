package ru.maksonic.easypayments.feature.settings.core

import ru.maksonic.easypayments.common.core.elm.ElmUpdate
import ru.maksonic.easypayments.common.core.elm.Sandbox

/**
 * @Author maksonic on 29.11.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class SettingsSandbox(program: SettingsProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Ui.OnTopBarBackClicked -> onTopBarBackClicked(model)
    }

    private fun onTopBarBackClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateBack))
}
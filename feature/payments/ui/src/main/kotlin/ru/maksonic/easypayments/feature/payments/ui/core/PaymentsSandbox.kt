package ru.maksonic.easypayments.feature.payments.ui.core

import ru.maksonic.easypayments.common.core.elm.ElmUpdate
import ru.maksonic.easypayments.common.core.elm.Sandbox

/**
 * @Author maksonic on 29.11.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class PaymentsSandbox(program: PaymentsProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Ui.OnSettingBtnClicked -> onSettingBtnClicked(model)
    }

    private fun onSettingBtnClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateToSettings))
}
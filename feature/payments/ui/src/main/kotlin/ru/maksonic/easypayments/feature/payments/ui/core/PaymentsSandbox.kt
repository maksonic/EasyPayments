package ru.maksonic.easypayments.feature.payments.ui.core

import ru.maksonic.easypayments.common.core.elm.ElmUpdate
import ru.maksonic.easypayments.common.core.elm.Sandbox

/**
 * @Author maksonic on 29.11.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class PaymentsSandbox(program: PaymentsProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchPayments),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Ui.OnSettingBtnClicked -> onSettingBtnClicked(model)
        is Msg.Ui.OnAuthBtnClicked -> onAuthBtnClicked(model)
        is Msg.Ui.OnPaymentClicked -> onPaymentClicked(model, msg)
        is Msg.Ui.OnRetryFetchPaymentsBtnClicked -> onRetryFetchPaymentsBtnClicked(model)
        is Msg.Inner.FetchedPayments -> fetchedPayments(model, msg)
        is Msg.Inner.CheckTokenInvalidStatus -> checkTokenInvalidStatus(model)
    }

    private fun onSettingBtnClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateToSettings))

    private fun onAuthBtnClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateToAuth))

    private fun onPaymentClicked(model: Model, msg: Msg.Ui.OnPaymentClicked): Update =
        ElmUpdate(model, effects = setOf(Eff.ShowPaymentTitleToast(msg.title)))

    private fun onRetryFetchPaymentsBtnClicked(model: Model): Update = ElmUpdate(
        model = model.copy(paymentsState = PaymentsState.Loading),
        commands = setOf(Cmd.RetryFetchPayments)
    )

    private fun fetchedPayments(model: Model, msg: Msg.Inner.FetchedPayments): Update =
        ElmUpdate(model.copy(paymentsState = msg.state, payments = msg.data))

    private fun checkTokenInvalidStatus(model: Model): Update =
        ElmUpdate(model, commands = setOf(Cmd.CheckTokenInvalidStatus))

}
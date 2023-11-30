package ru.maksonic.easypayments.feature.payments.ui.core

import ru.maksonic.easypayments.common.core.elm.ElmCommand
import ru.maksonic.easypayments.common.core.elm.ElmEffect
import ru.maksonic.easypayments.common.core.elm.ElmMessage
import ru.maksonic.easypayments.common.core.elm.ElmModel
import ru.maksonic.easypayments.feature.payments.domain.Payment

/**
 * @Author maksonic on 29.11.2023
 */
sealed class PaymentsState {
    data object Loading : PaymentsState()
    data object Empty : PaymentsState()
    data object Success : PaymentsState()
    data object NotAuthorized : PaymentsState()
}

data class Model(
    val paymentsState: PaymentsState,
    val payments: List<Payment>
) : ElmModel {
    companion object {
        val Initial = Model(
            paymentsState = PaymentsState.Loading,
            payments = emptyList()
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnSettingBtnClicked : Ui()
        data object OnAuthBtnClicked : Ui()
        data class OnPaymentClicked(val title: String) : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedPayments(val state: PaymentsState, val data: List<Payment>) : Inner()
        data object CheckTokenInvalidStatus : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchPayments : Cmd()
    data object CheckTokenInvalidStatus : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateToSettings : Eff()
    data object NavigateToAuth : Eff()
    data class ShowPaymentTitleToast(val title: String) : Eff()
}
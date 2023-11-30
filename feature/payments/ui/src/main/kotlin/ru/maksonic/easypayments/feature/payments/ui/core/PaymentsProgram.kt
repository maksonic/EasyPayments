package ru.maksonic.easypayments.feature.payments.ui.core

import kotlinx.coroutines.delay
import ru.maksonic.easypayments.common.core.elm.ElmProgram
import ru.maksonic.easypayments.feature.domain.CheckTokenValidityUseCase
import ru.maksonic.easypayments.feature.domain.TokenStatus
import ru.maksonic.easypayments.feature.domain.isValid
import ru.maksonic.easypayments.feature.payments.domain.FetchPaymentsUseCase

/**
 * @Author maksonic on 29.11.2023
 */
private const val DELAY_RETRY_FETCH_DATA = 1000L

class PaymentsProgram(
    private val fetchPaymentsUseCase: FetchPaymentsUseCase,
    private val checkTokenValidityUseCase: CheckTokenValidityUseCase,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchPayments -> fetchPayments(consumer)
            is Cmd.RetryFetchPayments -> retryFetchPayments(consumer)
            is Cmd.CheckTokenInvalidStatus -> checkInvalidTokenStatus(consumer)
        }
    }

    private suspend fun fetchPayments(consumer: (Msg) -> Unit) =
        checkTokenValidityUseCase().collect { status ->
            when (status) {
                is TokenStatus.Valid -> {
                    val payments = fetchPaymentsUseCase()
                    if (payments.isNotEmpty()) {
                        consumer(Msg.Inner.FetchedPayments(PaymentsState.Success, payments))
                    } else {
                        consumer(Msg.Inner.FetchedPayments(PaymentsState.Empty, emptyList()))
                    }
                }

                is TokenStatus.Invalid -> {
                    consumer(Msg.Inner.FetchedPayments(PaymentsState.NotAuthorized, emptyList()))
                }
            }
        }

    private suspend fun retryFetchPayments(consumer: (Msg) -> Unit) {
        delay(DELAY_RETRY_FETCH_DATA)
        fetchPayments(consumer)
    }

    private suspend fun checkInvalidTokenStatus(consumer: (Msg) -> Unit) =
        checkTokenValidityUseCase().collect { status ->
            if (!status.isValid)
                consumer(Msg.Inner.FetchedPayments(PaymentsState.NotAuthorized, emptyList()))
        }
}
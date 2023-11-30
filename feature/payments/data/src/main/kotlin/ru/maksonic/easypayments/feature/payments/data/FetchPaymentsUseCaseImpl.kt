package ru.maksonic.easypayments.feature.payments.data

import ru.maksonic.easypayments.data.ApiService
import ru.maksonic.easypayments.feature.payments.domain.FetchPaymentsUseCase
import ru.maksonic.easypayments.feature.payments.domain.Payments

/**
 * @Author maksonic on 30.11.2023
 */
class FetchPaymentsUseCaseImpl(private val api: ApiService) : FetchPaymentsUseCase {
    override suspend fun invoke(): Payments = api.getPayments().response.toDomain()
}
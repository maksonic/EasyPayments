package ru.maksonic.easypayments.feature.payments.data

import ru.maksonic.easypayments.data.payments.PaymentCloudModel
import ru.maksonic.easypayments.feature.payments.domain.Payment

/**
 * @Author maksonic on 30.11.2023
 */
fun List<PaymentCloudModel>.toDomain() = this.map { it.toDomain() }

fun PaymentCloudModel.toDomain() = Payment(
    id = this.id,
    title = this.title,
    amount = this.amount,
    created = this.created
)
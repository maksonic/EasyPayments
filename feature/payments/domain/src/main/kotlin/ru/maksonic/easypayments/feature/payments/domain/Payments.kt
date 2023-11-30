package ru.maksonic.easypayments.feature.payments.domain

/**
 * @Author maksonic on 30.11.2023
 */
typealias Payments = List<Payment>

data class Payment(
    val id: Int,
    val title: String,
    val amount: Double?,
    val created: Long?
)
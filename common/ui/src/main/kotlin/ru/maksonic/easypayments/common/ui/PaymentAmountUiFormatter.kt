package ru.maksonic.easypayments.common.ui

/**
 * @Author maksonic on 30.11.2023
 */
interface PaymentAmountUiFormatter {
    fun format(value: Double): String
}
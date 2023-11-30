package ru.maksonic.easypayments.common.ui

/**
 * @Author maksonic on 30.11.2023
 */
interface PaymentDateUiFormatter {
    fun format(rawDate: Long): String
}
package ru.maksonic.easypayments.utils

import ru.maksonic.easypayments.common.ui.PaymentAmountUiFormatter
import java.math.BigDecimal
import java.text.DecimalFormat
import kotlin.math.roundToInt

/**
 * @Author maksonic on 30.11.2023
 */
private const val PATTERN_TENTHS_LEFT_AMOUNT = "#0.0"
private const val PATTERN_HUNDREDTHS_LEFT_AMOUNT = "#0.00"

class PaymentAmountUiFormatterCore : PaymentAmountUiFormatter {

    private fun Double.countPlaces(): Int {
        val bigDecimalValue = BigDecimal(this)
        val scale = bigDecimalValue.scale()
        return if (scale > 0) scale else 0
    }

    override fun format(value: Double): String = when {
        value.countPlaces() == 0 -> value.roundToInt().toString()
        value.countPlaces() == 1 -> DecimalFormat(PATTERN_TENTHS_LEFT_AMOUNT).format(value)
        else -> DecimalFormat(PATTERN_HUNDREDTHS_LEFT_AMOUNT).format(value)
    }
}
package ru.maksonic.easypayments.utils

import ru.maksonic.easypayments.common.ui.PaymentDateUiFormatter
import java.text.DateFormat
import java.util.Date
import java.util.Locale

/**
 * @Author maksonic on 30.11.2023
 */
class PaymentDateUiFormatterCore : PaymentDateUiFormatter {
    private companion object {
        private const val LOCALE_RUSSIAN = "ru"
    }

    override fun format(rawDate: Long): String {
        val dateStyle = DateFormat.MONTH_FIELD
        val timeStyle = DateFormat.DATE_FIELD
        val locale = Locale(LOCALE_RUSSIAN)
        val date = Date(rawDate * 1000)

        return DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale).format(date)
    }
}
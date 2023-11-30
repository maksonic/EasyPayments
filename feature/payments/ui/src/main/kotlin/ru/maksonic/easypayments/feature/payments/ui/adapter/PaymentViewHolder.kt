package ru.maksonic.easypayments.feature.payments.ui.adapter

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import ru.maksonic.easypayments.common.ui.PaymentAmountUiFormatter
import ru.maksonic.easypayments.common.ui.PaymentDateUiFormatter
import ru.maksonic.easypayments.feature.payments.domain.Payment
import ru.maksonic.easypayments.feature.payments.ui.R
import ru.maksonic.easypayments.feature.payments.ui.databinding.ItemPaymentBinding
import ru.maksonic.easypayments.common.ui.R.color.green

/**
 * @Author maksonic on 30.11.2023
 */

class ProductViewHolder(
    private val view: ItemPaymentBinding,
    private val onPaymentClicked: (Payment) -> Unit,
    private val amountFormatter: PaymentAmountUiFormatter,
    private val dateFormatter: PaymentDateUiFormatter
) : RecyclerView.ViewHolder(view.root) {

    fun bind(item: Payment) = with(view) {
        id.text = id.context.getString(R.string.title_payment_id, item.id)
        title.text = item.title
        created.text = date(item.created)

        amount.apply {
            setTextColor(if (item.amount != null) context.getColor(green) else Color.RED)
            text = amount(item.amount)
        }

        card.setOnClickListener { onPaymentClicked(item) }
    }

    private fun amount(value: Double?): String = value?.let {
        amountFormatter.format(value)
    } ?: view.amount.context.getString(R.string.title_empty_amount)

    private fun date(rawDate: Long?): String = rawDate?.let {
        dateFormatter.format(rawDate)
    } ?: view.created.context.getString(R.string.title_empty_created)
}
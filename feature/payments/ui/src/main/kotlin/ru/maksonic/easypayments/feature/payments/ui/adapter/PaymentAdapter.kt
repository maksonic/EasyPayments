package ru.maksonic.easypayments.feature.payments.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.maksonic.easypayments.common.ui.PaymentAmountUiFormatter
import ru.maksonic.easypayments.common.ui.PaymentDateUiFormatter
import ru.maksonic.easypayments.feature.payments.domain.Payment
import ru.maksonic.easypayments.feature.payments.ui.databinding.ItemPaymentBinding

/**
 * @Author maksonic on 30.11.2023
 */
class PaymentAdapter(
    private val amountFormatter: PaymentAmountUiFormatter,
    private val dateFormatter: PaymentDateUiFormatter,
    private val onPaymentClicked: (Payment) -> Unit
) : ListAdapter<Payment, ProductViewHolder>(CatalogItemDiffUtil()) {

    class CatalogItemDiffUtil : DiffUtil.ItemCallback<Payment>() {
        override fun areItemsTheSame(oldItem: Payment, newItem: Payment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Payment, newItem: Payment): Boolean {
            return oldItem == newItem
        }
    }

    override fun submitList(list: List<Payment>?) {
        super.submitList(list ?: emptyList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        view = ItemPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        amountFormatter = amountFormatter,
        dateFormatter = dateFormatter,
        onPaymentClicked = onPaymentClicked
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(this.currentList[position])
    }
}

package ru.maksonic.easypayments.feature.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.maksonic.easypayments.common.ui.BaseScreen
import ru.maksonic.easypayments.feature.payments.databinding.ScreenPaymentsBinding

/**
 * @Author maksonic on 28.11.2023
 */
class PaymentsScreen : BaseScreen<ScreenPaymentsBinding, Any, Any>() {
    override val initBinding: (LayoutInflater, ViewGroup?, Boolean) -> ScreenPaymentsBinding
        get() = ScreenPaymentsBinding::inflate

    override fun render(savedInstanceState: Bundle?) {
    }
}
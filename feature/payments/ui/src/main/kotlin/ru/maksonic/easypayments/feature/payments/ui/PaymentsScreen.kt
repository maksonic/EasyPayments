package ru.maksonic.easypayments.feature.payments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.easypayments.common.ui.BaseScreen
import ru.maksonic.easypayments.feature.payments.ui.core.Eff
import ru.maksonic.easypayments.feature.payments.ui.core.Model
import ru.maksonic.easypayments.feature.payments.ui.core.Msg
import ru.maksonic.easypayments.feature.payments.ui.core.PaymentsSandbox
import ru.maksonic.easypayments.feature.payments.ui.databinding.ScreenPaymentsBinding
import ru.maksonic.easypayments.navigation.router.Router

/**
 * @Author maksonic on 28.11.2023
 */
class PaymentsScreen : BaseScreen<ScreenPaymentsBinding, Model, Eff>() {
    override val initBinding: (LayoutInflater, ViewGroup?, Boolean) -> ScreenPaymentsBinding
        get() = ScreenPaymentsBinding::inflate

    private val router: Router by inject()
    private val sandbox: PaymentsSandbox by viewModel()

    override fun render(savedInstanceState: Bundle?) {
        binding.toolBar.setNavigationOnClickListener { sandbox.send(Msg.Ui.OnSettingBtnClicked) }

        sandbox.model.render()
        sandbox.effects.handle()
    }

    override fun renderModel(model: Model) {
    }

    override fun handleEffects(eff: Eff) {
        when (eff) {
            is Eff.NavigateToSettings -> router.navigateFromPaymentsToSettings(this)
        }
    }
}
package ru.maksonic.easypayments.feature.payments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.easypayments.common.ui.BaseScreen
import ru.maksonic.easypayments.common.ui.PaymentAmountUiFormatter
import ru.maksonic.easypayments.common.ui.PaymentDateUiFormatter
import ru.maksonic.easypayments.common.ui.toastShortTime
import ru.maksonic.easypayments.feature.payments.domain.Payment
import ru.maksonic.easypayments.feature.payments.ui.adapter.PaymentAdapter
import ru.maksonic.easypayments.feature.payments.ui.core.Eff
import ru.maksonic.easypayments.feature.payments.ui.core.Model
import ru.maksonic.easypayments.feature.payments.ui.core.Msg
import ru.maksonic.easypayments.feature.payments.ui.core.PaymentsSandbox
import ru.maksonic.easypayments.feature.payments.ui.core.PaymentsState
import ru.maksonic.easypayments.feature.payments.ui.databinding.ScreenPaymentsBinding
import ru.maksonic.easypayments.navigation.router.Router

/**
 * @Author maksonic on 28.11.2023
 */
class PaymentsScreen : BaseScreen<ScreenPaymentsBinding, Model, Eff>() {
    override val initBinding: (LayoutInflater, ViewGroup?, Boolean) -> ScreenPaymentsBinding
        get() = ScreenPaymentsBinding::inflate

    private val adapter: PaymentAdapter by lazy(::initPaymentAdapter)
    private val amountFormatter: PaymentAmountUiFormatter by inject()
    private val dateFormatter: PaymentDateUiFormatter by inject()
    private val router: Router by inject()
    private val sandbox: PaymentsSandbox by viewModel()

    override fun onResume() {
        sandbox.send(Msg.Inner.CheckTokenInvalidStatus)
        super.onResume()
    }

    override fun render(savedInstanceState: Bundle?) {
        initRecycler()
        initClickListeners()
        initTopBarClickListener()

        sandbox.model.render()
        sandbox.effects.handle()
    }

    override fun renderModel(model: Model) {
        when (model.paymentsState) {
            is PaymentsState.Loading -> loadingState()
            is PaymentsState.Empty -> emptyState()
            is PaymentsState.NotAuthorized -> notAuthorizedState()
            is PaymentsState.Success -> successState(model.payments)
        }
    }

    override fun handleEffects(eff: Eff) {
        when (eff) {
            is Eff.NavigateToSettings -> router.navigateFromPaymentsToSettings(this)
            is Eff.NavigateToAuth -> router.navigateFromPaymentsToAuth(this)
            is Eff.ShowPaymentTitleToast -> context?.toastShortTime(eff.title)
        }
    }

    private fun initClickListeners() = with(binding) {
        val listener = View.OnClickListener { view ->
            when (view?.id) {
                stateNotAuthorized.btnAuth.id -> sandbox.send(Msg.Ui.OnAuthBtnClicked)
                stateEmptyList.btnRetry.id -> sandbox.send(Msg.Ui.OnRetryFetchPaymentsBtnClicked)
            }
        }
        stateNotAuthorized.btnAuth.setOnClickListener(listener)
        stateEmptyList.btnRetry.setOnClickListener(listener)
    }

    private fun initTopBarClickListener() = binding.toolBar.setNavigationOnClickListener {
        sandbox.send(Msg.Ui.OnSettingBtnClicked)
    }

    private fun initPaymentAdapter() = PaymentAdapter(amountFormatter, dateFormatter) { payment ->
        sandbox.send(Msg.Ui.OnPaymentClicked(payment.title))
    }

    private fun initRecycler() {
        binding.catalogRecyclerView.adapter = adapter
    }

    private fun loadingState() = with(binding) {
        stateNotAuthorized.root.visibility = View.GONE
        stateEmptyList.root.visibility = View.GONE
        stateLoading.root.visibility = View.VISIBLE
    }

    private fun emptyState() = with(binding) {
        stateNotAuthorized.root.visibility = View.GONE
        stateEmptyList.root.visibility = View.VISIBLE
        stateLoading.root.visibility = View.GONE
    }

    private fun notAuthorizedState() = with(binding) {
        catalogRecyclerView.visibility = View.GONE
        stateEmptyList.root.visibility = View.GONE
        stateNotAuthorized.root.visibility = View.VISIBLE
    }

    private fun successState(payments: List<Payment>) = with(binding) {
        stateNotAuthorized.root.visibility = View.GONE
        stateEmptyList.root.visibility = View.GONE
        stateLoading.root.visibility = View.GONE
        catalogRecyclerView.visibility = View.VISIBLE
        adapter.submitList(payments)
    }
}
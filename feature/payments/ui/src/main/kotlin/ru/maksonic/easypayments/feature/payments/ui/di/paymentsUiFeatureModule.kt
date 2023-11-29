package ru.maksonic.easypayments.feature.payments.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.easypayments.feature.payments.ui.core.PaymentsProgram
import ru.maksonic.easypayments.feature.payments.ui.core.PaymentsSandbox

/**
 * @Author maksonic on 29.11.2023
 */
val paymentsUiFeatureModule = module {
    factory { PaymentsProgram() }
    viewModel { PaymentsSandbox(program = get()) }
}
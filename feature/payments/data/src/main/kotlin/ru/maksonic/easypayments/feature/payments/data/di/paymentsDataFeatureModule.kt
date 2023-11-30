package ru.maksonic.easypayments.feature.payments.data.di

import org.koin.dsl.module
import ru.maksonic.easypayments.feature.payments.data.FetchPaymentsUseCaseImpl
import ru.maksonic.easypayments.feature.payments.domain.FetchPaymentsUseCase

/**
 * @Author maksonic on 30.11.2023
 */
val paymentsDataFeatureModule = module {
    factory<FetchPaymentsUseCase> { FetchPaymentsUseCaseImpl(api = get()) }
}
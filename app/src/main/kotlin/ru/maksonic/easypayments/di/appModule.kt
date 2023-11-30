package ru.maksonic.easypayments.di

import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.easypayments.MainActivityViewModel
import ru.maksonic.easypayments.ResourceProviderCore
import ru.maksonic.easypayments.common.core.CoroutineDispatchers
import ru.maksonic.easypayments.common.core.CryptoEngine
import ru.maksonic.easypayments.common.ui.PaymentAmountUiFormatter
import ru.maksonic.easypayments.common.ui.PaymentDateUiFormatter
import ru.maksonic.easypayments.common.ui.ResourceProvider
import ru.maksonic.easypayments.utils.CryptoEngineCore
import ru.maksonic.easypayments.utils.PaymentAmountUiFormatterCore
import ru.maksonic.easypayments.utils.PaymentDateUiFormatterCore

/**
 * @Author maksonic on 27.11.2023
 */
val appModule = module {
    viewModel { MainActivityViewModel(checkTokenValidityUseCase = get()) }
    single { Glide.with((androidContext())) }
    single<ResourceProvider> { ResourceProviderCore(androidContext()) }
    single(named(CoroutineDispatchers.IO)) { Dispatchers.IO }
    single(named(CoroutineDispatchers.DEFAULT)) { Dispatchers.Default }
    single(named(CoroutineDispatchers.MAIN)) { Dispatchers.Main }
    single<CryptoEngine> { CryptoEngineCore() }
    factory<PaymentAmountUiFormatter> { PaymentAmountUiFormatterCore() }
    factory<PaymentDateUiFormatter> { PaymentDateUiFormatterCore() }
}

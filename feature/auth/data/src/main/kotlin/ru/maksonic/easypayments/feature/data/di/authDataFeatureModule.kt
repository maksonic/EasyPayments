package ru.maksonic.easypayments.feature.data.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.easypayments.common.core.CoroutineDispatchers
import ru.maksonic.easypayments.feature.data.AuthLogOutUseCaseImpl
import ru.maksonic.easypayments.feature.data.AuthRepositoryCore
import ru.maksonic.easypayments.feature.data.CheckTokenValidityUseCaseImpl
import ru.maksonic.easypayments.feature.data.TokenStore
import ru.maksonic.easypayments.feature.domain.AuthLogOutUseCase
import ru.maksonic.easypayments.feature.domain.AuthRepository
import ru.maksonic.easypayments.feature.domain.CheckTokenValidityUseCase

/**
 * @Author maksonic on 28.11.2023
 */
val authDataFeatureModule = module {
    factory {
        TokenStore(
            context = androidContext(),
            cryptoEngine = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    single<AuthRepository> { AuthRepositoryCore(apiService = get(), tokenStore = get()) }
    factory<CheckTokenValidityUseCase> { CheckTokenValidityUseCaseImpl(repository = get()) }
    factory<AuthLogOutUseCase> { AuthLogOutUseCaseImpl(repository = get()) }
}
package ru.maksonic.easypayments

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.maksonic.easypayments.di.appModule
import ru.maksonic.easypayments.feature.onboarding.data.di.onboardingDataModule
import ru.maksonic.easypayments.feature.onboarding.data.local.di.onboardingLocalDataModule
import ru.maksonic.easypayments.feature.onboarding.ui.di.onboardingUiModule

/**
 * @Author maksonic on 27.11.2023
 */
class EasyPaymentApp : Application() {
    private val modules = listOf(
        appModule,
        onboardingLocalDataModule,
        onboardingDataModule,
        onboardingUiModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@EasyPaymentApp)
            modules(modules)
        }
    }
}
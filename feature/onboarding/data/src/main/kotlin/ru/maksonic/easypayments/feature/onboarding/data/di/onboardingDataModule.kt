package ru.maksonic.easypayments.feature.onboarding.data.di

import org.koin.dsl.module
import ru.maksonic.easypayments.feature.onboarding.data.OnboardingRepositoryCore
import ru.maksonic.easypayments.feature.onboarding.domain.OnboardingRepository

/**
 * @Author maksonic on 27.11.2023
 */
val onboardingDataModule = module {
    factory<OnboardingRepository> {
        OnboardingRepositoryCore(
            onboardingsLocalData = get(),
            onboardingLocalMapper = get()
        )
    }
}
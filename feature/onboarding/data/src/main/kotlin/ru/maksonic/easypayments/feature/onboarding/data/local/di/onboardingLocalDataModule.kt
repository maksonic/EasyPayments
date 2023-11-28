package ru.maksonic.easypayments.feature.onboarding.data.local.di

import org.koin.dsl.module
import ru.maksonic.easypayments.feature.onboarding.data.local.OnboardingLocalMapper
import ru.maksonic.easypayments.feature.onboarding.data.local.OnboardingsLocalData

/**
 * @Author maksonic on 27.11.2023
 */
val onboardingLocalDataModule = module {
    factory<OnboardingLocalMapper> { OnboardingLocalMapper.Core() }
    factory { OnboardingsLocalData() }
}
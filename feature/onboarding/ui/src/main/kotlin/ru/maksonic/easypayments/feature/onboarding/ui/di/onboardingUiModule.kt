package ru.maksonic.easypayments.feature.onboarding.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.easypayments.feature.onboarding.ui.core.OnboardingProgram
import ru.maksonic.easypayments.feature.onboarding.ui.core.OnboardingSandbox

/**
 * @Author maksonic on 27.11.2023
 */
val onboardingUiModule = module {
    factory { OnboardingProgram(repository = get()) }
    viewModel { OnboardingSandbox(program = get()) }
}
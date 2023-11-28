package ru.maksonic.easypayments.feature.onboarding.domain

/**
 * @Author maksonic on 27.11.2023
 */
interface OnboardingRepository {
    fun fetchOnboardings(): List<OnboardingUi>
}
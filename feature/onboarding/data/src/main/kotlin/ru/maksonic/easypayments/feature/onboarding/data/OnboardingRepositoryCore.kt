package ru.maksonic.easypayments.feature.onboarding.data

import ru.maksonic.easypayments.feature.onboarding.data.local.OnboardingLocalMapper
import ru.maksonic.easypayments.feature.onboarding.data.local.OnboardingsLocalData
import ru.maksonic.easypayments.feature.onboarding.domain.OnboardingUi
import ru.maksonic.easypayments.feature.onboarding.domain.OnboardingRepository

/**
 * @Author maksonic on 27.11.2023
 */
class OnboardingRepositoryCore(
    private val onboardingsLocalData: OnboardingsLocalData,
    private val onboardingLocalMapper: OnboardingLocalMapper
) : OnboardingRepository {
    override fun fetchOnboardings(): List<OnboardingUi> =
        onboardingLocalMapper.mapListTo(onboardingsLocalData.list)
}
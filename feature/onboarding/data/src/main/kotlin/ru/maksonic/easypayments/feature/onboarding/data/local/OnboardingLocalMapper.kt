package ru.maksonic.easypayments.feature.onboarding.data.local

import ru.maksonic.easypayments.common.core.Mapper
import ru.maksonic.easypayments.feature.onboarding.domain.OnboardingUi

/**
 * @Author maksonic on 27.11.2023
 */
interface OnboardingLocalMapper : Mapper<OnboardingLocal, OnboardingUi> {

    class Core : OnboardingLocalMapper {
        override fun mapTo(i: OnboardingLocal) = OnboardingUi(i.id, i.titleResId, i.imageResId)
        override fun mapFrom(o: OnboardingUi) = OnboardingLocal(o.id, o.titleResId, o.imageResId)
    }
}


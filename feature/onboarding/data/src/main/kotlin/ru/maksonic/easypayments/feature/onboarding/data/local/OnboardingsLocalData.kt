package ru.maksonic.easypayments.feature.onboarding.data.local

import ru.maksonic.easypayments.feature.onboarding.data.R

/**
 * @Author maksonic on 27.11.2023
 */
class OnboardingsLocalData {
    val list = listOf(
        OnboardingLocal(
            id = 0,
            titleResId = R.string.title_onb_01,
            imageResId = R.drawable.img_onb_01
        ),
        OnboardingLocal(
            id = 1,
            titleResId = R.string.title_onb_02,
            imageResId = R.drawable.img_onb_02
        ),
        OnboardingLocal(
            id = 2,
            titleResId = R.string.title_onb_03,
            imageResId = R.drawable.img_onb_03
        ),
        OnboardingLocal(
            id = 3,
            titleResId = R.string.title_onb_04,
            imageResId = R.drawable.img_onb_04
        ),
        OnboardingLocal(
            id = 4,
            titleResId = R.string.title_onb_05,
            imageResId = R.drawable.img_onb_05
        )
    )
}
package ru.maksonic.easypayments.feature.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.maksonic.easypayments.common.ui.BaseScreen
import ru.maksonic.easypayments.feature.onboarding.databinding.ScreenOnboardingBinding

/**
 * @Author maksonic on 27.11.2023
 */
class OnboardingScreen : BaseScreen<ScreenOnboardingBinding>() {
    override val initBinding: (LayoutInflater, ViewGroup?, Boolean) -> ScreenOnboardingBinding
        get() = ScreenOnboardingBinding::inflate

    override fun render(savedInstanceState: Bundle?) {

    }
}
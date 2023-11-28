package ru.maksonic.easypayments.feature.onboarding.ui.core

import ru.maksonic.easypayments.common.core.elm.ElmCommand
import ru.maksonic.easypayments.common.core.elm.ElmEffect
import ru.maksonic.easypayments.common.core.elm.ElmMessage
import ru.maksonic.easypayments.common.core.elm.ElmModel
import ru.maksonic.easypayments.feature.onboarding.domain.OnboardingUi

/**
 * @Author maksonic on 27.11.2023
 */
data class Model(
    val onboardings: List<OnboardingUi>
) : ElmModel {
    companion object {
        val Initial = Model(onboardings = emptyList())
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnBtnNextClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedOnboardingsResult(val onboardings: List<OnboardingUi>) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchOnboardings : Cmd()
}

sealed class Eff : ElmEffect
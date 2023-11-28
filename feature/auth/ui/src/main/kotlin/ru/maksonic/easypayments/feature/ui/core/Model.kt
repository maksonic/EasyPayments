package ru.maksonic.easypayments.feature.ui.core

import ru.maksonic.easypayments.common.core.elm.ElmCommand
import ru.maksonic.easypayments.common.core.elm.ElmEffect
import ru.maksonic.easypayments.common.core.elm.ElmMessage
import ru.maksonic.easypayments.common.core.elm.ElmModel
import ru.maksonic.easypayments.feature.onboarding.domain.OnboardingUi

/**
 * @Author maksonic on 27.11.2023
 */
data class Model(
) : ElmModel {
    companion object {
        val Initial = Model()
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
    }

    sealed class Inner : Msg() {
    }
}

sealed class Cmd : ElmCommand {
}

sealed class Eff : ElmEffect
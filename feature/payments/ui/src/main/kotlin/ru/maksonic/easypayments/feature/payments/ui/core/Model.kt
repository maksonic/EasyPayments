package ru.maksonic.easypayments.feature.payments.ui.core

import ru.maksonic.easypayments.common.core.elm.ElmCommand
import ru.maksonic.easypayments.common.core.elm.ElmEffect
import ru.maksonic.easypayments.common.core.elm.ElmMessage
import ru.maksonic.easypayments.common.core.elm.ElmModel

/**
 * @Author maksonic on 29.11.2023
 */
data class Model(
    val isVisibleLoader: Boolean
    ) : ElmModel {
    companion object {
        val Initial = Model(
            isVisibleLoader = false,
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnSettingBtnClicked : Ui()
    }

    sealed class Inner : Msg() {

    }
}

sealed class Cmd : ElmCommand {
    data class StartAuth(val username: String, val password: String) : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateToSettings : Eff()
}
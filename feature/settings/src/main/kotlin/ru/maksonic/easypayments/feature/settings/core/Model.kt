package ru.maksonic.easypayments.feature.settings.core

import ru.maksonic.easypayments.common.core.elm.ElmCommand
import ru.maksonic.easypayments.common.core.elm.ElmEffect
import ru.maksonic.easypayments.common.core.elm.ElmMessage
import ru.maksonic.easypayments.common.core.elm.ElmModel

/**
 * @Author maksonic on 29.11.2023
 */
data class Model(
    val isLogOutBtn: Boolean
    ) : ElmModel {
    companion object {
        val Initial = Model(
            isLogOutBtn = false,
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnTopBarBackClicked : Ui()
        data object OnLogOutClicked : Ui()
        data object OnAuthClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class ShowedLogOutError(val cause: String) : Inner()
        data class FetchedTokenStatus(val isValid: Boolean) : Inner()
        data object SuccessfulLogOut : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object LogOut : Cmd()
    data object CheckTokenValidity : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
    data object NavigateToAuth : Eff()
    data object ShowLogOutSuccessToast : Eff()
    data class ShowLogOutErrorToast(val message: String) : Eff()
}
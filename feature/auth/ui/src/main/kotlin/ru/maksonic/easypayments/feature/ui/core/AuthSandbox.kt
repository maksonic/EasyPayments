package ru.maksonic.easypayments.feature.ui.core

import ru.maksonic.easypayments.common.core.elm.ElmUpdate
import ru.maksonic.easypayments.common.core.elm.Sandbox

/**
 * @Author maksonic on 27.11.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class AuthSandbox(program: AuthProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): Update = when (msg) {
       is
    }

}
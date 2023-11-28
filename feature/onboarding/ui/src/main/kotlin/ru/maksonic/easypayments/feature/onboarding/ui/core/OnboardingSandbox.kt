package ru.maksonic.easypayments.feature.onboarding.ui.core

import ru.maksonic.easypayments.common.core.elm.ElmUpdate
import ru.maksonic.easypayments.common.core.elm.Sandbox

/**
 * @Author maksonic on 27.11.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class OnboardingSandbox(
    onboardingCommandHandler: OnboardingDataProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchOnboardings),
    subscriptions = listOf(onboardingCommandHandler)
) {
    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Ui.OnBtnNextClicked -> onNextBtnClicked(model)
        is Msg.Inner.FetchedOnboardingsResult -> fetchedOnboardingsResult(model, msg)
    }

    private fun onNextBtnClicked(state: Model): Update = ElmUpdate(state)

    private fun fetchedOnboardingsResult(
        state: Model,
        msg: Msg.Inner.FetchedOnboardingsResult
    ): Update = ElmUpdate(state.copy(onboardings = msg.onboardings))
}
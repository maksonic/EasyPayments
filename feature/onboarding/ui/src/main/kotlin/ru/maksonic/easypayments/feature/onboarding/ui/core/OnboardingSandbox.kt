package ru.maksonic.easypayments.feature.onboarding.ui.core

import ru.maksonic.easypayments.common.core.elm.ElmUpdate
import ru.maksonic.easypayments.common.core.elm.Sandbox

/**
 * @Author maksonic on 27.11.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

private const val LAST_PAGE = 4

class OnboardingSandbox(program: OnboardingProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchOnboardings),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Ui.OnBtnNextClicked -> onNextBtnClicked(model)
        is Msg.Inner.FetchedOnboardingsResult -> fetchedOnboardingsResult(model, msg)
        is Msg.Inner.UpdatedCurrentPage -> updatedCurrentPage(model, msg)
    }

    private fun onNextBtnClicked(model: Model): Update = ElmUpdate(
        model = model,
        effects = setOf(if (model.currentPage == LAST_PAGE) Eff.NavigateToAuth else Eff.ScrollToNextPage)
    )

    private fun fetchedOnboardingsResult(
        model: Model,
        msg: Msg.Inner.FetchedOnboardingsResult
    ): Update = ElmUpdate(model.copy(onboardings = msg.onboardings))

    private fun updatedCurrentPage(
        model: Model,
        msg: Msg.Inner.UpdatedCurrentPage
    ): Update = ElmUpdate(model.copy(currentPage = msg.page))
}
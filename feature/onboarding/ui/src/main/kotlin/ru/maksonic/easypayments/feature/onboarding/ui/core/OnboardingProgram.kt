package ru.maksonic.easypayments.feature.onboarding.ui.core

import ru.maksonic.easypayments.common.core.elm.ElmProgram
import ru.maksonic.easypayments.feature.onboarding.domain.OnboardingRepository

/**
 * @Author maksonic on 27.11.2023
 */
class OnboardingProgram(
    private val repository: OnboardingRepository
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchOnboardings -> fetchOnboardings(consumer)
        }
    }

    private fun fetchOnboardings(consumer: (Msg) -> Unit) {
        consumer(Msg.Inner.FetchedOnboardingsResult(repository.fetchOnboardings()))
    }
}
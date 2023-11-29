package ru.maksonic.easypayments.feature.ui.core

import kotlinx.coroutines.delay
import ru.maksonic.easypayments.common.core.elm.ElmProgram
import ru.maksonic.easypayments.common.ui.ResourceProvider
import ru.maksonic.easypayments.feature.auth.ui.R
import ru.maksonic.easypayments.feature.domain.AuthRepository
import ru.maksonic.easypayments.feature.domain.TokenStatus

/**
 * @Author maksonic on 28.11.2023
 */
private const val MIN_PASSWORD_LENGTH = 4
private typealias idlePasswordState = VerificationPasswordState.Idle

class AuthProgram(
    private val repository: AuthRepository,
    private val resourceProvider: ResourceProvider
) : ElmProgram<Msg, Cmd> {
    private companion object {
        private const val MOCK_NAME = "demo"
        private const val MOCK_PASSWORD = "12345"
        private const val MOCK_DELAY = 2000L
    }

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.StartAuth -> startAuth(cmd.username, cmd.password, consumer)
            is Cmd.VerifyInputs -> verifyInputs(cmd.username, cmd.password, consumer)
        }
    }

    private suspend fun verifyInputs(name: String, password: String, consumer: (Msg) -> Unit) {
        delay(MOCK_DELAY)
        val nameState = verifyUsername(name)
        val passwordState = verifyPassword(password)

        when {
            nameState.isValid && !passwordState.isValid -> {
                consumer(Msg.Inner.InputsVerificationResult(nameState, passwordState))
            }

            !nameState.isValid && passwordState.isValid -> {
                consumer(Msg.Inner.InputsVerificationResult(nameState, idlePasswordState))
            }

            !nameState.isValid && !passwordState.isValid -> {
                consumer(Msg.Inner.InputsVerificationResult(nameState, idlePasswordState))
            }

            nameState.isValid && passwordState.isValid -> {
                consumer(Msg.Inner.InputsVerificationResult(nameState, passwordState))
            }
        }
    }

    private fun verifyUsername(name: String): VerificationUsernameState = when {
        name.isBlank() -> {
            VerificationUsernameState.Empty(
                resourceProvider.getString(R.string.error_empty_username)
            )
        }

        name != MOCK_NAME -> {
            VerificationUsernameState.Invalid(
                resourceProvider.getString(R.string.error_invalid_username)
            )
        }

        else -> VerificationUsernameState.Valid
    }

    private fun verifyPassword(password: String): VerificationPasswordState = when {
        password.isBlank() -> {
            VerificationPasswordState.Empty(
                resourceProvider.getString(R.string.error_empty_password)
            )
        }

        password.length < MIN_PASSWORD_LENGTH -> {
            VerificationPasswordState.InvalidLength(
                resourceProvider.getString(R.string.error_invalid_length_password)
            )
        }

        password != MOCK_PASSWORD -> {
            VerificationPasswordState.InvalidLength(
                resourceProvider.getString(R.string.error_invalid_password)
            )
        }

        else -> VerificationPasswordState.Valid
    }

    private suspend fun startAuth(name: String, password: String, consumer: (Msg) -> Unit) {
        delay(MOCK_DELAY)
        repository.authWithNameAndPassword(name, password)
            .onSuccess { status ->
                when (status) {
                    is TokenStatus.Valid -> {
                        consumer(Msg.Inner.FetchedValidTokenStatus)
                    }

                    is TokenStatus.Invalid -> {
                        consumer(Msg.Inner.FetchedInvalidTokenStatus(status.cause))
                    }
                }
            }.onFailure {
                val failInfo = resourceProvider.getString(R.string.error_invalid_token)
                consumer(Msg.Inner.FetchedInvalidTokenStatus(it.localizedMessage ?: failInfo))
            }
    }
}

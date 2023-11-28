package ru.maksonic.easypayments.feature.ui.core

import android.util.Patterns
import ru.maksonic.easypayments.common.core.elm.ElmProgram
import ru.maksonic.easypayments.common.ui.ResourceProvider
import ru.maksonic.easypayments.feature.auth.ui.R

/**
 * @Author maksonic on 28.11.2023
 */
private const val MIN_PASSWORD_LENGTH = 8


class AuthProgram(
    private val resourceProvider: ResourceProvider
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.StartAuth -> startAuth(cmd.email, cmd.password, consumer)
            is Cmd.VerifyInputs -> verifyInputs(cmd.email, cmd.password, consumer)
        }
    }

    private fun verifyInputs(email: String, password: String, consumer: (Msg) -> Unit) {
        val emailState = verifyEmail(email)
        val passwordState = verifyPassword(password)

        consumer(Msg.Inner.InputsVerificationResult(emailState, passwordState))
    }

    private fun verifyEmail(email: String): VerificationEmailState = when {
        email.isBlank() -> {
            VerificationEmailState.Empty(resourceProvider.getString(R.string.error_empty_email))
        }

        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
            VerificationEmailState.Invalid(
                resourceProvider.getString(R.string.error_invalid_email)
            )
        }

        else -> VerificationEmailState.Valid
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

        else -> VerificationPasswordState.Valid
    }

    private fun startAuth(email: String, password: String, consumer: (Msg) -> Unit) {

    }
}

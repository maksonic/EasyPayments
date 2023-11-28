package ru.maksonic.easypayments.feature.ui.core

/**
 * @Author maksonic on 28.11.2023
 */
sealed class VerificationUsernameState(open val info: String = "") {
    data object Idle : VerificationUsernameState()
    data object Valid : VerificationUsernameState()
    data class Empty(override val info: String) : VerificationUsernameState()
    data class Invalid(override val info: String) : VerificationUsernameState()
}

val VerificationUsernameState.isIdle get() = this == VerificationUsernameState.Idle
val VerificationUsernameState.isValid get() = this == VerificationUsernameState.Valid


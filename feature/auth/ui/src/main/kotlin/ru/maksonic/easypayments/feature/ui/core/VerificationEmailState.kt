package ru.maksonic.easypayments.feature.ui.core

/**
 * @Author maksonic on 28.11.2023
 */
sealed class VerificationEmailState(open val info: String = "") {
    data object Idle : VerificationEmailState()
    data object Valid : VerificationEmailState()
    data class Empty(override val info: String) : VerificationEmailState()
    data class Invalid(override val info: String) : VerificationEmailState()
}

val VerificationEmailState.isIdle get() = this == VerificationEmailState.Idle
val VerificationEmailState.isValid get() = this == VerificationEmailState.Valid


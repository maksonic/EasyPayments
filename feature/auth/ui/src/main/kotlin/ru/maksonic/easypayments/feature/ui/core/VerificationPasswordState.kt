package ru.maksonic.easypayments.feature.ui.core

/**
 * @Author maksonic on 28.11.2023
 */
sealed class VerificationPasswordState(open val info: String = "") {
    data object Idle : VerificationPasswordState()
    data object Valid : VerificationPasswordState()
    data class Empty(override val info: String) : VerificationPasswordState()
    data class InvalidLength(override val info: String) : VerificationPasswordState()
}

val VerificationPasswordState.isIdle get() =  this == VerificationPasswordState.Idle
val VerificationPasswordState.isValid get() =  this == VerificationPasswordState.Valid


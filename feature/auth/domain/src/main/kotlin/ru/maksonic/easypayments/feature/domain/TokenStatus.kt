package ru.maksonic.easypayments.feature.domain

/**
 * @Author maksonic on 28.11.2023
 */
sealed class TokenStatus {
    data object Valid : TokenStatus()
    data class Invalid(val cause: String) : TokenStatus()
}

val TokenStatus.isValid get() = this == TokenStatus.Valid
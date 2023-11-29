package ru.maksonic.easypayments

import androidx.lifecycle.ViewModel
import ru.maksonic.easypayments.feature.domain.AuthRepository
import ru.maksonic.easypayments.feature.domain.TokenStatus

/**
 * @Author maksonic on 28.11.2023
 */
class MainActivityViewModel(private val authRepository: AuthRepository) : ViewModel() {
    fun setStartDestinationByTokenStatus(onValid: () -> Unit, onInvalid: () -> Unit) {
        authRepository.isValidToken.onSuccess { status ->
            when (status) {
                is TokenStatus.Valid -> onValid()
                is TokenStatus.Invalid -> onInvalid()
            }
        }.onFailure { onInvalid() }
    }
}
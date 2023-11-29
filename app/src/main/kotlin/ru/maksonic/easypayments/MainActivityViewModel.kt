package ru.maksonic.easypayments

import androidx.lifecycle.ViewModel
import ru.maksonic.easypayments.feature.domain.AuthRepository
import ru.maksonic.easypayments.feature.domain.CheckTokenValidityUseCase
import ru.maksonic.easypayments.feature.domain.TokenStatus

/**
 * @Author maksonic on 28.11.2023
 */
class MainActivityViewModel(
    private val checkTokenValidityUseCase: CheckTokenValidityUseCase
) : ViewModel() {
    fun setStartDestinationByTokenStatus(onValid: () -> Unit, onInvalid: () -> Unit) {
        checkTokenValidityUseCase().onSuccess { status ->
            when (status) {
                is TokenStatus.Valid -> onValid()
                is TokenStatus.Invalid -> onInvalid()
            }
        }.onFailure { onInvalid() }
    }
}
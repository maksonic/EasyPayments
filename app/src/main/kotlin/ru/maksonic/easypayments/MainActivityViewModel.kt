package ru.maksonic.easypayments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maksonic.easypayments.feature.domain.CheckTokenValidityUseCase
import ru.maksonic.easypayments.feature.domain.TokenStatus

/**
 * @Author maksonic on 28.11.2023
 */
class MainActivityViewModel(
    private val checkTokenValidityUseCase: CheckTokenValidityUseCase
) : ViewModel() {
    private var isInstalledDestination = false

    fun setStartDestinationByTokenStatus(onValid: () -> Unit, onInvalid: () -> Unit) {
        if (!isInstalledDestination) {
            viewModelScope.launch {
                checkTokenValidityUseCase().collect { status ->
                    when (status) {
                        is TokenStatus.Valid -> onValid()
                        is TokenStatus.Invalid -> onInvalid()
                    }
                }
            }
            isInstalledDestination = true
        }
    }
}
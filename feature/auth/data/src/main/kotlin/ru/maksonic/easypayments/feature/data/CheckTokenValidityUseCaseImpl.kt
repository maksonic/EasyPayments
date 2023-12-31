package ru.maksonic.easypayments.feature.data

import kotlinx.coroutines.flow.Flow
import ru.maksonic.easypayments.feature.domain.AuthRepository
import ru.maksonic.easypayments.feature.domain.CheckTokenValidityUseCase
import ru.maksonic.easypayments.feature.domain.TokenStatus

/**
 * @Author maksonic on 29.11.2023
 */
class CheckTokenValidityUseCaseImpl(
    private val repository: AuthRepository
) : CheckTokenValidityUseCase {
    override fun invoke(): Flow<TokenStatus> = repository.isValidToken
}
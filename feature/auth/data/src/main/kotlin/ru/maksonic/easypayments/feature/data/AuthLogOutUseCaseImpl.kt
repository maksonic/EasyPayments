package ru.maksonic.easypayments.feature.data

import ru.maksonic.easypayments.feature.domain.AuthLogOutUseCase
import ru.maksonic.easypayments.feature.domain.AuthRepository

/**
 * @Author maksonic on 29.11.2023
 */
class AuthLogOutUseCaseImpl(private val repository: AuthRepository) : AuthLogOutUseCase {
    override suspend fun invoke() = repository.logOut()
}
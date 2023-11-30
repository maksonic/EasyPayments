package ru.maksonic.easypayments.feature.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 28.11.2023
 */
interface AuthRepository {
    fun authWithNameAndPassword(name: String, password: String): Flow<TokenStatus>
    fun getToken(): Result<String>
    suspend fun logOut()
    val isValidToken: Flow<TokenStatus>
}
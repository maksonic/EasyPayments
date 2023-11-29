package ru.maksonic.easypayments.feature.domain

/**
 * @Author maksonic on 28.11.2023
 */
interface AuthRepository {
    suspend fun authWithNameAndPassword(name: String, password: String): Result<TokenStatus>
    fun getToken(): Result<String>
    fun logOut(): Result<Boolean>
    val isValidToken: Result<TokenStatus>
}
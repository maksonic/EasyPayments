package ru.maksonic.easypayments.feature.domain

/**
 * @Author maksonic on 28.11.2023
 */
interface AuthRepository {
    suspend fun authWithNameAndPassword(email: String, password: String): Result<TokenStatus>
    fun getToken(): Result<String>
    //suspend fun logOut()
    val isValidToken: Result<TokenStatus>
}
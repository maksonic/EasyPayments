package ru.maksonic.easypayments.feature.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.maksonic.easypayments.data.ApiService
import ru.maksonic.easypayments.data.auth.AuthCredentials
import ru.maksonic.easypayments.feature.domain.AuthRepository
import ru.maksonic.easypayments.feature.domain.TokenStatus

/**
 * @Author maksonic on 28.11.2023
 */
class AuthRepositoryCore(
    private val apiService: ApiService,
    private val tokenStore: TokenStore
) : AuthRepository {
    override fun authWithNameAndPassword(
        name: String,
        password: String
    ): Flow<TokenStatus> = flow {
        val response = apiService.authWithUsernameAndPassword(AuthCredentials(name, password))
        if (response.isSuccess) {
            val token = response.response.token ?: ""

            if (token.isNotBlank()) {
                tokenStore.saveToken(token.encodeToByteArray())
                emit(TokenStatus.Valid)
            } else {
                emit(TokenStatus.Invalid("Token is null!"))
            }
        }
    }

    override fun getToken(): Result<String> = tokenStore.getToken()

    override suspend fun logOut() = tokenStore.deleteToken()

    override val isValidToken: Flow<TokenStatus> = tokenStore.verifyToken()
}
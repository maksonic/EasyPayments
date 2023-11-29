package ru.maksonic.easypayments.feature.data

import ru.maksonic.easypayments.data.ApiService
import ru.maksonic.easypayments.data.AuthCredentials
import ru.maksonic.easypayments.feature.domain.AuthRepository
import ru.maksonic.easypayments.feature.domain.TokenStatus

/**
 * @Author maksonic on 28.11.2023
 */
class AuthRepositoryCore(
    private val apiService: ApiService,
    private val tokenStore: TokenStore
) : AuthRepository {
    override suspend fun authWithNameAndPassword(
        name: String,
        password: String
    ): Result<TokenStatus> = runCatching {
        apiService.authWithUsernameAndPassword(AuthCredentials(name, password))
    }.fold(
        onSuccess = {
            val token = it.response.token ?: ""

            if (token.isNotBlank()) {
                tokenStore.saveToken(token.encodeToByteArray())
                Result.success(TokenStatus.Valid)
            } else {
                Result.success(TokenStatus.Invalid("Token is null!"))
            }
        },
        onFailure = { Result.failure(it) }
    )

    override fun getToken(): Result<String> = tokenStore.getToken()

    override val isValidToken: Result<TokenStatus>
        get() = tokenStore.verifyToken().fold(
            onSuccess = { Result.success(TokenStatus.Valid) },
            onFailure = { Result.failure(it) }
        )
}
package ru.maksonic.easypayments.feature.data

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.maksonic.easypayments.common.core.CryptoEngine
import ru.maksonic.easypayments.feature.domain.TokenStatus
import java.io.File

/**
 * @Author maksonic on 28.11.2023
 */
class TokenStore(
    private val context: Context,
    private val cryptoEngine: CryptoEngine,
    private val ioDispatcher: CoroutineDispatcher
) {
    private companion object {
        private const val FILE_NAME = "easypayments_token.txt"
    }

    fun saveToken(byteArray: ByteArray): Unit = with(ioDispatcher) {
        val file = File(context.filesDir, FILE_NAME)

        if (!file.exists()) {
            file.createNewFile()
        }

        val encryptedPin = cryptoEngine.encrypt(byteArray)
        context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
            it.write(encryptedPin)
        }
    }

    fun verifyToken(): Flow<TokenStatus> = flow {
        val file = File(context.filesDir, FILE_NAME)

        if (file.exists()) {
            cryptoEngine.decrypt(file.readBytes())
                .onSuccess { emit(TokenStatus.Valid) }
                .onFailure { emit(TokenStatus.Invalid(it.localizedMessage ?: "Invalid token")) }
        } else {
            emit(TokenStatus.Invalid("Token file not found"))
        }
    }

    fun getToken(): Result<String> = runCatching {
        val encryptedPin = context.openFileInput(FILE_NAME).readBytes()
        val decrypted = cryptoEngine.decrypt(encryptedPin)

        return decrypted.fold(
            onSuccess = { Result.success(it.decodeToString()) },
            onFailure = { Result.failure(it) }
        )
    }

    fun deleteToken() {
        val file = File(context.filesDir, FILE_NAME)
        if (file.exists()) {
            context.deleteFile(file.name)
        }
    }
}
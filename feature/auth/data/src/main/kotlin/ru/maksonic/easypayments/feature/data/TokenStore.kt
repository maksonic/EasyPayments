package ru.maksonic.easypayments.feature.data

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import ru.maksonic.easypayments.common.core.CryptoEngine
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

    fun verifyToken(): Result<Boolean> = runCatching {
        val encryptedPin = context.openFileInput(FILE_NAME).readBytes()
        val decrypted = cryptoEngine.decrypt(encryptedPin)

        return decrypted.fold(
            onSuccess = { Result.success(it.isNotEmpty()) },
            onFailure = { Result.success(false) }
        )
    }

    fun getToken(): Result<String> = runCatching {
        val encryptedPin = context.openFileInput(FILE_NAME).readBytes()
        val decrypted = cryptoEngine.decrypt(encryptedPin)

        return decrypted.fold(
            onSuccess = { Result.success(it.decodeToString()) },
            onFailure = { Result.failure(it) }
        )
    }
}
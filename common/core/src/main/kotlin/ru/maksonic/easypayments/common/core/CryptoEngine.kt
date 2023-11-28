package ru.maksonic.easypayments.common.core

/**
 * @Author maksonic on 28.11.2023
 */
interface CryptoEngine {
    fun encrypt(rawData: ByteArray): ByteArray
    fun decrypt(encryptedData: ByteArray): Result<ByteArray>
    fun deleteEntry()
}
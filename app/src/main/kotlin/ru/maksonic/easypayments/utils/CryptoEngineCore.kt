package ru.maksonic.easypayments.utils

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import ru.maksonic.easypayments.common.core.CryptoEngine
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PublicKey
import javax.crypto.Cipher

/**
 * @Author maksonic on 28.11.2023
 */
class CryptoEngineCore : CryptoEngine {
    private companion object {
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_RSA
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_ECB
        private const val KEY_ALIAS = "XFK83K28NVSK10NDJ27XJMAD37"
        private const val KEYSTORE_PROVIDER = "AndroidKeyStore"
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    }

    private fun generateEncryptKey(): PublicKey {
        val kpg = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, KEYSTORE_PROVIDER)
        val spec: KeyGenParameterSpec = KeyGenParameterSpec.Builder(
            KEY_ALIAS, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1).run {
                setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                build()
            }

        kpg.initialize(spec)
        return kpg.generateKeyPair().public
    }

    private val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER).apply {
        load(null)
    }

    private val encryptKey: PublicKey = if (keyStore.containsAlias(KEY_ALIAS)) {
        keyStore.getCertificate(KEY_ALIAS).publicKey
    } else {
        generateEncryptKey()
    }

    private val cipherEncrypt = Cipher.getInstance(TRANSFORMATION).apply {
        init(Cipher.ENCRYPT_MODE, encryptKey)
    }

    private val cipherDecrypt = Cipher.getInstance(TRANSFORMATION).apply {
        val privateKey = keyStore.getEntry(KEY_ALIAS, null) as KeyStore.PrivateKeyEntry
        init(Cipher.DECRYPT_MODE, privateKey.privateKey)
    }

    override fun encrypt(rawData: ByteArray): ByteArray = cipherEncrypt.doFinal(rawData)

    override fun decrypt(encryptedData: ByteArray): Result<ByteArray> = runCatching {
        cipherDecrypt.doFinal(encryptedData)
    }

    override fun deleteEntry() = keyStore.deleteEntry(KEY_ALIAS)
}

package ru.maksonic.beresta.common.core.security

/**
 * @Author maksonic on 24.10.2023
 */
interface CryptoEngine {
    fun encrypt(rawData: ByteArray): ByteArray
    fun decrypt(encryptedData: ByteArray): Result<ByteArray>
    fun deleteEntry()
}
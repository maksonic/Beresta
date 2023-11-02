package ru.maksonic.beresta.platform.core.security

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import ru.maksonic.beresta.common.core.security.BiometricEngine

/**
 * @Author maksonic on 25.10.2023
 */
class BiometricEngineCore(context: Context) : BiometricEngine {
    private val biometricManager = BiometricManager.from(context)

    private fun checkBiometricState() =
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> true
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> false
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> false
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> false
            else -> false
        }

    override val isAvailable: Boolean
        get() = checkBiometricState()
}
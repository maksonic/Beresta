package ru.maksonic.beresta.core.system.vibration

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import ru.maksonic.beresta.core.system.VibrationManager
import ru.maksonic.beresta.core.system.VibrationPerformer

/**
 * @Author maksonic on 26.07.2023
 */
class VibrationManagerCore(
    context: Context, private val vibrationPerformer: VibrationPerformer
) : VibrationManager {
    private companion object {
        private const val ONE_SHOT_MS = 250L
    }

    private val vibrator = with(context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val manager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            manager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            getSystemService(VIBRATOR_SERVICE) as Vibrator
        }
    }

    override suspend fun vibrateShortOneShot() = vibrationPerformer.isEnabled
        .collect { isEnabledVibration ->
            if (isEnabledVibration) {
                vibrator.apply {
                    cancel()
                    vibrate(
                        VibrationEffect.createOneShot(
                            ONE_SHOT_MS,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                }
            }
        }
}
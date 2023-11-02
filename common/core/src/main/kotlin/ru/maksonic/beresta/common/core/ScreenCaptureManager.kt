package ru.maksonic.beresta.common.core

import android.view.Window
import kotlinx.coroutines.CoroutineScope

/**
 * @Author maksonic on 14.10.2023
 */
interface ScreenCaptureManager {
    fun init(window: Window, lifecycleScope: CoroutineScope)
    fun update(isEnabled: Boolean)
}
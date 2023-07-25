package ru.maksonic.beresta.core.secure

import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @Author maksonic on 15.07.2023
 */
interface ScreenCaptureManager {
    fun initMutablePermission(window: Window, lifecycleScope: LifecycleCoroutineScope)
    fun enableScreenCapture()
    fun disableScreenCapture()

    class Core : ScreenCaptureManager {
        private val mutablePermission = MutableStateFlow(false)
        private val permission = mutablePermission.asStateFlow()

        override fun initMutablePermission(
            window: Window,
            lifecycleScope: LifecycleCoroutineScope
        ) {
            lifecycleScope.launch {
                permission.collect { isEnableScreenCapture ->
                    if (isEnableScreenCapture) {
                        window.setFlags(
                            WindowManager.LayoutParams.FLAG_SECURE,
                            WindowManager.LayoutParams.FLAG_SECURE
                        )
                    } else {
                        window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
                    }
                }
            }
        }

        override fun enableScreenCapture() {
            mutablePermission.update { true }
        }

        override fun disableScreenCapture() {
            mutablePermission.update { false }
        }
    }
}
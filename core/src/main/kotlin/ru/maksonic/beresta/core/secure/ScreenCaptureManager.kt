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
    fun initPermission(window: Window, lifecycleScope: LifecycleCoroutineScope)
    fun update(isEnabled: Boolean)

    class Core : ScreenCaptureManager {
        private val mutablePermission = MutableStateFlow(false)
        private val permission = mutablePermission.asStateFlow()

        override fun initPermission(window: Window, lifecycleScope: LifecycleCoroutineScope) {
            lifecycleScope.launch {
                permission.collect { isBlockedScreenCapture ->
                    if (isBlockedScreenCapture) {
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

        override fun update(isEnabled: Boolean) = mutablePermission.update { isEnabled }
    }
}
package ru.maksonic.beresta.platform.core.ui

import android.view.Window
import android.view.WindowManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.maksonic.beresta.common.core.ScreenCaptureManager

/**
 * @Author maksonic on 14.10.2023
 */

class ScreenCaptureManagerCore : ScreenCaptureManager {
    private val mutablePermission = MutableStateFlow(false)
    private val permission = mutablePermission.asStateFlow()

    override fun init(window: Window, lifecycleScope: CoroutineScope) {
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

    override fun update(isEnabled: Boolean) {
        mutablePermission.update { isEnabled }
    }
}
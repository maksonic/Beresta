package ru.maksonic.beresta.common.ui_kit.animation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.maksonic.beresta.common.ui_theme.provide.dp8

/**
 * @Author maksonic on 19.09.2023
 */
private const val TITLE_SHAKE_REPEAT_COUNT = 3
private const val TITLE_SHAKE_DELAY = 60L

@Composable
fun rememberShakeController(): ShakeController {
    return remember { ShakeController() }
}

class ShakeController {
    private val _isShake = MutableStateFlow(false)
    val isShake = _isShake.asStateFlow()

    fun shake() {
        _isShake.value = true
    }

    fun cancelShake() {
        _isShake.value = false
    }
}


fun Modifier.shake(shakeController: ShakeController) = composed {
    shakeController.let { controller ->
        val isEnabledLeft = remember { mutableStateOf(false) }
        val isEnabledRight = remember { mutableStateOf(false) }
        val startPadding = animateDpAsState(if (isEnabledLeft.value) dp8 else 0.dp, label = "")
        val endPadding = animateDpAsState(if (isEnabledRight.value) dp8 else 0.dp, label = "")

        LaunchedEffect(controller.isShake.value) {
            if (shakeController.isShake.value) {
                repeat(TITLE_SHAKE_REPEAT_COUNT) {
                    isEnabledLeft.value = true
                    delay(TITLE_SHAKE_DELAY)
                    isEnabledLeft.value = false
                    isEnabledRight.value = true
                    delay(TITLE_SHAKE_DELAY)
                    isEnabledRight.value = false
                }.let { controller.cancelShake() }
            }
        }
        this.padding(start = startPadding.value, end = endPadding.value)
    }
}

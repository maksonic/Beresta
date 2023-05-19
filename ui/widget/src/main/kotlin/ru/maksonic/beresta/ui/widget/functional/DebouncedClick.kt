package ru.maksonic.beresta.ui.widget.functional

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @Author maksonic on 15.11.2022
 */
fun Modifier.clickAction(
    rippleColor: Color = Color.Black,
    timeOut: Long = 300,
    onClick: () -> Unit,
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "debouncedClick"
        properties["timeOut"] = timeOut
        properties["onClick"] = onClick
    }
) {
    var isEnabled by rememberSaveable {
        mutableStateOf(true)
    }
    val coroutineScope = rememberCoroutineScope()
    val currentClickListener by rememberUpdatedState(onClick)

    Modifier.rippleClickable(isEnabled, rippleColor = rippleColor) {
        coroutineScope.launch {
            isEnabled = false
            currentClickListener.invoke()
            delay(timeOut)
            isEnabled = true
        }
    }
}

fun Modifier.rippleClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    rippleColor: Color = Color.Black,
    onClick: () -> Unit,
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["rippleColor"] = rippleColor
        properties["onClick"] = onClick
    }
) {
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = onClick,
        role = role,
        indication = rememberRipple(color = rippleColor, bounded = true),
        interactionSource = remember { MutableInteractionSource() }
    )
}

fun Modifier.noRippleClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = onClick,
        role = role,
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    )
}
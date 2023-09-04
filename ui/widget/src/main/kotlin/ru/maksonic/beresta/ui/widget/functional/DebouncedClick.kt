package ru.maksonic.beresta.ui.widget.functional

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import ru.maksonic.beresta.core.system.VibrationPerformer

/**
 * @Author maksonic on 15.11.2022
 */

fun Modifier.rippledClick(
    performer: VibrationPerformer? = null,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    rippleColor: Color = Color.Black,
    onClick: () -> Unit,
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "rippledClick"
        properties["rippledClickEnabled"] = enabled
        properties["rippledClickOnClickLabel"] = onClickLabel
        properties["rippledClickRole"] = role
        properties["rippledClickRippleColor"] = rippleColor
        properties["rippledClickOnClick"] = onClick
    }
) {
    val view = LocalView.current
    val indication = if (enabled) rememberRipple(color = rippleColor) else null

    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = { onClick().run { performer?.keyboardTapVibration(view) } },
        role = role,
        indication = indication,
        interactionSource = remember { MutableInteractionSource() }
    )
}

fun Modifier.noRippleClick(
    performer: VibrationPerformer? = null,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "noRippleClick"
        properties["noRippleClickEnabled"] = enabled
        properties["noRippleClickOnClickLabel"] = onClickLabel
        properties["noRippleClickRole"] = role
        properties["noRippleClickOnClick"] = onClick
    }
) {
    val view = LocalView.current

    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = { onClick().run { performer?.keyboardTapVibration(view) } },
        role = role,
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    )
}
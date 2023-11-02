package ru.maksonic.beresta.common.ui_kit.helpers.modifier

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
import ru.maksonic.beresta.common.core.VibrationPerformer

/**
 * @Author maksonic on 14.10.2023
 */
fun Modifier.rippledClick(
    rippleColor: Color = Color.Black,
    enabled: Boolean = true,
    performer: VibrationPerformer? = null,
    onClickLabel: String? = null,
    role: Role? = null,
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
    enabled: Boolean = true,
    performer: VibrationPerformer? = null,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit = {},
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
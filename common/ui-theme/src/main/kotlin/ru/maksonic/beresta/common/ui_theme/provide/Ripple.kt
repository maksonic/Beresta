package ru.maksonic.beresta.common.ui_theme.provide

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.common.ui_theme.Theme

/**
 * @Author maksonic on 08.11.2022
 */
object AppRipple : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Theme.color.primary

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        Theme.color.primary,
        lightTheme = !isSystemInDarkTheme()
    )
}

object PrimaryRipple : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Theme.color.surface

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        Theme.color.primary,
        lightTheme = !isSystemInDarkTheme()
    )
}

object NoRipple : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
}
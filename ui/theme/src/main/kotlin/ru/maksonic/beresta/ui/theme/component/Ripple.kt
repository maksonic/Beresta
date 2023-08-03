package ru.maksonic.beresta.ui.theme.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.ui.theme.Theme

/**
 * @Author maksonic on 08.11.2022
 */
object AppRipple : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Theme.color.inverseOnSurface

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        Theme.color.primary,
        lightTheme = !isSystemInDarkTheme()
    )
}

object PrimaryRipple : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Theme.color.primary

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        Theme.color.primary,
        lightTheme = !isSystemInDarkTheme()
    )
}

data class Ripple(private val color: Color) : RippleTheme {
    @Composable
    override fun defaultColor(): Color = color

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleTheme.defaultRippleAlpha(color, lightTheme = !isSystemInDarkTheme())
}

object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
}
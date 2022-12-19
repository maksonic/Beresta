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
    override fun defaultColor(): Color = Theme.color.onPrimary

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        Theme.color.primary,
        lightTheme = !isSystemInDarkTheme()
    )
}
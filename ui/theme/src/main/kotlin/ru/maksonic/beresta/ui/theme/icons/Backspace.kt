package ru.maksonic.beresta.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 16.07.2023
 */
val AppIcon.Backspace: ImageVector
    get() {
        if (_backspace != null) {
            return _backspace!!
        }
        _backspace = ImageVector.Builder(
            name = "Backspace",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(363.35F, 768.13F)
            quadToRelative(-22.39F, 0.0F, -42.16F, -9.96F)
            quadToRelative(-19.77F, -9.96F, -32.21F, -28.11F)
            lineTo(149.41F, 532.46F)
            quadTo(132.5F, 508.49F, 132.5F, 479.95F)
            quadToRelative(0.0F, -28.54F, 16.91F, -52.41F)
            lineToRelative(139.57F, -197.61F)
            quadToRelative(12.43F, -17.91F, 32.2F, -27.99F)
            quadToRelative(19.77F, -10.08F, 42.17F, -10.08F)
            lineTo(757.13F, 191.87F)
            quadToRelative(37.54F, 0.0F, 64.27F, 26.73F)
            quadToRelative(26.73F, 26.73F, 26.73F, 64.27F)
            verticalLineToRelative(394.26F)
            quadToRelative(0.0F, 37.54F, -26.73F, 64.27F)
            quadToRelative(-26.73F, 26.73F, -64.27F, 26.73F)
            lineTo(363.35F, 768.13F)

            moveTo(757.13F, 677.13F)
            verticalLineToRelative(-394.26F)

            moveTo(363.31F, 677.13F)
            lineTo(757.13F, 677.13F)
            verticalLineToRelative(-394.26F)
            lineTo(363.35F, 282.87F)
            lineTo(223.5F, 480.0F)
            lineToRelative(139.81F, 197.13F)

            moveTo(560.24F, 540.07F)
            lineTo(628.83F, 608.65F)
            quadToRelative(11.96F, 11.96F, 30.03F, 11.84F)
            quadToRelative(18.08F, -0.12F, 30.03F, -12.08F)
            quadToRelative(11.96F, -11.96F, 11.96F, -30.03F)
            reflectiveQuadToRelative(-11.96F, -30.03F)
            lineTo(620.3F, 480.0F)
            lineToRelative(68.59F, -68.59F)
            quadToRelative(11.96F, -11.96F, 11.96F, -30.03F)
            quadToRelative(0.0F, -18.08F, -11.96F, -30.03F)
            quadToRelative(-11.96F, -11.96F, -30.03F, -11.96F)
            quadToRelative(-18.08F, 0.0F, -30.03F, 11.96F)
            lineToRelative(-68.59F, 68.59F)
            lineToRelative(-68.59F, -68.59F)
            quadToRelative(-11.96F, -11.96F, -29.91F, -11.96F)
            quadToRelative(-17.96F, 0.0F, -29.91F, 11.96F)
            quadToRelative(-11.96F, 11.96F, -11.96F, 30.03F)
            quadToRelative(0.0F, 18.08F, 11.96F, 30.03F)
            lineTo(500.17F, 480.0F)
            lineToRelative(-68.59F, 68.59F)
            quadTo(419.63F, 560.54F, 419.75F, 578.5F)
            quadToRelative(0.12F, 17.96F, 12.08F, 29.91F)
            quadToRelative(11.96F, 11.96F, 30.03F, 11.96F)
            reflectiveQuadToRelative(30.03F, -11.96F)
            lineToRelative(68.35F, -68.35F)
            close()
        }.build()
        return _backspace!!
    }
private var _backspace: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconBackspacePreview() {
    Image(imageVector = AppIcon.Backspace, contentDescription = null)
}
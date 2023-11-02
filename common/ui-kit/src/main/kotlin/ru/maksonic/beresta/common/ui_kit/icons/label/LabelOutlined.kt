package ru.maksonic.beresta.common.ui_kit.icons.label

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon

/**
 * @Author maksonic on 09.09.2023
 */
val AppIcon.LabelOutlined: ImageVector
    get() {
        if (_labelOutlined != null) {
            return _labelOutlined!!
        }
        _labelOutlined = ImageVector.Builder(
            name = "LabelOutlined",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(810.59F, 532.46F)
            lineTo(671.02F, 730.07F)
            quadToRelative(-12.44F, 18.15F, -32.21F, 28.11F)
            quadToRelative(-19.77F, 9.96F, -42.16F, 9.96F)
            lineTo(202.87F, 768.13F)
            quadToRelative(-37.54F, 0.0F, -64.27F, -26.73F)
            quadToRelative(-26.73F, -26.73F, -26.73F, -64.27F)
            verticalLineToRelative(-394.26F)
            quadToRelative(0.0F, -37.54F, 26.73F, -64.27F)
            quadToRelative(26.73F, -26.73F, 64.27F, -26.73F)
            horizontalLineToRelative(393.78F)
            quadToRelative(22.39F, 0.0F, 42.17F, 10.08F)
            quadToRelative(19.77F, 10.08F, 32.2F, 27.99F)
            lineToRelative(139.57F, 197.61F)
            quadToRelative(16.91F, 23.97F, 16.91F, 52.51F)
            quadToRelative(0.0F, 28.54F, -16.91F, 52.41F)

            moveTo(596.65F, 677.13F)
            lineTo(736.5F, 480.0F)
            lineTo(596.69F, 282.87F)
            lineTo(202.87F, 282.87F)
            verticalLineToRelative(394.26F)
            horizontalLineToRelative(393.78F)

            moveTo(202.87F, 282.87F)
            verticalLineToRelative(394.26F)
            close()
        }.build()
        return _labelOutlined!!
    }
private var _labelOutlined: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconLabelOutlinedPreview() {
    Image(imageVector = AppIcon.LabelOutlined, contentDescription = null)
}
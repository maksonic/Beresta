package ru.maksonic.beresta.ui.theme.icons.label

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.icons.AppIcon

/**
 * @Author maksonic on 09.09.2023
 */
val AppIcon.LabelFilled: ImageVector
    get() {
        if (_labelFilled != null) {
            return _labelFilled!!
        }
        _labelFilled = ImageVector.Builder(
            name = "LabelFilled",
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
            quadToRelative(22.39F, 0.0F, 42.16F, 10.08F)
            quadToRelative(19.77F, 10.08F, 32.21F, 27.99F)
            lineToRelative(139.57F, 197.61F)
            quadTo(827.5F, 451.41F, 827.5F, 480.0F)
            reflectiveQuadToRelative(-16.91F, 52.46F)
            close()
        }.build()
        return _labelFilled!!
    }
private var _labelFilled: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconLabelFilledPreview() {
    Image(imageVector = AppIcon.LabelFilled, contentDescription = null)
}
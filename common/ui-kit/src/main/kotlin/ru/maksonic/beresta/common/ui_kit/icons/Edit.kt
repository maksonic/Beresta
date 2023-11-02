package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_theme.BerestaTheme

/**
 * @Author maksonic on 23.02.2023
 */
val AppIcon.Edit: ImageVector
    get() {
        if (_edit != null) {
            return _edit!!
        }
        _edit = ImageVector.Builder(
            name = "Edit",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(202.15F, 760.24F)
            horizontalLineToRelative(52.17F)
            lineToRelative(342.61F, -342.85F)
            lineToRelative(-52.17F, -52.17F)
            lineToRelative(-342.61F, 342.85F)
            verticalLineToRelative(52.17F)

            moveTo(772.96F, 359.63F)
            lineTo(601.76F, 190.2F)
            lineToRelative(54.56F, -54.81F)
            quadToRelative(23.96F, -23.96F, 58.65F, -24.19F)
            quadToRelative(34.7F, -0.24F, 59.61F, 24.19F)
            lineToRelative(52.65F, 52.41F)
            quadToRelative(24.91F, 24.43F, 25.2F, 58.25F)
            quadToRelative(0.28F, 33.82F, -23.67F, 57.77F)
            lineToRelative(-55.8F, 55.8F)

            moveTo(163.83F, 843.83F)
            quadToRelative(-19.15F, 0.0F, -32.33F, -13.17F)
            reflectiveQuadToRelative(-13.17F, -32.33F)
            lineTo(118.33F, 691.78F)
            quadToRelative(0.0F, -9.2F, 3.48F, -17.65F)
            quadToRelative(3.48F, -8.46F, 10.2F, -15.17F)
            lineToRelative(411.76F, -411.76F)
            lineToRelative(171.43F, 171.2F)
            lineToRelative(-411.76F, 411.76F)
            quadToRelative(-6.72F, 6.72F, -15.29F, 10.2F)
            quadToRelative(-8.58F, 3.48F, -17.77F, 3.48F)
            lineTo(163.83F, 843.83F)

            moveTo(571.09F, 391.3F)
            lineTo(544.76F, 365.22F)
            lineTo(596.93F, 417.39F)
            lineTo(571.09F, 391.3F)
            close()
        }.build()
        return _edit!!
    }
private var _edit: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconEditPreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.Edit, contentDescription = null)
    }
}
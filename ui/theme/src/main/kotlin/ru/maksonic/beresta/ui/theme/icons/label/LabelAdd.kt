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
val AppIcon.LabelAdd: ImageVector
    get() {
        if (_labelAdd != null) {
            return _labelAdd!!
        }
        _labelAdd = ImageVector.Builder(
            name = "LabelAdd",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(596.65F, 768.13F)
            lineTo(531.0F, 768.13F)
            quadToRelative(-19.15F, 0.0F, -32.33F, -13.17F)
            reflectiveQuadTo(485.5F, 722.63F)
            quadToRelative(0.0F, -19.15F, 13.17F, -32.33F)
            reflectiveQuadTo(531.0F, 677.13F)
            horizontalLineToRelative(65.65F)
            lineTo(736.5F, 480.0F)
            lineTo(596.69F, 282.87F)
            lineTo(202.87F, 282.87F)
            lineTo(202.87F, 349.0F)
            quadToRelative(0.0F, 19.15F, -13.17F, 32.33F)
            reflectiveQuadTo(157.37F, 394.5F)
            quadToRelative(-19.15F, 0.0F, -32.33F, -13.17F)
            reflectiveQuadTo(111.87F, 349.0F)
            verticalLineToRelative(-66.13F)
            quadToRelative(0.0F, -37.54F, 26.73F, -64.27F)
            quadToRelative(26.73F, -26.73F, 64.27F, -26.73F)
            horizontalLineToRelative(393.78F)
            quadToRelative(22.39F, 0.0F, 42.16F, 9.96F)
            quadToRelative(19.77F, 9.96F, 32.21F, 28.11F)
            lineToRelative(139.57F, 197.61F)
            quadToRelative(16.91F, 23.97F, 16.91F, 52.51F)
            quadToRelative(0.0F, 28.54F, -16.91F, 52.41F)
            lineTo(671.02F, 730.07F)
            quadToRelative(-12.43F, 17.91F, -32.2F, 27.99F)
            quadToRelative(-19.77F, 10.08F, -42.17F, 10.08F)

            moveTo(469.8F, 480.0F)

            moveTo(194.5F, 685.5F)
            lineTo(120.0F, 685.5F)
            quadToRelative(-19.15F, 0.0F, -32.33F, -13.17F)
            reflectiveQuadTo(74.5F, 640.0F)
            quadToRelative(0.0F, -19.15F, 13.17F, -32.33F)
            reflectiveQuadTo(120.0F, 594.5F)
            horizontalLineToRelative(74.5F)
            lineTo(194.5F, 520.0F)
            quadToRelative(0.0F, -19.15F, 13.17F, -32.33F)
            reflectiveQuadTo(240.0F, 474.5F)
            quadToRelative(19.15F, 0.0F, 32.33F, 13.17F)
            reflectiveQuadTo(285.5F, 520.0F)
            verticalLineToRelative(74.5F)
            lineTo(360.0F, 594.5F)
            quadToRelative(19.15F, 0.0F, 32.33F, 13.17F)
            reflectiveQuadTo(405.5F, 640.0F)
            quadToRelative(0.0F, 19.15F, -13.17F, 32.33F)
            reflectiveQuadTo(360.0F, 685.5F)
            horizontalLineToRelative(-74.5F)
            verticalLineToRelative(74.5F)
            quadToRelative(0.0F, 19.15F, -13.17F, 32.33F)
            reflectiveQuadTo(240.0F, 805.5F)
            quadToRelative(-19.15F, 0.0F, -32.33F, -13.17F)
            reflectiveQuadTo(194.5F, 760.0F)
            verticalLineToRelative(-74.5F)
            close()
        }.build()
        return _labelAdd!!
    }
private var _labelAdd: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconLabelAddPreview() {
    Image(imageVector = AppIcon.LabelAdd, contentDescription = null)
}
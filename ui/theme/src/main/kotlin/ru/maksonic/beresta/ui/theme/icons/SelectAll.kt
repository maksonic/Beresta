package ru.maksonic.beresta.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.BerestaTheme

/**
 * @Author maksonic on 30.04.2023
 */
val AppIcon.SelectAll: ImageVector
    get() {
        if (_selectAll != null) {
            return _selectAll!!
        }
        _selectAll = ImageVector.Builder(
            name = "SelectAll",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(195.7F, 855.3F)
            quadToRelative(-37.78F, 0.0F, -64.39F, -26.61F)
            quadToRelative(-26.61F, -26.61F, -26.61F, -64.39F)
            horizontalLineToRelative(91.0F)
            verticalLineToRelative(91.0F)

            moveTo(104.69F, 684.3F)
            verticalLineToRelative(-82.87F)
            horizontalLineToRelative(91.0F)
            verticalLineToRelative(82.87F)
            horizontalLineToRelative(-91.0F)

            moveTo(104.69F, 521.43F)
            verticalLineToRelative(-82.87F)
            horizontalLineToRelative(91.0F)
            verticalLineToRelative(82.87F)
            horizontalLineToRelative(-91.0F)

            moveTo(104.69F, 358.56F)
            verticalLineToRelative(-82.87F)
            horizontalLineToRelative(91.0F)
            verticalLineToRelative(82.87F)
            horizontalLineToRelative(-91.0F)

            moveTo(104.69F, 195.7F)
            quadToRelative(0.0F, -37.78F, 26.61F, -64.39F)
            quadToRelative(26.61F, -26.61F, 64.39F, -26.61F)
            verticalLineToRelative(91.0F)
            horizontalLineToRelative(-91.0F)

            moveTo(275.7F, 684.3F)
            lineTo(275.7F, 275.7F)
            horizontalLineToRelative(408.61F)
            verticalLineToRelative(408.61F)
            lineTo(275.7F, 684.3F)

            moveTo(275.7F, 855.3F)
            verticalLineToRelative(-91.0F)
            horizontalLineToRelative(82.87F)
            verticalLineToRelative(91.0F)
            horizontalLineToRelative(-82.87F)

            moveTo(275.7F, 195.7F)
            verticalLineToRelative(-91.0F)
            horizontalLineToRelative(82.87F)
            verticalLineToRelative(91.0F)
            horizontalLineToRelative(-82.87F)

            moveTo(366.7F, 593.3F)
            horizontalLineToRelative(226.61F)
            lineTo(593.3F, 366.7F)
            lineTo(366.7F, 366.7F)
            verticalLineToRelative(226.61F)

            moveTo(438.57F, 855.3F)
            verticalLineToRelative(-91.0F)
            horizontalLineToRelative(82.87F)
            verticalLineToRelative(91.0F)
            horizontalLineToRelative(-82.87F)

            moveTo(438.57F, 195.7F)
            verticalLineToRelative(-91.0F)
            horizontalLineToRelative(82.87F)
            verticalLineToRelative(91.0F)
            horizontalLineToRelative(-82.87F)

            moveTo(601.43F, 855.3F)
            verticalLineToRelative(-91.0F)
            horizontalLineToRelative(82.87F)
            verticalLineToRelative(91.0F)
            horizontalLineToRelative(-82.87F)

            moveTo(601.43F, 195.7F)
            verticalLineToRelative(-91.0F)
            horizontalLineToRelative(82.87F)
            verticalLineToRelative(91.0F)
            horizontalLineToRelative(-82.87F)

            moveTo(764.3F, 855.3F)
            verticalLineToRelative(-91.0F)
            horizontalLineToRelative(91.0F)
            quadToRelative(0.0F, 37.78F, -26.61F, 64.39F)
            quadToRelative(-26.61F, 26.61F, -64.39F, 26.61F)

            moveTo(764.3F, 684.3F)
            verticalLineToRelative(-82.87F)
            horizontalLineToRelative(91.0F)
            verticalLineToRelative(82.87F)
            horizontalLineToRelative(-91.0F)

            moveTo(764.3F, 521.43F)
            verticalLineToRelative(-82.87F)
            horizontalLineToRelative(91.0F)
            verticalLineToRelative(82.87F)
            horizontalLineToRelative(-91.0F)

            moveTo(764.3F, 358.56F)
            verticalLineToRelative(-82.87F)
            horizontalLineToRelative(91.0F)
            verticalLineToRelative(82.87F)
            horizontalLineToRelative(-91.0F)

            moveTo(764.3F, 195.7F)
            verticalLineToRelative(-91.0F)
            quadToRelative(37.78F, 0.0F, 64.39F, 26.61F)
            quadToRelative(26.61F, 26.61F, 26.61F, 64.39F)
            horizontalLineToRelative(-91.0F)
            close()
        }.build()
        return _selectAll!!
    }
private var _selectAll: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconSelectAllPreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.SelectAll, contentDescription = null)

    }
}
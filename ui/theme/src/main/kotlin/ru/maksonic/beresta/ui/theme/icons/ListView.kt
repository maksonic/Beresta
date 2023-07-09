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
 * @Author maksonic on 03.07.2023
 */
val AppIcon.ListView: ImageVector
    get() {
        if (_listView != null) {
            return _listView!!
        }
        _listView = ImageVector.Builder(
            name = "ListView",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(202.87F, 441.91F)
            quadToRelative(-37.78F, 0.0F, -64.39F, -26.61F)
            quadToRelative(-26.61F, -26.61F, -26.61F, -64.39F)
            verticalLineToRelative(-152.83F)
            quadToRelative(0.0F, -37.78F, 26.61F, -64.39F)
            quadToRelative(26.61F, -26.61F, 64.39F, -26.61F)
            horizontalLineToRelative(554.26F)
            quadToRelative(37.78F, 0.0F, 64.39F, 26.61F)
            quadToRelative(26.61F, 26.61F, 26.61F, 64.39F)
            verticalLineToRelative(152.83F)
            quadToRelative(0.0F, 37.78F, -26.61F, 64.39F)
            quadToRelative(-26.61F, 26.61F, -64.39F, 26.61F)
            lineTo(202.87F, 441.91F)

            moveTo(202.87F, 350.91F)
            horizontalLineToRelative(554.26F)
            verticalLineToRelative(-152.83F)
            lineTo(202.87F, 198.09F)
            verticalLineToRelative(152.83F)

            moveTo(202.87F, 852.91F)
            quadToRelative(-37.78F, 0.0F, -64.39F, -26.61F)
            quadToRelative(-26.61F, -26.61F, -26.61F, -64.39F)
            verticalLineToRelative(-152.83F)
            quadToRelative(0.0F, -37.78F, 26.61F, -64.39F)
            quadToRelative(26.61F, -26.61F, 64.39F, -26.61F)
            horizontalLineToRelative(554.26F)
            quadToRelative(37.78F, 0.0F, 64.39F, 26.61F)
            quadToRelative(26.61F, 26.61F, 26.61F, 64.39F)
            verticalLineToRelative(152.83F)
            quadToRelative(0.0F, 37.78F, -26.61F, 64.39F)
            quadToRelative(-26.61F, 26.61F, -64.39F, 26.61F)
            lineTo(202.87F, 852.91F)

            moveTo(202.87F, 761.91F)
            horizontalLineToRelative(554.26F)
            verticalLineToRelative(-152.83F)
            lineTo(202.87F, 609.09F)
            verticalLineToRelative(152.83F)

            moveTo(202.87F, 198.09F)
            verticalLineToRelative(152.83F)

            moveTo(202.87F, 609.09F)
            verticalLineToRelative(152.83F)
            close()
        }.build()
        return _listView!!
    }
private var _listView: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconListViewPreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.ListView, contentDescription = null)
    }
}
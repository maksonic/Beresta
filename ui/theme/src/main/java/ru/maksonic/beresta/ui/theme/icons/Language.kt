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
 * @Author maksonic on 16.02.2023
 */
val AppIcon.Language: ImageVector
    get() {
        if (_language != null) {
            return _language!!
        }
        _language = ImageVector.Builder(
            name = "Language",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(480.0F, 888.13F)
            quadToRelative(-83.67F, 0.0F, -158.11F, -32.22F)
            quadToRelative(-74.43F, -32.22F, -130.01F, -87.79F)
            quadToRelative(-55.58F, -55.58F, -87.79F, -130.01F)
            quadTo(71.87F, 563.67F, 71.87F, 480.0F)
            quadToRelative(0.0F, -84.43F, 32.22F, -158.49F)
            quadToRelative(32.22F, -74.06F, 87.79F, -129.63F)
            quadToRelative(55.58F, -55.58F, 130.01F, -87.79F)
            quadTo(396.33F, 71.87F, 480.0F, 71.87F)
            quadToRelative(84.43F, 0.0F, 158.49F, 32.22F)
            quadToRelative(74.06F, 32.22F, 129.63F, 87.79F)
            quadToRelative(55.58F, 55.58F, 87.79F, 129.63F)
            quadTo(888.13F, 395.57F, 888.13F, 480.0F)
            quadToRelative(0.0F, 83.67F, -32.22F, 158.11F)
            quadToRelative(-32.22F, 74.43F, -87.79F, 130.01F)
            quadToRelative(-55.58F, 55.58F, -129.63F, 87.79F)
            quadTo(564.43F, 888.13F, 480.0F, 888.13F)

            moveTo(479.52F, 795.37F)
            quadToRelative(25.04F, -35.52F, 44.04F, -74.4F)
            quadToRelative(19.0F, -38.88F, 31.0F, -82.4F)
            lineTo(405.2F, 638.57F)
            quadToRelative(12.0F, 43.52F, 30.64F, 82.4F)
            quadToRelative(18.64F, 38.88F, 43.69F, 74.4F)

            moveTo(376.72F, 779.37F)
            quadToRelative(-18.48F, -32.52F, -31.5F, -68.02F)
            quadToRelative(-13.02F, -35.5F, -22.02F, -72.78F)
            lineTo(206.63F, 638.57F)
            quadToRelative(28.28F, 49.52F, 71.42F, 86.28F)
            quadToRelative(43.14F, 36.76F, 98.66F, 54.52F)

            moveTo(582.57F, 779.37F)
            quadToRelative(55.52F, -17.76F, 98.9F, -54.52F)
            quadToRelative(43.38F, -36.76F, 71.66F, -86.28F)
            lineTo(636.57F, 638.57F)
            quadToRelative(-9.0F, 37.28F, -22.26F, 72.78F)
            reflectiveQuadToRelative(-31.74F, 68.02F)

            moveTo(172.87F, 559.28F)
            horizontalLineToRelative(134.33F)
            quadToRelative(-3.0F, -20.0F, -4.5F, -39.26F)
            quadToRelative(-1.5F, -19.26F, -1.5F, -40.02F)
            quadToRelative(0.0F, -21.0F, 1.5F, -40.14F)
            quadToRelative(1.5F, -19.14F, 4.5F, -39.14F)
            lineTo(172.87F, 400.72F)
            quadToRelative(-5.0F, 19.76F, -7.5F, 39.02F)
            reflectiveQuadTo(162.87F, 480.0F)
            quadToRelative(0.0F, 20.76F, 2.5F, 40.14F)
            quadToRelative(2.5F, 19.38F, 7.5F, 39.14F)

            moveTo(387.2F, 559.28F)
            horizontalLineToRelative(185.37F)
            quadToRelative(3.0F, -20.0F, 4.26F, -39.26F)
            quadToRelative(1.26F, -19.26F, 1.26F, -40.02F)
            quadToRelative(0.0F, -21.0F, -1.26F, -40.14F)
            quadToRelative(-1.26F, -19.14F, -4.26F, -39.14F)
            lineTo(387.2F, 400.72F)
            quadToRelative(-3.0F, 20.0F, -4.5F, 39.14F)
            quadToRelative(-1.5F, 19.14F, -1.5F, 40.14F)
            quadToRelative(0.0F, 20.76F, 1.5F, 40.02F)
            reflectiveQuadToRelative(4.5F, 39.26F)

            moveTo(652.56F, 559.28F)
            horizontalLineToRelative(134.09F)
            quadToRelative(5.0F, -19.76F, 7.5F, -39.14F)
            quadToRelative(2.5F, -19.38F, 2.5F, -40.14F)
            quadToRelative(0.0F, -21.0F, -2.5F, -40.26F)
            quadToRelative(-2.5F, -19.26F, -7.5F, -39.02F)
            lineTo(652.57F, 400.72F)
            quadToRelative(2.76F, 20.0F, 4.14F, 39.14F)
            quadTo(658.09F, 459.0F, 658.09F, 480.0F)
            quadToRelative(0.0F, 20.76F, -1.38F, 40.02F)
            quadToRelative(-1.38F, 19.26F, -4.14F, 39.26F)

            moveTo(636.56F, 321.2F)
            lineTo(753.13F, 321.2F)
            quadToRelative(-28.28F, -49.52F, -71.66F, -86.16F)
            quadToRelative(-43.38F, -36.64F, -98.9F, -54.4F)
            quadToRelative(18.48F, 32.52F, 31.74F, 67.9F)
            quadToRelative(13.26F, 35.38F, 22.26F, 72.66F)

            moveTo(405.2F, 321.2F)
            horizontalLineToRelative(149.37F)
            quadToRelative(-11.76F, -43.52F, -30.76F, -82.28F)
            reflectiveQuadToRelative(-44.28F, -74.28F)
            quadToRelative(-25.04F, 35.52F, -43.69F, 74.28F)
            quadToRelative(-18.64F, 38.76F, -30.64F, 82.28F)

            moveTo(206.63F, 321.2F)
            horizontalLineToRelative(116.57F)
            quadToRelative(9.0F, -37.28F, 22.02F, -72.66F)
            quadToRelative(13.02F, -35.38F, 31.5F, -67.9F)
            quadToRelative(-55.52F, 17.76F, -98.66F, 54.4F)
            quadToRelative(-43.14F, 36.64F, -71.42F, 86.16F)
            close()
        }.build()
        return _language!!
    }
private var _language: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconLanguagePreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.Language, contentDescription = null)
    }
}
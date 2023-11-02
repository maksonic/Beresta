package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_theme.BerestaTheme

/**
 * @Author maksonic on 19.02.2023
 */
val AppIcon.Info: ImageVector
    get() {
        if (_info != null) {
            return _info!!
        }
        _info = ImageVector.Builder(
            name = "Info",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(480.0F, 683.59F)
            quadToRelative(18.52F, 0.0F, 31.06F, -12.53F)
            quadToRelative(12.53F, -12.53F, 12.53F, -31.06F)
            lineTo(523.59F, 482.59F)
            quadToRelative(0.0F, -18.43F, -12.53F, -30.51F)
            quadTo(498.52F, 440.0F, 480.0F, 440.0F)
            reflectiveQuadToRelative(-31.06F, 12.53F)
            quadToRelative(-12.53F, 12.53F, -12.53F, 31.06F)
            lineTo(436.41F, 641.0F)
            quadToRelative(0.0F, 18.43F, 12.53F, 30.51F)
            quadToRelative(12.53F, 12.08F, 31.06F, 12.08F)

            moveTo(480.0F, 364.78F)
            quadToRelative(19.03F, 0.0F, 31.91F, -12.88F)
            reflectiveQuadTo(524.78F, 320.0F)
            quadToRelative(0.0F, -19.03F, -12.88F, -31.91F)
            reflectiveQuadTo(480.0F, 275.22F)
            quadToRelative(-19.03F, 0.0F, -31.91F, 12.88F)
            reflectiveQuadTo(435.22F, 320.0F)
            quadToRelative(0.0F, 19.03F, 12.88F, 31.91F)
            reflectiveQuadTo(480.0F, 364.78F)

            moveTo(480.0F, 888.13F)
            quadToRelative(-84.91F, 0.0F, -159.35F, -32.12F)
            reflectiveQuadToRelative(-129.49F, -87.18F)
            quadToRelative(-55.06F, -55.06F, -87.18F, -129.49F)
            quadTo(71.87F, 564.91F, 71.87F, 480.0F)
            reflectiveQuadToRelative(32.12F, -159.35F)
            quadToRelative(32.12F, -74.43F, 87.18F, -129.49F)
            quadToRelative(55.06F, -55.06F, 129.49F, -87.18F)
            quadTo(395.09F, 71.87F, 480.0F, 71.87F)
            reflectiveQuadToRelative(159.35F, 32.12F)
            quadToRelative(74.43F, 32.12F, 129.49F, 87.18F)
            quadToRelative(55.06F, 55.06F, 87.18F, 129.49F)
            quadTo(888.13F, 395.09F, 888.13F, 480.0F)
            reflectiveQuadToRelative(-32.12F, 159.35F)
            quadToRelative(-32.12F, 74.43F, -87.18F, 129.49F)
            quadToRelative(-55.06F, 55.06F, -129.49F, 87.18F)
            quadTo(564.91F, 888.13F, 480.0F, 888.13F)

            moveTo(480.0F, 480.0F)

            moveTo(480.0F, 797.13F)
            quadToRelative(131.81F, 0.0F, 224.47F, -92.54F)
            quadTo(797.13F, 612.04F, 797.13F, 480.0F)
            reflectiveQuadToRelative(-92.66F, -224.59F)
            quadToRelative(-92.66F, -92.54F, -224.47F, -92.54F)
            quadToRelative(-131.81F, 0.0F, -224.47F, 92.54F)
            quadTo(162.87F, 347.96F, 162.87F, 480.0F)
            reflectiveQuadToRelative(92.66F, 224.59F)
            quadToRelative(92.66F, 92.54F, 224.47F, 92.54F)
            close()
        }.build()
        return _info!!
    }
private var _info: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconInfoPreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.Info, contentDescription = null)

    }
}
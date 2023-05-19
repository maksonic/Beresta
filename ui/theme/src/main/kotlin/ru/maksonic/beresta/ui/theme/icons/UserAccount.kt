package ru.maksonic.beresta.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.BerestaTheme

/**
 * @Author maksonic on 19.02.2023
 */
val AppIcon.UserAccount: ImageVector
    get() {
        if (_userAccount != null) {
            return _userAccount!!
        }
        _userAccount = ImageVector.Builder(
            name = "UserAccount",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(235.2F, 680.41F)
            quadToRelative(51.0F, -38.52F, 113.52F, -60.54F)
            quadToRelative(62.52F, -22.02F, 131.34F, -22.02F)
            quadToRelative(68.81F, 0.0F, 131.64F, 22.38F)
            quadToRelative(62.83F, 22.38F, 113.11F, 60.42F)
            quadToRelative(34.28F, -41.24F, 53.31F, -91.92F)
            quadTo(797.13F, 538.04F, 797.13F, 480.0F)
            quadToRelative(0.0F, -131.57F, -92.78F, -224.35F)
            quadTo(611.57F, 162.87F, 480.0F, 162.87F)
            reflectiveQuadToRelative(-224.35F, 92.78F)
            quadTo(162.87F, 348.43F, 162.87F, 480.0F)
            quadToRelative(0.0F, 57.8F, 18.9F, 108.49F)
            reflectiveQuadToRelative(53.42F, 91.92F)

            moveTo(480.0F, 522.15F)
            quadToRelative(-59.96F, 0.0F, -100.93F, -40.86F)
            quadToRelative(-40.98F, -40.86F, -40.98F, -100.82F)
            quadToRelative(0.0F, -59.96F, 40.98F, -100.93F)
            quadTo(420.04F, 238.57F, 480.0F, 238.57F)
            reflectiveQuadToRelative(100.93F, 40.98F)
            quadToRelative(40.98F, 40.98F, 40.98F, 100.93F)
            quadToRelative(0.0F, 59.96F, -40.98F, 100.82F)
            quadTo(539.96F, 522.15F, 480.0F, 522.15F)

            moveTo(479.98F, 888.13F)
            quadToRelative(-84.65F, 0.0F, -159.09F, -32.1F)
            reflectiveQuadToRelative(-129.63F, -87.29F)
            quadToRelative(-55.2F, -55.19F, -87.29F, -129.65F)
            quadToRelative(-32.1F, -74.46F, -32.1F, -159.11F)
            quadToRelative(0.0F, -84.65F, 32.1F, -159.09F)
            reflectiveQuadToRelative(87.29F, -129.63F)
            quadToRelative(55.19F, -55.2F, 129.65F, -87.29F)
            quadToRelative(74.46F, -32.1F, 159.11F, -32.1F)
            quadToRelative(84.65F, 0.0F, 159.09F, 32.1F)
            reflectiveQuadToRelative(129.63F, 87.29F)
            quadToRelative(55.2F, 55.19F, 87.29F, 129.65F)
            quadToRelative(32.1F, 74.46F, 32.1F, 159.11F)
            quadToRelative(0.0F, 84.65F, -32.1F, 159.09F)
            reflectiveQuadToRelative(-87.29F, 129.63F)
            quadToRelative(-55.19F, 55.2F, -129.65F, 87.29F)
            quadToRelative(-74.46F, 32.1F, -159.11F, 32.1F)

            moveTo(480.0F, 797.13F)
            quadToRelative(51.8F, 0.0F, 97.37F, -14.9F)
            quadToRelative(45.56F, -14.9F, 84.57F, -42.94F)
            quadToRelative(-39.48F, -28.28F, -84.45F, -43.07F)
            quadToRelative(-44.97F, -14.78F, -97.49F, -14.78F)
            quadToRelative(-52.52F, 0.0F, -97.37F, 14.78F)
            quadToRelative(-44.85F, 14.78F, -84.33F, 43.07F)
            quadToRelative(39.0F, 28.04F, 84.45F, 42.94F)
            quadTo(428.2F, 797.13F, 480.0F, 797.13F)

            moveTo(480.0F, 438.57F)
            quadToRelative(25.04F, 0.0F, 41.56F, -16.52F)
            quadToRelative(16.52F, -16.52F, 16.52F, -41.56F)
            quadToRelative(0.0F, -25.04F, -16.52F, -41.68F)
            quadToRelative(-16.52F, -16.64F, -41.56F, -16.64F)
            reflectiveQuadToRelative(-41.56F, 16.64F)
            quadToRelative(-16.52F, 16.64F, -16.52F, 41.68F)
            quadToRelative(0.0F, 25.04F, 16.52F, 41.56F)
            quadToRelative(16.52F, 16.52F, 41.56F, 16.52F)

            moveTo(480.0F, 380.48F)

            moveTo(480.24F, 739.28F)
            close()
        }.build()
        return _userAccount!!
    }
private var _userAccount: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconUserAccountPreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.UserAccount, contentDescription = null)
    }
}
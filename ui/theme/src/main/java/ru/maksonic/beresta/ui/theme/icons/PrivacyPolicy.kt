package ru.maksonic.beresta.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.BerestaTheme

/**
 * @Author maksonic on 19.02.2023
 */
val AppIcon.PrivacyPolicy: ImageVector
    get() {
        if (_privacyPolicy != null) {
            return _privacyPolicy!!
        }
        _privacyPolicy = ImageVector.Builder(
            name = "PrivacyPolicy",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(480.03F, 556.41F)
            quadToRelative(31.53F, 0.0F, 53.96F, -22.46F)
            quadToRelative(22.42F, -22.45F, 22.42F, -53.99F)
            quadToRelative(0.0F, -31.53F, -22.46F, -53.96F)
            quadToRelative(-22.45F, -22.42F, -53.99F, -22.42F)
            quadToRelative(-31.53F, 0.0F, -53.96F, 22.46F)
            quadToRelative(-22.42F, 22.45F, -22.42F, 53.99F)
            quadToRelative(0.0F, 31.53F, 22.46F, 53.96F)
            quadToRelative(22.45F, 22.42F, 53.99F, 22.42F)

            moveTo(480.0F, 793.37F)
            quadToRelative(39.56F, -11.0F, 79.47F, -40.26F)
            quadToRelative(39.9F, -29.26F, 68.95F, -64.07F)
            lineToRelative(-69.85F, -69.85F)
            quadToRelative(-17.76F, 10.52F, -37.78F, 15.66F)
            quadTo(500.76F, 640.0F, 480.0F, 640.0F)
            quadToRelative(-66.0F, 0.0F, -113.0F, -47.0F)
            reflectiveQuadToRelative(-47.0F, -113.0F)
            quadToRelative(0.0F, -66.0F, 47.0F, -113.0F)
            reflectiveQuadToRelative(113.0F, -47.0F)
            quadToRelative(66.0F, 0.0F, 113.0F, 47.0F)
            reflectiveQuadToRelative(47.0F, 113.0F)
            quadToRelative(0.0F, 21.52F, -5.14F, 41.78F)
            quadToRelative(-5.14F, 20.26F, -15.66F, 38.78F)
            lineToRelative(56.89F, 57.13F)
            quadToRelative(20.0F, -40.52F, 30.52F, -83.85F)
            quadToRelative(10.52F, -43.33F, 10.52F, -89.8F)
            lineTo(717.13F, 257.15F)
            lineTo(480.0F, 168.35F)
            lineToRelative(-237.13F, 88.92F)
            lineTo(242.87F, 444.0F)
            quadToRelative(0.0F, 119.8F, 67.4F, 217.37F)
            quadToRelative(67.4F, 97.57F, 169.73F, 132.0F)

            moveTo(480.0F, 882.37F)
            quadToRelative(-11.0F, 0.0F, -20.5F, -1.44F)
            quadToRelative(-9.5F, -1.44F, -17.5F, -4.32F)
            quadToRelative(-127.59F, -46.72F, -208.86F, -167.35F)
            quadTo(151.87F, 588.63F, 151.87F, 444.0F)
            lineTo(151.87F, 257.15F)
            quadToRelative(0.0F, -28.35F, 16.41F, -51.34F)
            quadToRelative(16.41F, -22.99F, 42.76F, -33.19F)
            lineToRelative(237.13F, -88.8F)
            quadToRelative(15.91F, -5.72F, 31.83F, -5.72F)
            reflectiveQuadToRelative(31.83F, 5.72F)
            lineToRelative(237.13F, 88.8F)
            quadToRelative(26.35F, 10.2F, 42.76F, 33.19F)
            quadToRelative(16.41F, 22.99F, 16.41F, 51.34F)
            lineTo(808.13F, 444.0F)
            quadToRelative(0.0F, 144.63F, -81.27F, 265.26F)
            quadTo(645.59F, 829.89F, 518.0F, 876.61F)
            quadToRelative(-8.0F, 2.88F, -17.5F, 4.32F)
            quadToRelative(-9.5F, 1.44F, -20.5F, 1.44F)

            moveTo(492.78F, 480.72F)
            close()
        }.build()
        return _privacyPolicy!!
    }
private var _privacyPolicy: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconPrivacyPolicyPreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.PrivacyPolicy, contentDescription = null)
    }
}
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
 * @Author maksonic on 31.05.2023
 */
val AppIcon.ExpandMore: ImageVector
    get() {
        if (_expandMore != null) {
            return _expandMore!!
        }
        _expandMore = ImageVector.Builder(
            name = "ExpandMore",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(480.0F, 603.26F)
            quadToRelative(-8.96F, 0.0F, -17.03F, -2.98F)
            quadToRelative(-8.08F, -2.98F, -14.79F, -9.7F)
            lineToRelative(-185.0F, -185.0F)
            quadTo(250.5F, 392.91F, 251.0F, 374.26F)
            reflectiveQuadToRelative(13.17F, -31.33F)
            quadToRelative(12.67F, -12.67F, 31.83F, -12.67F)
            reflectiveQuadToRelative(31.83F, 12.67F)
            lineTo(480.0F, 495.11F)
            lineToRelative(153.17F, -153.17F)
            quadToRelative(12.67F, -12.67F, 31.33F, -12.17F)
            reflectiveQuadToRelative(31.33F, 13.17F)
            quadToRelative(12.67F, 12.67F, 12.67F, 31.83F)
            reflectiveQuadToRelative(-12.67F, 31.83F)
            lineToRelative(-184.0F, 184.0F)
            quadToRelative(-6.72F, 6.72F, -14.79F, 9.7F)
            quadToRelative(-8.08F, 2.98F, -17.03F, 2.98F)
            close()
        }.build()
        return _expandMore!!
    }
private var _expandMore: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconExpandMorePreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.ExpandMore, contentDescription = null)
    }
}
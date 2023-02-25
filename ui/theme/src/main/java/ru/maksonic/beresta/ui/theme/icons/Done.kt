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
 * @Author maksonic on 25.02.2023
 */
val AppIcon.Done: ImageVector
    get() {
        if (_done != null) {
            return _done!!
        }
        _done = ImageVector.Builder(
            name = "Done",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(382.0F, 708.5F)
            quadToRelative(-8.96F, 0.0F, -17.03F, -2.98F)
            reflectiveQuadToRelative(-14.79F, -9.7F)
            lineToRelative(-172.0F, -172.0F)
            quadTo(165.5F, 511.15F, 166.0F, 491.5F)
            reflectiveQuadToRelative(13.17F, -32.33F)
            quadToRelative(12.67F, -12.67F, 32.06F, -12.67F)
            reflectiveQuadToRelative(32.06F, 12.67F)
            lineTo(382.0F, 597.87F)
            lineToRelative(334.7F, -334.7F)
            quadToRelative(12.67F, -12.67F, 32.33F, -12.79F)
            quadToRelative(19.65F, -0.12F, 32.56F, 12.79F)
            quadToRelative(12.67F, 12.67F, 12.67F, 32.45F)
            quadToRelative(0.0F, 19.77F, -12.67F, 32.44F)
            lineTo(413.83F, 695.83F)
            quadToRelative(-6.72F, 6.72F, -14.79F, 9.7F)
            quadTo(390.96F, 708.5F, 382.0F, 708.5F)
            close()
        }.build()
        return _done!!
    }
private var _done: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconDonePreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.Done, contentDescription = null)
    }
}
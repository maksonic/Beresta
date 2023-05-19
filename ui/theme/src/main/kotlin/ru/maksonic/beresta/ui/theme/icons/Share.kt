package ru.maksonic.beresta.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

/**
 * @Author maksonic on 31.01.2023
 */
val AppIcon.Share: ImageVector
    get() {
        if (_share != null) {
            return _share!!
        }
        _share = materialIcon(name = "Share") {
            materialPath {
                moveTo(18.0F, 22.175F)
                quadToRelative(-1.325F, 0.0F, -2.25F, -0.925F)
                reflectiveQuadTo(14.825F, 19.0F)
                quadToRelative(0.0F, -0.175F, 0.025F, -0.375F)
                reflectiveQuadToRelative(0.075F, -0.35F)
                lineTo(8.15F, 14.35F)
                quadToRelative(-0.425F, 0.375F, -0.987F, 0.6F)
                quadToRelative(-0.563F, 0.225F, -1.163F, 0.225F)
                quadToRelative(-1.325F, 0.0F, -2.25F, -0.925F)
                reflectiveQuadTo(2.825F, 12.0F)
                quadToRelative(0.0F, -1.325F, 0.925F, -2.25F)
                reflectiveQuadTo(6.0F, 8.825F)
                quadToRelative(0.6F, 0.0F, 1.163F, 0.225F)
                quadToRelative(0.562F, 0.225F, 0.987F, 0.6F)
                lineToRelative(6.775F, -3.925F)
                quadToRelative(-0.05F, -0.15F, -0.075F, -0.35F)
                quadToRelative(-0.025F, -0.2F, -0.025F, -0.375F)
                quadToRelative(0.0F, -1.325F, 0.925F, -2.25F)
                reflectiveQuadTo(18.0F, 1.825F)
                quadToRelative(1.325F, 0.0F, 2.25F, 0.925F)
                reflectiveQuadTo(21.175F, 5.0F)
                quadToRelative(0.0F, 1.325F, -0.925F, 2.25F)
                reflectiveQuadTo(18.0F, 8.175F)
                quadToRelative(-0.6F, 0.0F, -1.162F, -0.213F)
                quadToRelative(-0.563F, -0.212F, -0.988F, -0.612F)
                lineToRelative(-6.775F, 3.925F)
                quadToRelative(0.05F, 0.15F, 0.075F, 0.35F)
                quadToRelative(0.025F, 0.2F, 0.025F, 0.375F)
                reflectiveQuadToRelative(-0.025F, 0.375F)
                quadToRelative(-0.025F, 0.2F, -0.075F, 0.35F)
                lineToRelative(6.775F, 3.925F)
                quadToRelative(0.425F, -0.4F, 0.988F, -0.612F)
                quadToRelative(0.562F, -0.213F, 1.162F, -0.213F)
                quadToRelative(1.325F, 0.0F, 2.25F, 0.925F)
                reflectiveQuadToRelative(0.925F, 2.25F)
                quadToRelative(0.0F, 1.325F, -0.925F, 2.25F)
                reflectiveQuadToRelative(-2.25F, 0.925F)

                moveTo(18.0F, 6.0F)
                quadToRelative(0.425F, 0.0F, 0.712F, -0.287F)
                quadTo(19.0F, 5.425F, 19.0F, 5.0F)
                reflectiveQuadToRelative(-0.288F, -0.713F)
                quadTo(18.425F, 4.0F, 18.0F, 4.0F)
                reflectiveQuadToRelative(-0.712F, 0.287F)
                quadTo(17.0F, 4.575F, 17.0F, 5.0F)
                reflectiveQuadToRelative(0.288F, 0.713F)
                quadTo(17.575F, 6.0F, 18.0F, 6.0F)

                moveTo(6.0F, 13.0F)
                quadToRelative(0.425F, 0.0F, 0.713F, -0.288F)
                quadTo(7.0F, 12.425F, 7.0F, 12.0F)
                reflectiveQuadToRelative(-0.287F, -0.713F)
                quadTo(6.425F, 11.0F, 6.0F, 11.0F)
                reflectiveQuadToRelative(-0.713F, 0.287F)
                quadTo(5.0F, 11.575F, 5.0F, 12.0F)
                reflectiveQuadToRelative(0.287F, 0.712F)
                quadTo(5.575F, 13.0F, 6.0F, 13.0F)

                moveTo(18.0F, 20.0F)
                quadToRelative(0.425F, 0.0F, 0.712F, -0.288F)
                quadTo(19.0F, 19.425F, 19.0F, 19.0F)
                reflectiveQuadToRelative(-0.288F, -0.712F)
                quadTo(18.425F, 18.0F, 18.0F, 18.0F)
                reflectiveQuadToRelative(-0.712F, 0.288F)
                quadTo(17.0F, 18.575F, 17.0F, 19.0F)
                reflectiveQuadToRelative(0.288F, 0.712F)
                quadTo(17.575F, 20.0F, 18.0F, 20.0F)

                moveTo(18.0F, 5.0F)

                moveTo(6.0F, 12.0F)

                moveTo(18.0F, 19.0F)
                close()
            }
        }
        return _share!!
    }

private var _share: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconSharePreview() {
    Image(imageVector = AppIcon.Share, contentDescription = null)
}
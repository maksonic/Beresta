package ru.maksonic.beresta.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.ui.theme.BerestaTheme

/**
 * @Author maksonic on 08.03.2023
 */
val AppIcon.DriveFile: ImageVector
    get() {
        if (_driveFile != null) {
            return _driveFile!!
        }
        _driveFile = materialIcon(name = "DriveFile") {
            materialPath {
                moveToRelative(9.675F, 21.1F)
                lineToRelative(4.275F, -4.275F)
                horizontalLineTo(20.0F)
                quadToRelative(0.875F, 0.0F, 1.513F, 0.625F)
                quadToRelative(0.637F, 0.625F, 0.637F, 1.5F)
                quadToRelative(0.0F, 0.9F, -0.637F, 1.525F)
                quadToRelative(-0.638F, 0.625F, -1.513F, 0.625F)

                moveTo(3.95F, 19.0F)
                horizontalLineToRelative(1.3F)
                lineToRelative(8.575F, -8.575F)
                lineToRelative(-1.3F, -1.3F)
                lineTo(3.95F, 17.7F)

                moveTo(18.225F, 9.0F)
                lineTo(13.95F, 4.75F)
                lineToRelative(1.35F, -1.375F)
                quadToRelative(0.6F, -0.6F, 1.475F, -0.6F)
                quadToRelative(0.875F, 0.0F, 1.5F, 0.6F)
                lineToRelative(1.3F, 1.325F)
                quadToRelative(0.625F, 0.6F, 0.638F, 1.45F)
                quadToRelative(0.012F, 0.85F, -0.588F, 1.45F)

                moveTo(3.0F, 21.1F)
                quadToRelative(-0.475F, 0.0F, -0.812F, -0.337F)
                quadToRelative(-0.338F, -0.338F, -0.338F, -0.813F)
                verticalLineTo(17.3F)
                quadToRelative(0.0F, -0.225F, 0.088F, -0.438F)
                quadToRelative(0.087F, -0.212F, 0.262F, -0.387F)
                lineToRelative(10.3F, -10.3F)
                lineToRelative(4.275F, 4.275F)
                lineToRelative(-10.3F, 10.3F)
                quadToRelative(-0.15F, 0.175F, -0.362F, 0.263F)
                quadToRelative(-0.213F, 0.087F, -0.438F, 0.087F)

                moveTo(13.175F, 9.775F)
                lineToRelative(-0.65F, -0.65F)
                lineToRelative(1.3F, 1.3F)
                close()
            }
        }
        return _driveFile!!
    }

private var _driveFile: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconDriveFilePreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.DriveFile, contentDescription = null)
    }
}
package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.common.ui_theme.BerestaTheme

/**
 * @Author maksonic on 09.03.2023
 */
val AppIcon.Wallpaper: ImageVector
    get() {
        if (_wallpaper != null) {
            return _wallpaper!!
        }
        _wallpaper = materialIcon(name = "BackgroundWallpaper") {
            materialPath {
                moveTo(15.5F, 10.0F)
                quadToRelative(-0.65F, 0.0F, -1.075F, -0.425F)
                quadTo(14.0F, 9.15F, 14.0F, 8.5F)
                quadToRelative(0.0F, -0.65F, 0.425F, -1.075F)
                quadTo(14.85F, 7.0F, 15.5F, 7.0F)
                quadToRelative(0.65F, 0.0F, 1.075F, 0.425F)
                quadTo(17.0F, 7.85F, 17.0F, 8.5F)
                quadToRelative(0.0F, 0.65F, -0.425F, 1.075F)
                quadTo(16.15F, 10.0F, 15.5F, 10.0F)

                moveTo(3.925F, 10.9F)
                quadToRelative(-0.475F, 0.0F, -0.8F, -0.325F)
                quadToRelative(-0.325F, -0.325F, -0.325F, -0.8F)
                verticalLineToRelative(-4.7F)
                quadToRelative(0.0F, -0.95F, 0.663F, -1.613F)
                quadToRelative(0.662F, -0.662F, 1.612F, -0.662F)
                horizontalLineToRelative(4.7F)
                quadToRelative(0.475F, 0.0F, 0.8F, 0.325F)
                quadToRelative(0.325F, 0.325F, 0.325F, 0.8F)
                quadToRelative(0.0F, 0.5F, -0.325F, 0.825F)
                quadToRelative(-0.325F, 0.325F, -0.8F, 0.325F)
                horizontalLineToRelative(-4.7F)
                verticalLineToRelative(4.7F)
                quadToRelative(0.0F, 0.475F, -0.325F, 0.8F)
                quadToRelative(-0.325F, 0.325F, -0.825F, 0.325F)

                moveTo(5.075F, 21.2F)
                quadToRelative(-0.95F, 0.0F, -1.612F, -0.662F)
                quadToRelative(-0.663F, -0.663F, -0.663F, -1.613F)
                verticalLineToRelative(-4.7F)
                quadToRelative(0.0F, -0.475F, 0.325F, -0.8F)
                quadToRelative(0.325F, -0.325F, 0.8F, -0.325F)
                quadToRelative(0.5F, 0.0F, 0.825F, 0.325F)
                quadToRelative(0.325F, 0.325F, 0.325F, 0.8F)
                verticalLineToRelative(4.7F)
                horizontalLineToRelative(4.7F)
                quadToRelative(0.475F, 0.0F, 0.8F, 0.325F)
                quadToRelative(0.325F, 0.325F, 0.325F, 0.825F)
                quadToRelative(0.0F, 0.475F, -0.325F, 0.8F)
                quadToRelative(-0.325F, 0.325F, -0.8F, 0.325F)

                moveTo(20.075F, 10.9F)
                quadToRelative(-0.5F, 0.0F, -0.825F, -0.325F)
                quadToRelative(-0.325F, -0.325F, -0.325F, -0.8F)
                verticalLineToRelative(-4.7F)
                horizontalLineToRelative(-4.7F)
                quadToRelative(-0.475F, 0.0F, -0.8F, -0.325F)
                quadToRelative(-0.325F, -0.325F, -0.325F, -0.825F)
                quadToRelative(0.0F, -0.475F, 0.325F, -0.8F)
                quadToRelative(0.325F, -0.325F, 0.8F, -0.325F)
                horizontalLineToRelative(4.7F)
                quadToRelative(0.95F, 0.0F, 1.613F, 0.662F)
                quadToRelative(0.662F, 0.663F, 0.662F, 1.613F)
                verticalLineToRelative(4.7F)
                quadToRelative(0.0F, 0.475F, -0.325F, 0.8F)
                quadToRelative(-0.325F, 0.325F, -0.8F, 0.325F)

                moveTo(14.225F, 21.2F)
                quadToRelative(-0.475F, 0.0F, -0.8F, -0.325F)
                quadToRelative(-0.325F, -0.325F, -0.325F, -0.8F)
                quadToRelative(0.0F, -0.5F, 0.325F, -0.825F)
                quadToRelative(0.325F, -0.325F, 0.8F, -0.325F)
                horizontalLineToRelative(4.7F)
                verticalLineToRelative(-4.7F)
                quadToRelative(0.0F, -0.475F, 0.325F, -0.8F)
                quadToRelative(0.325F, -0.325F, 0.825F, -0.325F)
                quadToRelative(0.475F, 0.0F, 0.8F, 0.325F)
                quadToRelative(0.325F, 0.325F, 0.325F, 0.8F)
                verticalLineToRelative(4.7F)
                quadToRelative(0.0F, 0.95F, -0.662F, 1.613F)
                quadToRelative(-0.663F, 0.662F, -1.613F, 0.662F)

                moveTo(7.025F, 17.0F)
                quadToRelative(-0.35F, 0.0F, -0.512F, -0.312F)
                quadToRelative(-0.163F, -0.313F, 0.062F, -0.613F)
                lineToRelative(1.975F, -2.65F)
                quadToRelative(0.175F, -0.25F, 0.462F, -0.25F)
                quadToRelative(0.288F, 0.0F, 0.463F, 0.25F)
                lineTo(11.25F, 15.8F)
                lineToRelative(2.525F, -3.375F)
                quadToRelative(0.175F, -0.25F, 0.463F, -0.25F)
                quadToRelative(0.287F, 0.0F, 0.462F, 0.25F)
                lineToRelative(2.725F, 3.65F)
                quadToRelative(0.225F, 0.3F, 0.05F, 0.613F)
                quadToRelative(-0.175F, 0.312F, -0.5F, 0.312F)
                close()
            }
        }
        return _wallpaper!!
    }

private var _wallpaper: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconBackgroundWallpaperPreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.Wallpaper, contentDescription = null)
    }
}
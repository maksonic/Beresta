package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

/**
 * @Author maksonic on 31.01.2023
 */
val AppIcon.Folder: ImageVector
    get() {
        if (_folder != null) {
            return _folder!!
        }
        _folder = materialIcon(name = "Folder") {
            materialPath {
                moveTo(4.075F, 20.2F)
                quadToRelative(-0.95F, 0.0F, -1.613F, -0.662F)
                quadToRelative(-0.662F, -0.663F, -0.662F, -1.613F)
                lineTo(1.8F, 5.975F)
                quadToRelative(0.0F, -0.95F, 0.662F, -1.613F)
                quadToRelative(0.663F, -0.662F, 1.613F, -0.662F)
                horizontalLineToRelative(4.9F)
                quadToRelative(0.45F, 0.0F, 0.863F, 0.175F)
                quadToRelative(0.412F, 0.175F, 0.737F, 0.5F)
                lineTo(12.0F, 5.8F)
                horizontalLineToRelative(7.925F)
                quadToRelative(0.95F, 0.0F, 1.613F, 0.663F)
                quadToRelative(0.662F, 0.662F, 0.662F, 1.612F)
                verticalLineToRelative(9.85F)
                quadToRelative(0.0F, 0.95F, -0.662F, 1.613F)
                quadToRelative(-0.663F, 0.662F, -1.613F, 0.662F)

                moveTo(4.075F, 5.975F)
                verticalLineToRelative(11.95F)
                horizontalLineToRelative(15.85F)
                verticalLineToRelative(-9.85F)
                lineTo(11.05F, 8.075F)
                lineToRelative(-2.075F, -2.1F)
                horizontalLineToRelative(-4.9F)

                moveTo(4.075F, 5.975F)
                verticalLineToRelative(11.95F)
                close()
            }
        }
        return _folder!!
    }

private var _folder: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconFolderPreview() {
    Image(imageVector = AppIcon.Folder, contentDescription = null)
}
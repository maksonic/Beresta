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
val AppIcon.CreateFolder: ImageVector
    get() {
        if (_createFolder != null) {
            return _createFolder!!
        }
        _createFolder = materialIcon(name = "CreateFolder") {
            materialPath {
                moveTo(15.0F, 16.15F)
                quadToRelative(0.45F, 0.0F, 0.75F, -0.313F)
                quadToRelative(0.3F, -0.312F, 0.3F, -0.737F)
                verticalLineToRelative(-1.05F)
                horizontalLineToRelative(1.05F)
                quadToRelative(0.425F, 0.0F, 0.738F, -0.3F)
                quadToRelative(0.312F, -0.3F, 0.312F, -0.75F)
                reflectiveQuadToRelative(-0.312F, -0.75F)
                quadToRelative(-0.313F, -0.3F, -0.738F, -0.3F)
                horizontalLineToRelative(-1.05F)
                lineTo(16.05F, 10.9F)
                quadToRelative(0.0F, -0.425F, -0.3F, -0.738F)
                quadToRelative(-0.3F, -0.312F, -0.75F, -0.312F)
                reflectiveQuadToRelative(-0.75F, 0.312F)
                quadToRelative(-0.3F, 0.313F, -0.3F, 0.738F)
                verticalLineToRelative(1.05F)
                lineTo(12.9F, 11.95F)
                quadToRelative(-0.425F, 0.0F, -0.737F, 0.3F)
                quadToRelative(-0.313F, 0.3F, -0.313F, 0.75F)
                reflectiveQuadToRelative(0.313F, 0.75F)
                quadToRelative(0.312F, 0.3F, 0.737F, 0.3F)
                horizontalLineToRelative(1.05F)
                verticalLineToRelative(1.05F)
                quadToRelative(0.0F, 0.425F, 0.3F, 0.737F)
                quadToRelative(0.3F, 0.313F, 0.75F, 0.313F)

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
        return _createFolder!!
    }

private var _createFolder: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconCreateFolderPreview() {
    Image(imageVector = AppIcon.CreateFolder, contentDescription = null)
}
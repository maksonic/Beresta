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
val AppIcon.FolderOpen: ImageVector
    get() {
        if (_folderOpen != null) {
            return _folderOpen!!
        }
        _folderOpen = materialIcon(name = "FolderOpen") {
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
                lineTo(11.05F, 8.075F)
                lineToRelative(-2.075F, -2.1F)
                horizontalLineToRelative(-4.9F)
                lineTo(4.075F, 17.95F)
                lineToRelative(1.9F, -6.275F)
                quadToRelative(0.225F, -0.725F, 0.837F, -1.163F)
                quadToRelative(0.613F, -0.437F, 1.363F, -0.437F)
                lineTo(20.6F, 10.075F)
                quadToRelative(1.15F, 0.0F, 1.825F, 0.925F)
                reflectiveQuadToRelative(0.35F, 2.025F)
                lineToRelative(-1.625F, 5.4F)
                quadToRelative(-0.275F, 0.85F, -0.912F, 1.312F)
                quadToRelative(-0.638F, 0.463F, -1.513F, 0.463F)

                moveTo(6.475F, 17.925F)
                lineTo(18.9F, 17.925F)
                lineToRelative(1.7F, -5.575F)
                lineTo(8.175F, 12.35F)

                moveTo(6.475F, 17.925F)
                lineTo(8.175F, 12.35F)
                lineTo(6.475F, 17.925F)

                moveTo(4.075F, 10.35F)
                lineTo(4.075F, 5.975F)
                verticalLineToRelative(4.375F)
                close()
            }
        }
        return _folderOpen!!
    }

private var _folderOpen: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconFolderOpenPreview() {
    Image(imageVector = AppIcon.FolderOpen, contentDescription = null)
}
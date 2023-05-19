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
val AppIcon.MoveFolder: ImageVector
    get() {
        if (_moveFolder != null) {
            return _moveFolder!!
        }
        _moveFolder = materialIcon(name = "MoveFolder") {
            materialPath {
                moveTo(12.2F, 14.0F)
                lineTo(11.275F, 14.925F)
                quadTo(10.975F, 15.225F, 10.975F, 15.663F)
                quadTo(10.975F, 16.1F, 11.275F, 16.325F)
                quadTo(11.575F, 16.6F, 11.988F, 16.6F)
                quadTo(12.4F, 16.6F, 12.675F, 16.325F)
                lineTo(15.2F, 13.8F)
                quadTo(15.55F, 13.45F, 15.55F, 13.0F)
                quadTo(15.55F, 12.55F, 15.2F, 12.2F)
                lineTo(12.675F, 9.675F)
                quadTo(12.4F, 9.4F, 11.975F, 9.4F)
                quadTo(11.55F, 9.4F, 11.275F, 9.675F)
                quadTo(11.0F, 9.95F, 11.0F, 10.375F)
                quadTo(11.0F, 10.8F, 11.275F, 11.075F)
                lineTo(12.2F, 12.0F)
                horizontalLineTo(9.0F)
                quadTo(8.575F, 12.0F, 8.288F, 12.287F)
                quadTo(8.0F, 12.575F, 8.0F, 13.0F)
                quadTo(8.0F, 13.425F, 8.288F, 13.712F)
                quadTo(8.575F, 14.0F, 9.0F, 14.0F)

                moveTo(4.075F, 20.2F)
                quadTo(3.125F, 20.2F, 2.462F, 19.538F)
                quadTo(1.8F, 18.875F, 1.8F, 17.925F)
                verticalLineTo(5.975F)
                quadTo(1.8F, 5.025F, 2.462F, 4.362F)
                quadTo(3.125F, 3.7F, 4.075F, 3.7F)
                horizontalLineTo(8.975F)
                quadTo(9.425F, 3.7F, 9.838F, 3.875F)
                quadTo(10.25F, 4.05F, 10.575F, 4.375F)
                lineTo(12.0F, 5.8F)
                horizontalLineTo(19.925F)
                quadTo(20.875F, 5.8F, 21.538F, 6.463F)
                quadTo(22.2F, 7.125F, 22.2F, 8.075F)
                verticalLineTo(17.925F)
                quadTo(22.2F, 18.875F, 21.538F, 19.538F)
                quadTo(20.875F, 20.2F, 19.925F, 20.2F)

                moveTo(4.075F, 5.975F)
                verticalLineTo(17.925F)
                quadTo(4.075F, 17.925F, 4.075F, 17.925F)
                quadTo(4.075F, 17.925F, 4.075F, 17.925F)
                horizontalLineTo(19.925F)
                quadTo(19.925F, 17.925F, 19.925F, 17.925F)
                quadTo(19.925F, 17.925F, 19.925F, 17.925F)
                verticalLineTo(8.075F)
                quadTo(19.925F, 8.075F, 19.925F, 8.075F)
                quadTo(19.925F, 8.075F, 19.925F, 8.075F)
                horizontalLineTo(11.05F)
                lineTo(8.975F, 5.975F)
                horizontalLineTo(4.075F)
                quadTo(4.075F, 5.975F, 4.075F, 5.975F)
                quadTo(4.075F, 5.975F, 4.075F, 5.975F)

                moveTo(4.075F, 5.975F)
                quadTo(4.075F, 5.975F, 4.075F, 5.975F)
                quadTo(4.075F, 5.975F, 4.075F, 5.975F)
                verticalLineTo(8.075F)
                quadTo(4.075F, 8.075F, 4.075F, 8.075F)
                quadTo(4.075F, 8.075F, 4.075F, 8.075F)
                verticalLineTo(17.925F)
                quadTo(4.075F, 17.925F, 4.075F, 17.925F)
                quadTo(4.075F, 17.925F, 4.075F, 17.925F)
                quadTo(4.075F, 17.925F, 4.075F, 17.925F)
                quadTo(4.075F, 17.925F, 4.075F, 17.925F)
                close()
            }
        }
        return _moveFolder!!
    }

private var _moveFolder: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconMoveFolderPreview() {
    Image(imageVector = AppIcon.MoveFolder, contentDescription = null)
}
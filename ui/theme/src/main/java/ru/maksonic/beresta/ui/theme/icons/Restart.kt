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
val AppIcon.Restart: ImageVector
    get() {
        if (_restart != null) {
            return _restart!!
        }
        _restart = materialIcon(name = "Restart") {
            materialPath {
                moveTo(9.65F, 20.85F)
                quadTo(7.05F, 20.1F, 5.425F, 17.95F)
                quadTo(3.8F, 15.8F, 3.8F, 13.0F)
                quadTo(3.8F, 11.625F, 4.25F, 10.363F)
                quadTo(4.7F, 9.1F, 5.525F, 8.025F)
                quadTo(5.825F, 7.65F, 6.3F, 7.625F)
                quadTo(6.775F, 7.6F, 7.175F, 8.0F)
                quadTo(7.475F, 8.275F, 7.488F, 8.712F)
                quadTo(7.5F, 9.15F, 7.225F, 9.525F)
                quadTo(6.675F, 10.275F, 6.375F, 11.162F)
                quadTo(6.075F, 12.05F, 6.075F, 13.0F)
                quadTo(6.075F, 14.975F, 7.213F, 16.525F)
                quadTo(8.35F, 18.075F, 10.15F, 18.675F)
                quadTo(10.525F, 18.8F, 10.762F, 19.113F)
                quadTo(11.0F, 19.425F, 11.0F, 19.775F)
                quadTo(11.0F, 20.35F, 10.588F, 20.688F)
                quadTo(10.175F, 21.025F, 9.65F, 20.85F)

                moveTo(14.35F, 20.85F)
                quadTo(13.825F, 21.025F, 13.413F, 20.688F)
                quadTo(13.0F, 20.35F, 13.0F, 19.75F)
                quadTo(13.0F, 19.425F, 13.238F, 19.113F)
                quadTo(13.475F, 18.8F, 13.85F, 18.675F)
                quadTo(15.65F, 18.05F, 16.788F, 16.512F)
                quadTo(17.925F, 14.975F, 17.925F, 13.0F)
                quadTo(17.925F, 10.575F, 16.238F, 8.862F)
                quadTo(14.55F, 7.15F, 12.125F, 7.1F)
                horizontalLineTo(12.025F)
                lineTo(12.425F, 7.5F)
                quadTo(12.725F, 7.8F, 12.725F, 8.238F)
                quadTo(12.725F, 8.675F, 12.425F, 8.95F)
                quadTo(12.15F, 9.25F, 11.7F, 9.25F)
                quadTo(11.25F, 9.25F, 10.975F, 8.95F)
                lineTo(8.7F, 6.7F)
                quadTo(8.55F, 6.55F, 8.475F, 6.338F)
                quadTo(8.4F, 6.125F, 8.4F, 5.9F)
                quadTo(8.4F, 5.675F, 8.475F, 5.475F)
                quadTo(8.55F, 5.275F, 8.7F, 5.125F)
                lineTo(10.975F, 2.85F)
                quadTo(11.25F, 2.55F, 11.7F, 2.55F)
                quadTo(12.15F, 2.55F, 12.425F, 2.85F)
                quadTo(12.725F, 3.15F, 12.725F, 3.587F)
                quadTo(12.725F, 4.025F, 12.425F, 4.325F)
                lineTo(11.95F, 4.8F)
                horizontalLineTo(12.05F)
                quadTo(15.475F, 4.8F, 17.838F, 7.2F)
                quadTo(20.2F, 9.6F, 20.2F, 13.0F)
                quadTo(20.2F, 15.775F, 18.575F, 17.925F)
                quadTo(16.95F, 20.075F, 14.35F, 20.85F)

                close()
            }
        }
        return _restart!!
    }
private var _restart: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconRestartPreview() {
    Image(imageVector = AppIcon.Restart, contentDescription = null)
}
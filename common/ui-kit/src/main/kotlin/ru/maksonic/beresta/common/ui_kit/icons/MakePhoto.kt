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
val AppIcon.MakePhoto: ImageVector
    get() {
        if (_makePhoto != null) {
            return _makePhoto!!
        }
        _makePhoto = materialIcon(name = "MakePhoto") {
            materialPath {
                moveTo(11.05F, 18.5F)
                quadToRelative(1.875F, 0.0F, 3.188F, -1.312F)
                quadTo(15.55F, 15.875F, 15.55F, 14.0F)
                quadToRelative(0.0F, -1.875F, -1.312F, -3.188F)
                quadTo(12.925F, 9.5F, 11.05F, 9.5F)
                quadToRelative(-1.875F, 0.0F, -3.187F, 1.312F)
                quadTo(6.55F, 12.125F, 6.55F, 14.0F)
                quadToRelative(0.0F, 1.875F, 1.313F, 3.188F)
                quadTo(9.175F, 18.5F, 11.05F, 18.5F)

                moveTo(3.125F, 22.2F)
                quadToRelative(-0.95F, 0.0F, -1.612F, -0.662F)
                quadToRelative(-0.663F, -0.663F, -0.663F, -1.613F)
                lineTo(0.85F, 8.075F)
                quadToRelative(0.0F, -0.95F, 0.663F, -1.612F)
                quadToRelative(0.662F, -0.663F, 1.612F, -0.663F)
                lineTo(6.15F, 5.8F)
                lineTo(7.4F, 4.45F)
                quadToRelative(0.325F, -0.35F, 0.762F, -0.55F)
                quadToRelative(0.438F, -0.2F, 0.913F, -0.2F)
                horizontalLineToRelative(3.7F)
                quadToRelative(0.475F, 0.0F, 0.8F, 0.337F)
                quadToRelative(0.325F, 0.338F, 0.325F, 0.813F)
                verticalLineToRelative(3.225F)
                lineTo(3.125F, 8.075F)
                verticalLineToRelative(11.85F)
                horizontalLineToRelative(15.85F)
                lineTo(18.975F, 11.15F)
                lineTo(20.1F, 11.15F)
                quadToRelative(0.25F, 0.0F, 0.45F, 0.075F)
                reflectiveQuadToRelative(0.363F, 0.237F)
                quadToRelative(0.162F, 0.163F, 0.25F, 0.375F)
                quadToRelative(0.087F, 0.213F, 0.087F, 0.438F)
                verticalLineToRelative(7.65F)
                quadToRelative(0.0F, 0.95F, -0.663F, 1.613F)
                quadToRelative(-0.662F, 0.662F, -1.612F, 0.662F)

                moveTo(18.975F, 6.075F)
                lineTo(17.95F, 6.075F)
                quadToRelative(-0.425F, 0.0F, -0.737F, -0.3F)
                quadToRelative(-0.313F, -0.3F, -0.313F, -0.75F)
                reflectiveQuadToRelative(0.313F, -0.75F)
                quadToRelative(0.312F, -0.3F, 0.737F, -0.3F)
                horizontalLineToRelative(1.025F)
                verticalLineToRelative(-1.0F)
                quadToRelative(0.0F, -0.45F, 0.3F, -0.75F)
                reflectiveQuadToRelative(0.75F, -0.3F)
                quadToRelative(0.45F, 0.0F, 0.75F, 0.3F)
                reflectiveQuadToRelative(0.3F, 0.75F)
                verticalLineToRelative(1.0F)
                lineTo(22.1F, 3.975F)
                quadToRelative(0.425F, 0.0F, 0.725F, 0.3F)
                reflectiveQuadToRelative(0.3F, 0.75F)
                quadToRelative(0.0F, 0.45F, -0.3F, 0.75F)
                reflectiveQuadToRelative(-0.725F, 0.3F)
                horizontalLineToRelative(-1.025F)
                lineTo(21.075F, 7.1F)
                quadToRelative(0.0F, 0.425F, -0.3F, 0.737F)
                quadToRelative(-0.3F, 0.313F, -0.75F, 0.313F)
                reflectiveQuadToRelative(-0.75F, -0.313F)
                quadToRelative(-0.3F, -0.312F, -0.3F, -0.737F)

                moveTo(3.125F, 8.075F)
                lineTo(3.125F, 19.925F)
                lineTo(3.125F, 8.075F)
                close()
            }
        }
        return _makePhoto!!
    }

private var _makePhoto: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconMakePhotoPreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.MakePhoto, contentDescription = null)
    }
}
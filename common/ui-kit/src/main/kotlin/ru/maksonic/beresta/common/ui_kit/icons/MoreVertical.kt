package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.common.ui_theme.BerestaTheme

/**
 * @Author maksonic on 09.03.2023
 */
val AppIcon.MoreVertical: ImageVector
    get() {
        if (_moreVertical != null) {
            return _moreVertical!!
        }
        _moreVertical = materialIcon(name = "MoreVertical") {
            materialPath {
                moveTo(12.0F, 20.275F)
                quadToRelative(-0.85F, 0.0F, -1.475F, -0.612F)
                quadToRelative(-0.625F, -0.613F, -0.625F, -1.488F)
                quadToRelative(0.0F, -0.85F, 0.625F, -1.463F)
                quadTo(11.15F, 16.1F, 12.0F, 16.1F)
                quadToRelative(0.875F, 0.0F, 1.488F, 0.612F)
                quadToRelative(0.612F, 0.613F, 0.612F, 1.463F)
                quadToRelative(0.0F, 0.875F, -0.612F, 1.488F)
                quadToRelative(-0.613F, 0.612F, -1.488F, 0.612F)

                moveTo(12.0F, 14.1F)
                quadToRelative(-0.85F, 0.0F, -1.475F, -0.625F)
                quadTo(9.9F, 12.85F, 9.9F, 12.0F)
                quadToRelative(0.0F, -0.875F, 0.625F, -1.488F)
                quadTo(11.15F, 9.9F, 12.0F, 9.9F)
                quadToRelative(0.875F, 0.0F, 1.488F, 0.612F)
                quadToRelative(0.612F, 0.613F, 0.612F, 1.488F)
                quadToRelative(0.0F, 0.85F, -0.612F, 1.475F)
                quadToRelative(-0.613F, 0.625F, -1.488F, 0.625F)

                moveTo(12.0F, 7.9F)
                quadToRelative(-0.85F, 0.0F, -1.475F, -0.613F)
                quadTo(9.9F, 6.675F, 9.9F, 5.825F)
                quadToRelative(0.0F, -0.875F, 0.625F, -1.488F)
                quadToRelative(0.625F, -0.612F, 1.475F, -0.612F)
                quadToRelative(0.875F, 0.0F, 1.488F, 0.612F)
                quadToRelative(0.612F, 0.613F, 0.612F, 1.488F)
                quadToRelative(0.0F, 0.85F, -0.612F, 1.462F)
                quadTo(12.875F, 7.9F, 12.0F, 7.9F)
                close()
            }
        }
        return _moreVertical!!
    }

private var _moreVertical: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconMoreVerticalPreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.MoreVertical, contentDescription = null)
    }
}
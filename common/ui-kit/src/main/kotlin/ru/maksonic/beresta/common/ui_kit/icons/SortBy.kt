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
val AppIcon.SortBy: ImageVector
    get() {
        if (_sortBy != null) {
            return _sortBy!!
        }
        _sortBy = materialIcon(name = "SortBy") {
            materialPath {
                moveTo(8.0F, 18.225F)
                lineTo(4.0F, 18.225F)
                quadToRelative(-0.475F, 0.0F, -0.812F, -0.325F)
                quadToRelative(-0.338F, -0.325F, -0.338F, -0.8F)
                quadToRelative(0.0F, -0.5F, 0.338F, -0.825F)
                quadToRelative(0.337F, -0.325F, 0.812F, -0.325F)
                horizontalLineToRelative(4.0F)
                quadToRelative(0.475F, 0.0F, 0.812F, 0.325F)
                quadToRelative(0.338F, 0.325F, 0.338F, 0.825F)
                quadToRelative(0.0F, 0.475F, -0.338F, 0.8F)
                quadToRelative(-0.337F, 0.325F, -0.812F, 0.325F)

                moveTo(20.0F, 8.05F)
                lineTo(4.0F, 8.05F)
                quadToRelative(-0.475F, 0.0F, -0.812F, -0.325F)
                quadTo(2.85F, 7.4F, 2.85F, 6.9F)
                quadToRelative(0.0F, -0.475F, 0.338F, -0.8F)
                quadToRelative(0.337F, -0.325F, 0.812F, -0.325F)
                horizontalLineToRelative(16.0F)
                quadToRelative(0.475F, 0.0F, 0.812F, 0.325F)
                quadToRelative(0.338F, 0.325F, 0.338F, 0.8F)
                quadToRelative(0.0F, 0.5F, -0.338F, 0.825F)
                quadToRelative(-0.337F, 0.325F, -0.812F, 0.325F)

                moveTo(14.0F, 13.15F)
                lineTo(4.0F, 13.15F)
                quadToRelative(-0.475F, 0.0F, -0.812F, -0.338F)
                quadToRelative(-0.338F, -0.337F, -0.338F, -0.812F)
                quadToRelative(0.0F, -0.475F, 0.338F, -0.812F)
                quadToRelative(0.337F, -0.338F, 0.812F, -0.338F)
                horizontalLineToRelative(10.0F)
                quadToRelative(0.475F, 0.0F, 0.812F, 0.338F)
                quadToRelative(0.338F, 0.337F, 0.338F, 0.812F)
                quadToRelative(0.0F, 0.475F, -0.338F, 0.812F)
                quadToRelative(-0.337F, 0.338F, -0.812F, 0.338F)
                close()
            }
        }
        return _sortBy!!
    }

private var _sortBy: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconSortByPreview() {
    Image(imageVector = AppIcon.SortBy, contentDescription = null)
}
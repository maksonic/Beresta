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
val AppIcon.Search: ImageVector
    get() {
        if (_search != null) {
            return _search!!
        }
        _search = materialIcon(name = "Search") {
            materialPath {
                moveToRelative(18.8F, 20.425F)
                lineToRelative(-5.55F, -5.55F)
                quadToRelative(-0.75F, 0.575F, -1.725F, 0.925F)
                quadToRelative(-0.975F, 0.35F, -2.075F, 0.35F)
                quadToRelative(-2.775F, 0.0F, -4.7F, -1.938F)
                quadTo(2.825F, 12.275F, 2.825F, 9.5F)
                quadToRelative(0.0F, -2.775F, 1.925F, -4.713F)
                quadTo(6.675F, 2.85F, 9.45F, 2.85F)
                quadToRelative(2.775F, 0.0F, 4.713F, 1.937F)
                quadTo(16.1F, 6.725F, 16.1F, 9.5F)
                quadToRelative(0.0F, 1.1F, -0.337F, 2.075F)
                quadToRelative(-0.338F, 0.975F, -0.913F, 1.7F)
                lineToRelative(5.575F, 5.6F)
                quadToRelative(0.3F, 0.3F, 0.3F, 0.763F)
                quadToRelative(0.0F, 0.462F, -0.325F, 0.787F)
                reflectiveQuadToRelative(-0.812F, 0.325F)
                quadToRelative(-0.488F, 0.0F, -0.788F, -0.325F)

                moveTo(9.45F, 13.85F)
                quadToRelative(1.825F, 0.0F, 3.1F, -1.263F)
                quadToRelative(1.275F, -1.262F, 1.275F, -3.087F)
                quadToRelative(0.0F, -1.825F, -1.275F, -3.088F)
                quadToRelative(-1.275F, -1.262F, -3.1F, -1.262F)
                quadToRelative(-1.825F, 0.0F, -3.087F, 1.262F)
                quadTo(5.1F, 7.675F, 5.1F, 9.5F)
                quadToRelative(0.0F, 1.825F, 1.263F, 3.087F)
                quadTo(7.625F, 13.85F, 9.45F, 13.85F)
                close()
            }
        }
        return _search!!
    }

private var _search: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconSearchPreview() {
    Image(imageVector = AppIcon.Search, contentDescription = null)
}
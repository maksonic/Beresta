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
val AppIcon.GridView: ImageVector
    get() {
        if (_gridView != null) {
            return _gridView!!
        }
        _gridView = materialIcon(name = "GridView") {
            materialPath {
                moveTo(5.075F, 11.0F)
                quadToRelative(-0.95F, 0.0F, -1.612F, -0.663F)
                quadTo(2.8F, 9.675F, 2.8F, 8.725F)
                verticalLineToRelative(-3.65F)
                quadToRelative(0.0F, -0.95F, 0.663F, -1.613F)
                quadToRelative(0.662F, -0.662F, 1.612F, -0.662F)
                horizontalLineToRelative(3.65F)
                quadToRelative(0.95F, 0.0F, 1.613F, 0.662F)
                quadToRelative(0.662F, 0.663F, 0.662F, 1.613F)
                verticalLineToRelative(3.65F)
                quadToRelative(0.0F, 0.95F, -0.662F, 1.612F)
                quadTo(9.675F, 11.0F, 8.725F, 11.0F)

                moveTo(5.075F, 21.2F)
                quadToRelative(-0.95F, 0.0F, -1.612F, -0.662F)
                quadToRelative(-0.663F, -0.663F, -0.663F, -1.613F)
                verticalLineToRelative(-3.65F)
                quadToRelative(0.0F, -0.95F, 0.663F, -1.613F)
                quadTo(4.125F, 13.0F, 5.075F, 13.0F)
                horizontalLineToRelative(3.65F)
                quadToRelative(0.95F, 0.0F, 1.613F, 0.662F)
                quadToRelative(0.662F, 0.663F, 0.662F, 1.613F)
                verticalLineToRelative(3.65F)
                quadToRelative(0.0F, 0.95F, -0.662F, 1.613F)
                quadToRelative(-0.663F, 0.662F, -1.613F, 0.662F)

                moveTo(15.275F, 11.0F)
                quadToRelative(-0.95F, 0.0F, -1.612F, -0.663F)
                quadTo(13.0F, 9.675F, 13.0F, 8.725F)
                verticalLineToRelative(-3.65F)
                quadToRelative(0.0F, -0.95F, 0.663F, -1.613F)
                quadToRelative(0.662F, -0.662F, 1.612F, -0.662F)
                horizontalLineToRelative(3.65F)
                quadToRelative(0.95F, 0.0F, 1.613F, 0.662F)
                quadToRelative(0.662F, 0.663F, 0.662F, 1.613F)
                verticalLineToRelative(3.65F)
                quadToRelative(0.0F, 0.95F, -0.662F, 1.612F)
                quadToRelative(-0.663F, 0.663F, -1.613F, 0.663F)

                moveTo(15.275F, 21.2F)
                quadToRelative(-0.95F, 0.0F, -1.612F, -0.662F)
                quadTo(13.0F, 19.875F, 13.0F, 18.925F)
                verticalLineToRelative(-3.65F)
                quadToRelative(0.0F, -0.95F, 0.663F, -1.613F)
                quadToRelative(0.662F, -0.662F, 1.612F, -0.662F)
                horizontalLineToRelative(3.65F)
                quadToRelative(0.95F, 0.0F, 1.613F, 0.662F)
                quadToRelative(0.662F, 0.663F, 0.662F, 1.613F)
                verticalLineToRelative(3.65F)
                quadToRelative(0.0F, 0.95F, -0.662F, 1.613F)
                quadToRelative(-0.663F, 0.662F, -1.613F, 0.662F)

                moveTo(5.075F, 8.725F)
                horizontalLineToRelative(3.65F)
                verticalLineToRelative(-3.65F)
                horizontalLineToRelative(-3.65F)

                moveTo(15.275F, 8.725F)
                horizontalLineToRelative(3.65F)
                verticalLineToRelative(-3.65F)
                horizontalLineToRelative(-3.65F)

                moveTo(15.275F, 18.925F)
                horizontalLineToRelative(3.65F)
                verticalLineToRelative(-3.65F)
                horizontalLineToRelative(-3.65F)

                moveTo(5.075F, 18.925F)
                horizontalLineToRelative(3.65F)
                verticalLineToRelative(-3.65F)
                horizontalLineToRelative(-3.65F)

                moveTo(15.275F, 8.725F)

                moveTo(15.275F, 15.275F)

                moveTo(8.725F, 15.275F)

                moveTo(8.725F, 8.725F)
                close()
            }
        }
        return _gridView!!
    }

private var _gridView: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconGridViewPreview() {
    Image(imageVector = AppIcon.GridView, contentDescription = null)
}
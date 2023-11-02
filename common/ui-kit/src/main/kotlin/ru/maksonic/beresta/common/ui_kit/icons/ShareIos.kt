package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.common.ui_theme.BerestaTheme

/**
 * @Author maksonic on 26.04.2023
 */
val AppIcon.ShareIos: ImageVector
    get() {
        if (_shareIos != null) {
            return _shareIos!!
        }
        _shareIos = materialIcon(name = "ShareIos") {
            materialPath {
                moveTo(12.0F, 15.444F)
                curveTo(11.724F, 15.444F, 11.492F, 15.349F, 11.302F, 15.158F)
                curveTo(11.113F, 14.967F, 11.018F, 14.733F, 11.018F, 14.456F)
                verticalLineTo(5.851F)
                lineTo(10.318F, 6.555F)
                curveTo(10.136F, 6.738F, 9.911F, 6.829F, 9.642F, 6.827F)
                curveTo(9.374F, 6.826F, 9.143F, 6.727F, 8.95F, 6.533F)
                curveTo(8.767F, 6.339F, 8.676F, 6.105F, 8.676F, 5.832F)
                curveTo(8.676F, 5.558F, 8.767F, 5.33F, 8.95F, 5.146F)
                lineTo(11.313F, 2.77F)
                curveTo(11.399F, 2.684F, 11.502F, 2.617F, 11.624F, 2.57F)
                curveTo(11.746F, 2.523F, 11.871F, 2.5F, 12.0F, 2.5F)
                curveTo(12.129F, 2.5F, 12.254F, 2.523F, 12.376F, 2.57F)
                curveTo(12.498F, 2.617F, 12.601F, 2.684F, 12.687F, 2.77F)
                lineTo(15.056F, 5.152F)
                curveTo(15.252F, 5.349F, 15.349F, 5.586F, 15.346F, 5.861F)
                curveTo(15.342F, 6.137F, 15.244F, 6.366F, 15.05F, 6.55F)
                curveTo(14.857F, 6.733F, 14.625F, 6.825F, 14.355F, 6.825F)
                curveTo(14.085F, 6.825F, 13.859F, 6.733F, 13.677F, 6.55F)
                lineTo(12.982F, 5.851F)
                verticalLineTo(14.456F)
                curveTo(12.982F, 14.733F, 12.887F, 14.967F, 12.698F, 15.158F)
                curveTo(12.508F, 15.349F, 12.276F, 15.444F, 12.0F, 15.444F)

                moveTo(6.964F, 21.5F)
                curveTo(6.421F, 21.5F, 5.957F, 21.308F, 5.574F, 20.923F)
                curveTo(5.191F, 20.538F, 5.0F, 20.072F, 5.0F, 19.525F)
                verticalLineTo(10.422F)
                curveTo(5.0F, 9.875F, 5.191F, 9.409F, 5.574F, 9.024F)
                curveTo(5.957F, 8.639F, 6.421F, 8.447F, 6.964F, 8.447F)
                horizontalLineTo(8.371F)
                curveTo(8.647F, 8.447F, 8.869F, 8.542F, 9.038F, 8.733F)
                curveTo(9.207F, 8.923F, 9.291F, 9.157F, 9.291F, 9.434F)
                curveTo(9.291F, 9.711F, 9.207F, 9.945F, 9.038F, 10.136F)
                curveTo(8.869F, 10.326F, 8.647F, 10.422F, 8.371F, 10.422F)
                horizontalLineTo(6.964F)
                verticalLineTo(19.525F)
                horizontalLineTo(17.036F)
                verticalLineTo(10.422F)
                horizontalLineTo(15.629F)
                curveTo(15.353F, 10.422F, 15.131F, 10.326F, 14.962F, 10.136F)
                curveTo(14.793F, 9.945F, 14.709F, 9.711F, 14.709F, 9.434F)
                curveTo(14.709F, 9.157F, 14.793F, 8.923F, 14.962F, 8.733F)
                curveTo(15.131F, 8.542F, 15.353F, 8.447F, 15.629F, 8.447F)
                horizontalLineTo(17.036F)
                curveTo(17.58F, 8.447F, 18.043F, 8.639F, 18.426F, 9.024F)
                curveTo(18.809F, 9.409F, 19.0F, 9.875F, 19.0F, 10.422F)
                verticalLineTo(19.525F)
                curveTo(19.0F, 20.072F, 18.809F, 20.538F, 18.426F, 20.923F)
                curveTo(18.043F, 21.308F, 17.58F, 21.5F, 17.036F, 21.5F)
                horizontalLineTo(6.964F)
                close()
            }
        }
        return _shareIos!!
    }

private var _shareIos: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconShareIosPreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.ShareIos, contentDescription = null)
    }
}
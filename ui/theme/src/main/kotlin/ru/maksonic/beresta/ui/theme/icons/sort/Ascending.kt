package ru.maksonic.beresta.ui.theme.icons.sort

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.ui.theme.icons.AppIcon

/**
 * @Author maksonic on 15.09.2023
 */
val AppIcon.Ascending: ImageVector
    get() {
        if (_ascending != null) {
            return _ascending!!
        }
        _ascending = materialIcon(name = "Ascending") {
            materialPath {
                moveTo(17.863F, 4.113F)
                curveTo(17.531F, 4.113F, 17.199F, 4.24F, 16.945F, 4.494F)
                lineTo(13.877F, 7.563F)
                curveTo(13.369F, 8.07F, 13.369F, 8.893F, 13.877F, 9.4F)
                curveTo(14.385F, 9.908F, 15.207F, 9.908F, 15.715F, 9.4F)
                lineTo(16.699F, 8.416F)
                lineTo(16.699F, 14.0F)
                curveTo(16.699F, 14.718F, 17.282F, 15.301F, 18.0F, 15.301F)
                curveTo(18.718F, 15.301F, 19.301F, 14.718F, 19.301F, 14.0F)
                lineTo(19.301F, 8.639F)
                lineTo(20.063F, 9.4F)
                curveTo(20.57F, 9.908F, 21.393F, 9.908F, 21.9F, 9.4F)
                curveTo(22.408F, 8.893F, 22.408F, 8.07F, 21.9F, 7.563F)
                lineTo(18.834F, 4.494F)
                curveTo(18.574F, 4.234F, 18.23F, 4.109F, 17.889F, 4.115F)
                curveTo(17.88F, 4.115F, 17.872F, 4.113F, 17.863F, 4.113F)

                moveTo(3.0F, 6.199F)
                curveTo(2.282F, 6.199F, 1.699F, 6.782F, 1.699F, 7.5F)
                curveTo(1.699F, 8.218F, 2.282F, 8.801F, 3.0F, 8.801F)
                lineTo(10.0F, 8.801F)
                curveTo(10.718F, 8.801F, 11.301F, 8.218F, 11.301F, 7.5F)
                curveTo(11.301F, 6.782F, 10.718F, 6.199F, 10.0F, 6.199F)
                lineTo(3.0F, 6.199F)

                moveTo(3.0F, 11.699F)
                curveTo(2.282F, 11.699F, 1.699F, 12.282F, 1.699F, 13.0F)
                curveTo(1.699F, 13.718F, 2.282F, 14.301F, 3.0F, 14.301F)
                lineTo(12.5F, 14.301F)
                curveTo(13.218F, 14.301F, 13.801F, 13.718F, 13.801F, 13.0F)
                curveTo(13.801F, 12.282F, 13.218F, 11.699F, 12.5F, 11.699F)
                lineTo(3.0F, 11.699F)

                moveTo(3.0F, 17.199F)
                curveTo(2.282F, 17.199F, 1.699F, 17.782F, 1.699F, 18.5F)
                curveTo(1.699F, 19.218F, 2.282F, 19.801F, 3.0F, 19.801F)
                lineTo(15.5F, 19.801F)
                curveTo(16.218F, 19.801F, 16.801F, 19.218F, 16.801F, 18.5F)
                curveTo(16.801F, 17.782F, 16.218F, 17.199F, 15.5F, 17.199F)
                lineTo(3.0F, 17.199F)
                close()
            }
        }
        return _ascending!!
    }

private var _ascending: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconAscendingPreview() {
    Image(imageVector = AppIcon.Ascending, contentDescription = null)
}
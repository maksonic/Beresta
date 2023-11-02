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
val AppIcon.FavoriteFill: ImageVector
    get() {
        if (_favoriteFill != null) {
            return _favoriteFill!!
        }
        _favoriteFill = materialIcon(name = "FavoriteFill") {
            materialPath {
                moveTo(11.5F, 18.816F)
                lineTo(6.539F, 21.801F)
                curveTo(6.301F, 21.958F, 6.044F, 22.022F, 5.767F, 21.993F)
                curveTo(5.489F, 21.963F, 5.252F, 21.869F, 5.054F, 21.712F)
                curveTo(4.856F, 21.554F, 4.702F, 21.357F, 4.594F, 21.121F)
                curveTo(4.484F, 20.884F, 4.459F, 20.628F, 4.519F, 20.352F)
                lineTo(5.826F, 14.737F)
                lineTo(1.459F, 10.955F)
                curveTo(1.221F, 10.757F, 1.078F, 10.531F, 1.029F, 10.275F)
                curveTo(0.979F, 10.019F, 0.994F, 9.763F, 1.073F, 9.506F)
                curveTo(1.152F, 9.27F, 1.286F, 9.073F, 1.473F, 8.915F)
                curveTo(1.662F, 8.758F, 1.915F, 8.659F, 2.231F, 8.62F)
                lineTo(7.995F, 8.117F)
                lineTo(10.252F, 2.827F)
                curveTo(10.371F, 2.552F, 10.545F, 2.345F, 10.773F, 2.207F)
                curveTo(11.0F, 2.069F, 11.243F, 2.0F, 11.5F, 2.0F)
                curveTo(11.757F, 2.0F, 12.0F, 2.069F, 12.228F, 2.207F)
                curveTo(12.456F, 2.345F, 12.629F, 2.552F, 12.748F, 2.827F)
                lineTo(15.005F, 8.117F)
                lineTo(20.768F, 8.62F)
                curveTo(21.085F, 8.659F, 21.338F, 8.758F, 21.527F, 8.915F)
                curveTo(21.714F, 9.073F, 21.848F, 9.27F, 21.927F, 9.506F)
                curveTo(22.006F, 9.763F, 22.021F, 10.019F, 21.971F, 10.275F)
                curveTo(21.922F, 10.531F, 21.779F, 10.757F, 21.541F, 10.955F)
                lineTo(17.174F, 14.737F)
                lineTo(18.481F, 20.352F)
                curveTo(18.541F, 20.628F, 18.516F, 20.884F, 18.406F, 21.121F)
                curveTo(18.298F, 21.357F, 18.144F, 21.554F, 17.946F, 21.712F)
                curveTo(17.748F, 21.869F, 17.511F, 21.963F, 17.233F, 21.993F)
                curveTo(16.956F, 22.022F, 16.699F, 21.958F, 16.461F, 21.801F)
                lineTo(11.5F, 18.816F)
                close()
            }
        }
        return _favoriteFill!!
    }

private var _favoriteFill: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconFavoriteFillPreview() {
    Image(imageVector = AppIcon.FavoriteFill, contentDescription = null)
}
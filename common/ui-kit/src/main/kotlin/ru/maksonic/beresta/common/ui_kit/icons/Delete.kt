package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.common.ui_theme.BerestaTheme

/**
 * @Author maksonic on 31.01.2023
 */
val AppIcon.Delete: ImageVector
    get() {
        if (_delete != null) {
            return _delete!!
        }
        _delete = materialIcon(name = "Delete") {
            materialPath {
                moveTo(10.25F, 2.5F)
                curveTo(9.56F, 2.5F, 9.0F, 3.06F, 9.0F, 3.75F)
                curveTo(9.0F, 3.784F, 9.001F, 3.817F, 9.004F, 3.85F)
                lineTo(5.0F, 3.85F)
                curveTo(4.365F, 3.85F, 3.85F, 4.365F, 3.85F, 5.0F)
                curveTo(3.85F, 5.584F, 4.286F, 6.065F, 4.85F, 6.139F)
                lineTo(4.85F, 15.5F)
                lineTo(4.85F, 15.527F)
                curveTo(4.849F, 16.035F, 4.85F, 17.332F, 5.299F, 18.529F)
                curveTo(5.533F, 19.154F, 5.913F, 19.818F, 6.539F, 20.328F)
                curveTo(7.183F, 20.853F, 8.009F, 21.15F, 9.0F, 21.15F)
                lineTo(12.0F, 21.15F)
                lineTo(15.0F, 21.15F)
                curveTo(15.991F, 21.15F, 16.817F, 20.853F, 17.461F, 20.328F)
                curveTo(18.087F, 19.818F, 18.467F, 19.154F, 18.701F, 18.529F)
                curveTo(19.15F, 17.332F, 19.151F, 16.035F, 19.15F, 15.527F)
                lineTo(19.15F, 15.5F)
                lineTo(19.15F, 6.139F)
                curveTo(19.714F, 6.065F, 20.15F, 5.584F, 20.15F, 5.0F)
                curveTo(20.15F, 4.365F, 19.635F, 3.85F, 19.0F, 3.85F)
                lineTo(14.996F, 3.85F)
                curveTo(14.999F, 3.817F, 15.0F, 3.784F, 15.0F, 3.75F)
                curveTo(15.0F, 3.06F, 14.44F, 2.5F, 13.75F, 2.5F)
                lineTo(10.25F, 2.5F)

                moveTo(7.15F, 6.15F)
                lineTo(16.85F, 6.15F)
                lineTo(16.85F, 15.5F)
                curveTo(16.85F, 16.013F, 16.841F, 16.941F, 16.549F, 17.721F)
                curveTo(16.408F, 18.096F, 16.225F, 18.37F, 16.008F, 18.547F)
                curveTo(15.808F, 18.709F, 15.509F, 18.85F, 15.0F, 18.85F)
                lineTo(12.0F, 18.85F)
                lineTo(9.0F, 18.85F)
                curveTo(8.491F, 18.85F, 8.192F, 18.709F, 7.992F, 18.547F)
                curveTo(7.775F, 18.37F, 7.592F, 18.096F, 7.451F, 17.721F)
                curveTo(7.159F, 16.941F, 7.15F, 16.013F, 7.15F, 15.5F)
                lineTo(7.15F, 6.15F)

                moveTo(10.383F, 9.477F)
                curveTo(10.089F, 9.477F, 9.793F, 9.588F, 9.568F, 9.813F)
                curveTo(9.119F, 10.262F, 9.119F, 10.99F, 9.568F, 11.439F)
                lineTo(10.523F, 12.395F)
                lineTo(9.568F, 13.35F)
                curveTo(9.119F, 13.799F, 9.119F, 14.525F, 9.568F, 14.975F)
                curveTo(9.821F, 15.227F, 10.162F, 15.338F, 10.492F, 15.307F)
                curveTo(10.749F, 15.282F, 10.999F, 15.171F, 11.195F, 14.975F)
                lineTo(12.15F, 14.02F)
                lineTo(13.105F, 14.975F)
                curveTo(13.555F, 15.424F, 14.281F, 15.424F, 14.73F, 14.975F)
                curveTo(15.18F, 14.525F, 15.18F, 13.799F, 14.73F, 13.35F)
                lineTo(13.775F, 12.395F)
                lineTo(14.73F, 11.439F)
                curveTo(15.18F, 10.99F, 15.18F, 10.262F, 14.73F, 9.813F)
                curveTo(14.478F, 9.56F, 14.137F, 9.451F, 13.807F, 9.482F)
                curveTo(13.55F, 9.507F, 13.302F, 9.616F, 13.105F, 9.813F)
                lineTo(12.15F, 10.768F)
                lineTo(11.195F, 9.813F)
                curveTo(10.971F, 9.588F, 10.677F, 9.477F, 10.383F, 9.477F)
                close()
            }
        }
        return _delete!!
    }

private var _delete: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconRemoveForeverPreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.Delete, contentDescription = null)
    }
}
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
val AppIcon.VoiceEnter: ImageVector
    get() {
        if (_voiceEnter != null) {
            return _voiceEnter!!
        }
        _voiceEnter = materialIcon(name = "VoiceEnter") {
            materialPath {
                moveTo(12.0F, 14.275F)
                quadTo(10.65F, 14.275F, 9.713F, 13.337F)
                quadTo(8.775F, 12.4F, 8.775F, 11.05F)
                verticalLineTo(5.1F)
                quadTo(8.775F, 3.75F, 9.713F, 2.8F)
                quadTo(10.65F, 1.85F, 12.0F, 1.85F)
                quadTo(13.35F, 1.85F, 14.288F, 2.8F)
                quadTo(15.225F, 3.75F, 15.225F, 5.1F)
                verticalLineTo(11.05F)
                quadTo(15.225F, 12.4F, 14.288F, 13.337F)
                quadTo(13.35F, 14.275F, 12.0F, 14.275F)

                moveTo(12.0F, 8.075F)
                quadTo(12.0F, 8.075F, 12.0F, 8.075F)
                quadTo(12.0F, 8.075F, 12.0F, 8.075F)
                quadTo(12.0F, 8.075F, 12.0F, 8.075F)
                quadTo(12.0F, 8.075F, 12.0F, 8.075F)
                quadTo(12.0F, 8.075F, 12.0F, 8.075F)
                quadTo(12.0F, 8.075F, 12.0F, 8.075F)
                quadTo(12.0F, 8.075F, 12.0F, 8.075F)
                quadTo(12.0F, 8.075F, 12.0F, 8.075F)

                moveTo(12.0F, 21.375F)
                quadTo(11.525F, 21.375F, 11.213F, 21.062F)
                quadTo(10.9F, 20.75F, 10.9F, 20.275F)
                verticalLineTo(18.275F)
                quadTo(8.5F, 17.925F, 6.825F, 16.262F)
                quadTo(5.15F, 14.6F, 4.775F, 12.25F)
                quadTo(4.7F, 11.775F, 5.025F, 11.412F)
                quadTo(5.35F, 11.05F, 5.9F, 11.05F)
                quadTo(6.275F, 11.05F, 6.575F, 11.325F)
                quadTo(6.875F, 11.6F, 6.95F, 12.0F)
                quadTo(7.3F, 13.8F, 8.7F, 14.988F)
                quadTo(10.1F, 16.175F, 12.0F, 16.175F)
                quadTo(13.9F, 16.175F, 15.3F, 14.988F)
                quadTo(16.7F, 13.8F, 17.05F, 12.0F)
                quadTo(17.125F, 11.6F, 17.425F, 11.325F)
                quadTo(17.725F, 11.05F, 18.125F, 11.05F)
                quadTo(18.65F, 11.05F, 18.975F, 11.412F)
                quadTo(19.3F, 11.775F, 19.225F, 12.25F)
                quadTo(18.85F, 14.6F, 17.175F, 16.262F)
                quadTo(15.5F, 17.925F, 13.1F, 18.275F)
                verticalLineTo(20.275F)
                quadTo(13.1F, 20.75F, 12.788F, 21.062F)
                quadTo(12.475F, 21.375F, 12.0F, 21.375F)

                moveTo(12.0F, 12.0F)
                quadTo(12.4F, 12.0F, 12.675F, 11.725F)
                quadTo(12.95F, 11.45F, 12.95F, 11.05F)
                verticalLineTo(5.1F)
                quadTo(12.95F, 4.675F, 12.675F, 4.412F)
                quadTo(12.4F, 4.15F, 12.0F, 4.15F)
                quadTo(11.6F, 4.15F, 11.325F, 4.412F)
                quadTo(11.05F, 4.675F, 11.05F, 5.1F)
                verticalLineTo(11.05F)
                quadTo(11.05F, 11.45F, 11.325F, 11.725F)
                quadTo(11.6F, 12.0F, 12.0F, 12.0F)
                close()
            }
        }
        return _voiceEnter!!
    }

private var _voiceEnter: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconVoiceEnterPreview() {
    Image(imageVector = AppIcon.VoiceEnter, contentDescription = null)
}
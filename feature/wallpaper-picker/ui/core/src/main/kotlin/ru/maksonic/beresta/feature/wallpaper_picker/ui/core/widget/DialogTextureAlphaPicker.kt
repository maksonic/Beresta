package ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.noRippleClick
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.scrim
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperTexture
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.TextureAlphaKey

/**
 * @Author maksonic on 21.11.2023
 */
@Composable
internal fun DialogTextureAlphaPicker(
    isVisible: Boolean,
    hideDialog: () -> Unit,
    key: TextureAlphaKey,
    texture: WallpaperTexture<Color>,
    update: (WallpaperTexture<Color>) -> Unit,
    modifier: Modifier = Modifier
) {
    AnimateFadeInOut(isVisible) {

        BackHandler(isVisible) {
            hideDialog()
        }

        Box(
            modifier
                .fillMaxSize()
                .background(scrim)
                .noRippleClick { hideDialog() },
            contentAlignment = Alignment.Center
        ) {
            val contentModifier = if (key == TextureAlphaKey.TINT)
                Modifier.padding(bottom = Theme.size.bottomMainBarHeight)
            else Modifier

            Column(
                contentModifier
                    .clip(CircleShape)
                    .background(secondaryContainer),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (key) {
                    TextureAlphaKey.BACKGROUND -> {
                        SliderAlphaPicker(
                            color = texture.backgroundColor.value,
                            initialAlpha = 0.3f,
                            currentAlpha = texture.backgroundColorAlpha,
                            updateAlpha = { update(texture.copy(backgroundColorAlpha = it)) },
                            valueRange = 0.3f..1f
                        )
                    }

                    TextureAlphaKey.TINT -> {
                        SliderAlphaPicker(
                            color = texture.tintColor.value,
                            initialAlpha = 0f,
                            currentAlpha = texture.tintColorAlpha,
                            updateAlpha = { update(texture.copy(tintColorAlpha = it)) },
                            valueRange = 0f..1f,
                        )
                    }

                    TextureAlphaKey.NOTHING -> {}
                }
            }
        }
    }
}

package ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.ArrowBack
import ru.maksonic.beresta.common.ui_kit.icons.Close
import ru.maksonic.beresta.common.ui_kit.icons.Colors
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.provide.dp6
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.TextureAlphaKey
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.WallpaperPickerContent
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 18.09.2023
 */
@Composable
internal fun TopBar(
    currentContent: State<WallpaperPickerContent>,
    isTexturePage: Boolean,
    textureAlphaKey: TextureAlphaKey,
    onCloseClicked: () -> Unit,
    onSetTextureBackgroundClicked: () -> Unit,
    onInitialContentClicked: () -> Unit,
    onCloseAlphaPickerDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(contentAlignment = Alignment.Center) {
        val title = when (currentContent.value) {
            WallpaperPickerContent.IDLE -> text.editor.dialogTitleWallpaperPicker

            WallpaperPickerContent.TEXTURE_SETTINGS -> {
                with(text.editor) {
                    when (textureAlphaKey) {
                        TextureAlphaKey.BACKGROUND -> dialogTitleWallpaperTextureAlphaBackgroundPicker
                        TextureAlphaKey.TINT -> dialogTitleWallpaperTextureAlphaTintPicker
                        TextureAlphaKey.NOTHING -> dialogTitleWallpaperTextureStylePicker
                    }
                }
            }
        }

        Row(
            modifier
                .fillMaxWidth()
                .height(Theme.size.topBarSmallHeight)
                .padding(start = dp6, end = dp6),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            AnimateFadeInOut(isTexturePage) {
                Crossfade(
                    currentContent.value == WallpaperPickerContent.TEXTURE_SETTINGS,
                    label = ""
                ) {
                    ButtonIcon(
                        icon = if (it) AppIcon.ArrowBack else AppIcon.Colors,
                        onClick = {
                            if (it) if (textureAlphaKey != TextureAlphaKey.NOTHING) {
                                onCloseAlphaPickerDialog()
                            } else {
                                onInitialContentClicked()
                            }
                            else {
                                onSetTextureBackgroundClicked()
                            }
                        }
                    )
                }
            }

            Spacer(modifier.weight(1f))

            ButtonIcon(icon = AppIcon.Close, onClick = onCloseClicked)
        }

        Text(text = title, style = TextDesign.headlineSmall)
    }
}
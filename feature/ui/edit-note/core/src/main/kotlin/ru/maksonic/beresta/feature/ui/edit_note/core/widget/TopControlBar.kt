package ru.maksonic.beresta.feature.ui.edit_note.core.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemStatusBarHeight
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.label.LabelOutlined
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.inverseOnSurface
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.surface
import ru.maksonic.beresta.common.ui_theme.provide.dp12
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.feature.marker_color_picker.ui.api.findColor
import ru.maksonic.beresta.feature.ui.edit_note.core.Model
import ru.maksonic.beresta.feature.ui.edit_note.core.Msg
import ru.maksonic.beresta.feature.ui.edit_note.core.editorColors
import ru.maksonic.beresta.feature.ui.edit_note.core.screen.Send
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperGradient
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperImage
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperTexture
import ru.maksonic.beresta.platform.core.ui.ColorSaver

/**
 * @Author maksonic on 08.09.2023
 */
@Composable
internal fun TopControlBar(
    isHiddenNote: Boolean,
    model: Model,
    send: Send,
    backgroundColor: State<Color>,
    modifier: Modifier = Modifier
) {
    val animDelay = Theme.animVelocity.common
    var isAnimated by remember { mutableStateOf(false) }
    val animVelocity = if (isAnimated) animDelay else 0
    val markerColorState = rememberUpdatedState(model.markerState.currentSelectedColorId)
    val initialMarkerColor = editorColors.tint.copy(0.7f)
    val markerColor =
        rememberSaveable(saver = ColorSaver) { mutableStateOf(initialMarkerColor) }

    LaunchedEffect(Unit) {
        if (!isAnimated) {
            delay(animDelay.toLong())
            isAnimated = true
        }
    }

    LaunchedEffect(markerColorState.value) {
        updateMarkerColor(model, markerColor, initialMarkerColor, markerColorState.value)
    }

    LaunchedEffect(model.currentWallpaper) {
        updateMarkerColor(model, markerColor, initialMarkerColor, markerColorState.value)
    }

    Box {
        val background =
            animateColorAsState(backgroundColor.value, tween(animVelocity), label = "")
        Row(
            modifier
                .drawBehind { drawRect(background.value) }
                .fillMaxWidth()
                .padding(
                    top = Theme.size.topBarNormalHeight
                        .plus(SystemStatusBarHeight)
                        .plus(dp8),
                    bottom = dp8
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier
                    .padding(start = dp4)
                    .clip(CircleShape)
                    .rippledClick(rippleColor = primary) { send(Msg.Ui.OnSelectColorMarkerClicked) }
                    .size(Theme.size.minimumTouchTargetSize)
                    .padding(dp12)
                    .border(1.dp, editorColors.tint, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier
                        .fillMaxSize()
                        .padding(dp4)
                        .clip(CircleShape)
                        .background(markerColor.value)
                )
            }
            ButtonIcon(
                icon = AppIcon.LabelOutlined,
                tint = editorColors.tint,
                onClick = {},
            )

            Spacer(modifier.weight(1f))

            if (!isHiddenNote) {
                DropdownFolderPicker(model, send)
            }
        }
    }
}

private fun updateMarkerColor(
    model: Model,
    mutableColor: MutableState<Color>,
    initialMarkerColor: Color,
    currentMarkerId: Long
) {
    val colorContainer = model.markerState.findColor(currentMarkerId)

    if (colorContainer == null || colorContainer.colorId == 0L) {
        mutableColor.value = initialMarkerColor
    } else {
        mutableColor.value = colorContainer.value
    }
}

@Composable
internal fun getWallpaperBackgroundColor(wallpaper: BaseWallpaper<Color>): State<Color> {
    var color by remember { mutableStateOf(Color.Transparent) }

    color = when (wallpaper) {
        is WallpaperColor -> if (wallpaper.id == 100000L) surface else wallpaper.value
        is WallpaperGradient -> wallpaper.value.first()
        is WallpaperTexture -> {
            if (wallpaper.backgroundColor.id == 0L) surface else wallpaper.backgroundColor.value
        }

        is WallpaperImage -> if (wallpaper.isDark) inverseOnSurface else surface
        else -> surface
    }

    return remember { derivedStateOf { color } }
}
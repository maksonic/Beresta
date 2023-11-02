package ru.maksonic.beresta.feature.ui.edit_note.core

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.Palette
import ru.maksonic.beresta.common.ui_theme.colors.inverseOnSurface
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.colors.onSurface
import ru.maksonic.beresta.common.ui_theme.colors.onTertiary
import ru.maksonic.beresta.common.ui_theme.colors.outline

/**
 * @Author maksonic on 01.11.2023
 */
val LocalAppEditorColors = staticCompositionLocalOf<EditorSurroundingColors> {
    error("No editor surrounding colors provided")
}

data class EditorSurroundingColors(
    val isNoneWallpaper: Boolean,
    val tint: Color,
    val tintX: State<Color>,
    val textTint: State<Color>,
    val onBarIconTint: Color,
    val captionTint: Color
)

internal val editorColors: EditorSurroundingColors @Composable get() = LocalAppEditorColors.current

@Composable
internal fun provideEditorColors(
    isDarkAppTheme: Boolean,
    isNoneWallpapers: Boolean
): EditorSurroundingColors {

    val animDelay = Theme.animVelocity.common
    var isAnimated by remember { mutableStateOf(false) }
    val animVelocity = if (isAnimated) animDelay else 0

    LaunchedEffect(isDarkAppTheme) {
        if (!isAnimated) {
            delay(animDelay.toLong())
            isAnimated = true
        }
    }

    return EditorSurroundingColors(
        isNoneWallpaper = isNoneWallpapers,
        tint = if (isNoneWallpapers) onBackground
        else if (isDarkAppTheme) inverseOnSurface else onTertiary,

        tintX = animateColorAsState(
            if (isNoneWallpapers) onBackground
            else if (isDarkAppTheme) inverseOnSurface else onTertiary,
            tween(animVelocity),
            label = ""
        ),
        textTint = animateColorAsState(
            if (isNoneWallpapers) onBackground
            else if (isDarkAppTheme) inverseOnSurface else onTertiary,
            tween(animVelocity), label = ""
        ),
        onBarIconTint = if (isNoneWallpapers) onSurface
        else if (isDarkAppTheme) inverseOnSurface else onTertiary,

        captionTint = if (isNoneWallpapers) outline
        else if (isDarkAppTheme) Palette.chineseSilver else Palette.doveGray,
    )
}

/*@Composable
internal fun provideEditorColors(
    isDarkAppTheme: Boolean,
    isNoneWallpapers: Boolean
): EditorSurroundingColors = EditorSurroundingColors(
    isNoneWallpaper = isNoneWallpapers,
    tint = if (isNoneWallpapers) onBackground
    else if (isDarkAppTheme) inverseOnSurface else onTertiary,

    onBarIconTint = if (isNoneWallpapers) onSurface
    else if (isDarkAppTheme) inverseOnSurface else onTertiary,

    captionTint = if (isNoneWallpapers) outline
    else if (isDarkAppTheme) Palette.chineseSilver else Palette.doveGray,
)*/
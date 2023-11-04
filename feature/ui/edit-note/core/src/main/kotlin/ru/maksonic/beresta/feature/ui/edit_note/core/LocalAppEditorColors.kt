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
    val tint: State<Color>,
    val tintMarkerColor: Color,
    val textTint: State<Color>,
    val onBarIconTint: Color,
    val captionTint: Color
)

internal val editorColors: EditorSurroundingColors @Composable get() = LocalAppEditorColors.current

@Composable
internal fun provideEditorColors(
    isDarkAppTheme: Boolean,
    isNoneWallpaper: Boolean
): EditorSurroundingColors {

    val animDelay = Theme.animVelocity.common
    var isAnimated by remember { mutableStateOf(false) }
    val animVelocity = if (isAnimated) animDelay else 0
    val tintColor = animateColorAsState(
        if (isNoneWallpaper) onBackground
        else if (isDarkAppTheme) inverseOnSurface else onTertiary,
        tween(animVelocity), label = ""
    )

    val tintMarkerColor = if (isNoneWallpaper) onBackground.copy(0.7f)
    else if (isDarkAppTheme) inverseOnSurface.copy(0.7f) else onTertiary.copy(0.7f)

    LaunchedEffect(isDarkAppTheme) {
        if (!isAnimated) {
            delay(animDelay.toLong())
            isAnimated = true
        }
    }

    return EditorSurroundingColors(
        isNoneWallpaper = isNoneWallpaper,
        tint = tintColor,
        textTint = tintColor,
        tintMarkerColor = tintMarkerColor,
        onBarIconTint = if (isNoneWallpaper) onSurface
        else if (isDarkAppTheme) inverseOnSurface else onTertiary,

        captionTint = if (isNoneWallpaper) outline
        else if (isDarkAppTheme) Palette.chineseSilver else Palette.doveGray,
    )
}
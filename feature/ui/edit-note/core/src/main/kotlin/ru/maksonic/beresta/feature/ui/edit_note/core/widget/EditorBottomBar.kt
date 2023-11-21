package ru.maksonic.beresta.feature.ui.edit_note.core.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.movableContentOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_kit.bar.bottom.BottomBarItem
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.common.ui_kit.button.ButtonFloatingIconFab
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.icons.AddImage
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.MakePhoto
import ru.maksonic.beresta.common.ui_kit.icons.Save
import ru.maksonic.beresta.common.ui_kit.icons.VoiceEnter
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onSurface
import ru.maksonic.beresta.common.ui_theme.colors.surface
import ru.maksonic.beresta.common.ui_theme.provide.dp12
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.feature.ui.edit_note.core.ColorByWallpaperCreator
import ru.maksonic.beresta.feature.ui.edit_note.core.Msg
import ru.maksonic.beresta.feature.ui.edit_note.core.editorColors
import ru.maksonic.beresta.feature.ui.edit_note.core.screen.Send

/**
 * @Author maksonic on 24.03.2023
 */
@Composable
internal fun EditorBottomBar(
    send: Send,
    barColorCreatorState: State<ColorByWallpaperCreator>,
    isScrollUp: State<Boolean>,
    isBlankNote: Boolean,
    isHiddenNote: Boolean,
    modifier: Modifier = Modifier,
) {
    val bottomBarColor = barColorCreatorState.value.bottomBarColor()
    val fabColor = barColorCreatorState.value.fabSaveNoteColor(isBlankNote)

    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
        val fabElevation = animateDpAsState(
            if (isScrollUp.value) Theme.tonal.level0 else Theme.tonal.level3,
            animationSpec = tween(Theme.animVelocity.common), label = ""
        )
        val fabIconColor = animateColorAsState(
            if (isBlankNote) Color.White.copy(alpha = 0.7f)
            else Color.White, label = ""
        )
        val fabShape = if (isHiddenNote) 50.dp else dp16
        val barContent = movableContentOf { EditorBottomBarContent(send) }
        val offset = Theme.size.bottomMainBarHeight.plus(SystemNavigationBarHeight)
        val transition = animateDpAsState(
            if (isScrollUp.value) 0.dp else offset,
            tween(Theme.animVelocity.common),
            label = ""
        )
        val barModifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                translationY = transition.value.toPx()
            }

        if (bottomBarColor.value != surface) {
            SurfacePro(color = bottomBarColor.value, modifier = barModifier) {
                barContent()
            }
        } else {
            SurfacePro(tonalElevation = Theme.tonal.level4, modifier = barModifier) {
                barContent()
            }
        }

        ButtonFloatingIconFab(
            icon = AppIcon.Save,
            iconColor = fabIconColor.value,
            onClick = { send(Msg.Ui.OnSaveNoteClicked(isHiddenNote)) },
            enabled = !isBlankNote,
            fabColor = fabColor.value,
            shadowElevation = fabElevation.value,
            shape = RoundedCornerShape(fabShape),
            modifier = Modifier.padding(top = dp12, end = dp16)
        )
    }
}

@Composable
private fun EditorBottomBarContent(
    send: Send,
    modifier: Modifier = Modifier
) {
    val actions = arrayOf(
        BottomBarItem(
            icon = AppIcon.VoiceEnter,
            onClick = { send(Msg.Ui.OnStartRecordVoiceClicked) }),
        BottomBarItem(
            icon = AppIcon.AddImage,
            onClick = { send(Msg.Ui.OnAddImagesClicked) }),
        BottomBarItem(
            icon = AppIcon.MakePhoto,
            onClick = { send(Msg.Ui.OnAddCameraSnapshotClicked) })
    )

    Column {
        val keyboardHeight = WindowInsets.Companion.ime.asPaddingValues().calculateBottomPadding()
        val calculatedBottomPadding = if (keyboardHeight != 0.dp && keyboardHeight > 100.dp) 0.dp
        else SystemNavigationBarHeight

        Row(
            modifier
                .padding(start = dp4, bottom = calculatedBottomPadding)
                .height(Theme.size.bottomMainBarHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            actions.forEach { panelItem ->
                ButtonIcon(
                    icon = panelItem.icon,
                    onClick = panelItem.onClick,
                    tint = if (editorColors.isNoneWallpaper) onSurface else editorColors.tint.value
                )
            }
        }
    }
}


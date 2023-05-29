package ru.maksonic.beresta.feature.edit_note.core.ui.widget

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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.edit_note.core.Msg
import ru.maksonic.beresta.feature.edit_note.core.ui.SendMessage
import ru.maksonic.beresta.feature.notes.list.api.BaseBottomBarItem
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AddImage
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.MakePhoto
import ru.maksonic.beresta.ui.theme.icons.Save
import ru.maksonic.beresta.ui.theme.icons.VoiceEnter
import ru.maksonic.beresta.ui.theme.icons.Wallpaper
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.button.FloatingFabButton
import ru.maksonic.beresta.ui.widget.button.IconAction

/**
 * @Author maksonic on 24.03.2023
 */
@Composable
internal fun EditorBottomBar(
    send: SendMessage,
    isScrollUp: Boolean,
    isBlankNote: Boolean,
    modifier: Modifier = Modifier
) {
    val panelHeight = Theme.widgetSize.bottomMainBarHeight.plus(SystemNavigationBarHeight)
    val panelOffset = animateDpAsState(
        if (isScrollUp) 0.dp else panelHeight,
        animationSpec = tween(Theme.animSpeed.common), label = ""
    )

    Box(
        modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {
        val fabUnselectedColor = tertiaryContainer.copy(alpha = 0.35f).compositeOver(background)
        val fabColor = animateColorAsState(
            if (isBlankNote) fabUnselectedColor else tertiaryContainer, label = ""
        )
        val fabElevation = animateDpAsState(
            if (isScrollUp) Theme.tonal.Level0 else Theme.tonal.Level3,
            animationSpec = tween(Theme.animSpeed.common), label = ""
        )
        val fabIconColor = animateColorAsState(
            if (isBlankNote) onTertiaryContainer.copy(alpha = 0.5f)
                .compositeOver(fabUnselectedColor)
            else onTertiaryContainer, label = ""
        )

        SurfacePro(
            tonalElevation = Theme.tonal.Level4,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    translationY = panelOffset.value.toPx()
                }
        ) {
            EditorBottomBarContent(send)
        }

        FloatingFabButton(
            onClick = { send(Msg.Ui.OnSaveNoteClicked) },
            enabled = !isBlankNote,
            fabColor = fabColor.value,
            shadowElevation = fabElevation.value,
            modifier = Modifier.padding(top = dp12, end = dp16)
        ) {
            Icon(
                imageVector = AppIcon.Save,
                tint = fabIconColor.value,
                contentDescription = ""
            )
        }
    }
}

@Composable
private fun EditorBottomBarContent(
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    val actions = arrayOf(
        BaseBottomBarItem(
            icon = AppIcon.VoiceEnter,
            action = { send(Msg.Ui.OnStartRecordVoiceClicked) }),
        BaseBottomBarItem(
            icon = AppIcon.AddImage,
            action = { send(Msg.Ui.OnAddImagesClicked) }),
        BaseBottomBarItem(
            icon = AppIcon.MakePhoto,
            action = { send(Msg.Ui.OnAddCameraSnapshotClicked) }),
        BaseBottomBarItem(
            icon = AppIcon.Wallpaper,
            action = { send(Msg.Ui.OnSetNoteWallpaperClicked) }),
    )

    Column {
        val keyboardHeight = WindowInsets.Companion.ime.asPaddingValues().calculateBottomPadding()
        val calculatedBottomPadding = if (keyboardHeight != 0.dp && keyboardHeight > 100.dp) 0.dp
        else SystemNavigationBarHeight

        Row(
            modifier
                .padding(start = dp4, bottom = calculatedBottomPadding)
                .height(Theme.widgetSize.bottomMainBarHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            actions.forEach { panelItem ->
                IconAction(
                    icon = { panelItem.icon },
                    action = panelItem.action,
                    tint = onSurface
                )
            }
        }
    }
}


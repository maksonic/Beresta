package ru.maksonic.beresta.screen.settings.appearance.ui.widget.items

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.button.base.ScalableClickBox
import ru.maksonic.beresta.common.ui_kit.dialog.dropdown.DropdownMenuItem
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Colors
import ru.maksonic.beresta.common.ui_kit.icons.CornerRadius
import ru.maksonic.beresta.common.ui_kit.icons.Grading
import ru.maksonic.beresta.common.ui_kit.icons.Shadow
import ru.maksonic.beresta.common.ui_kit.icons.Wallpaper
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.RightPart
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingCategoryContainer
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingClickableItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingDropdownClickableItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingTextTitle
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.inverseSurface
import ru.maksonic.beresta.common.ui_theme.colors.onPrimaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.surfaceVariant
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp24
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.notes_list.ui.api.card.LocalNoteCardState
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardShapeUi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardUiState
import ru.maksonic.beresta.feature.notes_list.ui.api.card.isEnabled
import ru.maksonic.beresta.feature.notes_list.ui.api.card.isSquare
import ru.maksonic.beresta.feature.notes_list.ui.api.card.noteUiCardState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.appearance.core.Msg
import ru.maksonic.beresta.screen.settings.appearance.ui.Send

/**
 * @Author maksonic on 07.07.2023
 */
@Composable
internal fun CardSettingItem(
    send: Send,
    noteCardDate: String,
    noteCardState: State<NoteCardUiState>,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalNoteCardState provides noteCardState.value) {

        SettingCategoryContainer {

            SettingTextTitle(title = text.settingsAppearance.titleNoteCard)

            Card(noteCardDate, modifier)

            SettingDropdownClickableItem(shapeSettingItem(), dropdownMenuItems(send))

            items(send).forEach { item ->
                SettingClickableItem(item)
            }
        }
    }
}

@Composable
private fun Card(noteCardDate: String, modifier: Modifier) {
    val corner = animateDpAsState(
        with(noteUiCardState.shape) { if (isSquare) dp else dp },
        tween(Theme.animVelocity.common), label = ""
    )
    val elevation = animateDpAsState(
        with(noteUiCardState.elevation) { if (isEnabled) dp else dp },
        tween(Theme.animVelocity.common), label = ""
    )

    ScalableClickBox {
        SurfacePro(
            shadowElevation = elevation.value,
            shape = RoundedCornerShape(corner.value),
            modifier = modifier.padding(dp16)
        ) {
            Column(
                modifier
                    .fillMaxWidth()
                    .background(surfaceVariant)
                    .padding(start = dp16, end = dp16, bottom = dp16),
            ) {
                TopPanelIndicators()

                Text(
                    text = text.shared.noteTitlePlaceholder,
                    style = TextDesign.titleMedium.copy(color = onPrimaryContainer),
                    maxLines = noteUiCardState.maxTitleLines,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.animateContentSize(tween(Theme.animVelocity.common))
                )

                Spacer(modifier.size(dp8))

                Text(
                    text = text.shared.noteMessagePlaceholder,
                    style = TextDesign.bodyMedium.copy(color = onPrimaryContainer),
                    maxLines = noteUiCardState.maxMessageLines,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.animateContentSize(tween(Theme.animVelocity.common))
                )

                Spacer(modifier.size(dp8))

                Text(
                    text = noteCardDate,
                    style = TextDesign.labelSmall.copy(color = inverseSurface),
                    maxLines = 1,
                    modifier = Modifier.padding(top = dp8)
                )
            }
        }
    }
}

@Composable
private fun shapeSettingItem(): SettingItem {
    val notesShapeHint = with(text.settingsAppearance) {
        if (noteUiCardState.shape.isSquare) hintNoteCardSquaredShape else hintNoteCardRoundedShape
    }

    return SettingItem(
        text.settingsAppearance.itemNoteCardShape,
        prefixIcon = AppIcon.CornerRadius,
        valueHint = notesShapeHint,
        rightPart = RightPart.CURRENT_VALUE,
    )
}

@Composable
private fun items(send: Send): List<SettingItem> {
    val isEnabledElevation = noteUiCardState.elevation.isEnabled
    val isVisibleColorMarker = noteUiCardState.isVisibleColorMarker
    val isVisibleWallpaper = noteUiCardState.isVisibleWallpaper

    return listOf(
        SettingItem(
            title = text.settingsAppearance.itemNoteCardElevation,
            prefixIcon = AppIcon.Shadow,
            rightPart = RightPart.TOGGLE,
            isEnabledToggle = noteUiCardState.elevation.isEnabled,
            onClick = { send(Msg.Ui.OnCardElevationClicked(isEnabledElevation)) },
        ),
        SettingItem(
            title = text.editor.noteWallpaperCategoryImage,
            prefixIcon = AppIcon.Wallpaper,
            rightPart = RightPart.TOGGLE,
            isEnabledToggle = noteUiCardState.isVisibleWallpaper,
            onClick = { send(Msg.Ui.OnCardWallpaperClicked(isVisibleWallpaper)) },
        ),
        SettingItem(
            title = text.settingsAppearance.itemNoteCardColorMarker,
            rightPart = RightPart.TOGGLE,
            prefixIcon = AppIcon.Colors,
            isEnabledToggle = noteUiCardState.isVisibleColorMarker,
            onClick = { send(Msg.Ui.OnCardColorMarkerVisibilityClicked(isVisibleColorMarker)) }
        ),
        SettingItem(
            title = text.settingsAppearance.itemNoteCardMaxLines,
            prefixIcon = AppIcon.Grading,
            rightPart = RightPart.NOTHING,
            onClick = { send(Msg.Ui.OnNoteLinesCountClicked) },
        )
    )
}

@Composable
private fun dropdownMenuItems(send: Send) = listOf(
    DropdownMenuItem(
        title = text.settingsAppearance.hintNoteCardRoundedShape,
        onClick = { send(Msg.Ui.OnCardShapeClicked(NoteCardShapeUi.ROUNDED)) }
    ),
    DropdownMenuItem(
        title = text.settingsAppearance.hintNoteCardSquaredShape,
        onClick = { send(Msg.Ui.OnCardShapeClicked(NoteCardShapeUi.SQUARED)) }
    )
)

@Composable
private fun TopPanelIndicators(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .height(dp24), contentAlignment = Alignment.Center
    ) {

        AnimateFadeInOut(noteUiCardState.isVisibleColorMarker) {
            val color = tertiaryContainer

            Canvas(
                modifier
                    .fillMaxWidth()
                    .size(dp4)
                    .clip(CircleShape),
                onDraw = { drawRect(color) })
        }
    }
}
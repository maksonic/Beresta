package ru.maksonic.beresta.feature.notes_list.ui.core

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.core.VibrationPerformer
import ru.maksonic.beresta.common.ui_kit.button.base.ScalableClickBox
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.outlineVariant
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.primaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.transparent
import ru.maksonic.beresta.common.ui_theme.provide.dp0
import ru.maksonic.beresta.common.ui_theme.provide.dp12
import ru.maksonic.beresta.common.ui_theme.provide.dp6
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NotesCardUiApi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.noteUiCardState
import ru.maksonic.beresta.feature.notes_list.ui.core.card.content.ContentNoteColorWallpaper
import ru.maksonic.beresta.feature.notes_list.ui.core.card.content.ContentNoteDefault
import ru.maksonic.beresta.feature.notes_list.ui.core.card.content.ContentNoteWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperType
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 15.10.2023
 */
private const val DEF_WALLPAPER_COLOR_ID = 100000L
internal const val MAX_TEXT_LENGTH = 500

class NotesCardUiCore : NotesCardUiApi {

    @Composable
    override fun Card(
        note: NoteUi,
        onNoteClicked: (Long) -> Unit,
        onNoteLongClicked: (Long) -> Unit,
        modifier: Modifier,
        cardBackground: @Composable (wallpaper: BaseWallpaper<Color>) -> Unit
    ) {
        CardContent(
            note = note,
            onNoteClicked = onNoteClicked,
            onNoteLongClicked = onNoteLongClicked,
            modifier = modifier,
            cardBackground = cardBackground
        )
    }
}

@Composable
internal fun CardContent(
    note: NoteUi,
    onNoteClicked: (Long) -> Unit,
    onNoteLongClicked: (Long) -> Unit,
    vibrationPerformer: VibrationPerformer = koinInject(),
    cardBackground: @Composable (wallpaper: BaseWallpaper<Color>) -> Unit,
    modifier: Modifier
) {
    val isPinned = note.style.isPinned && !note.isMovedToTrash
    val isHiddenAndTrashed = note.isHidden && note.isMovedToTrash
    val hintPrefixRemovedDate = text.trash.hintRemovedDatePrefix
    val date = if (note.isMovedToTrash) "$hintPrefixRemovedDate ${note.dateMovedToTrash}"
    else note.dateCreation
    val view = LocalView.current
    val isFocusedItem = rememberSaveable { mutableStateOf(false) }
    val backgroundColor = animateColorAsState(
        if (isFocusedItem.value) outlineVariant else primaryContainer,
        tween(Theme.animVelocity.common), label = ""
    )

    val isVisible = noteUiCardState.isVisibleWallpaper
    val isDefault = note.style.wallpaperId == DEF_WALLPAPER_COLOR_ID
    val isVisibleWallpaper = isVisible && note.style.wallpaperId != 0L && !isDefault
    val isColorWallpaper = note.style.wallpaperTypeId == WallpaperType.Value.COLOR.id && !isDefault

    ScalableClickBox(
        onClick = { onNoteClicked(note.id) },
        onLongClick = {
            onNoteLongClicked(note.id).run { vibrationPerformer.keyboardTapVibration(view) }
        },
        modifier = modifier.onFocusChanged { isFocusedItem.value = it.isFocused }
    ) {
        val borderWidth = animateDpAsState(
            if (note.isSelected) 3.dp else dp0, tween(Theme.animVelocity.common), label = ""
        )

        val borderColor = animateColorAsState(
            if (note.isSelected) primary else transparent,
            tween(Theme.animVelocity.common), label = ""
        )

        SurfacePro(
            shadowElevation = noteUiCardState.elevation.dp,
            shape = RoundedCornerShape(noteUiCardState.shape.dp),
            color = backgroundColor.value,
            border = BorderStroke(borderWidth.value, borderColor.value),
            modifier = modifier.padding(bottom = dp12, start = dp6, end = dp6)
        ) {
            when {
                isVisibleWallpaper && !isColorWallpaper -> {
                    ContentNoteWallpaper(
                        note = note,
                        isPinned = isPinned,
                        isHiddenAndTrashed = isHiddenAndTrashed,
                        date = date,
                        cardBackground = cardBackground
                    )
                }

                isVisibleWallpaper && isColorWallpaper -> {
                    ContentNoteColorWallpaper(
                        note = note,
                        isPinned = isPinned,
                        isHiddenAndTrashed = isHiddenAndTrashed,
                        date = date,
                        cardBackground = cardBackground
                    )
                }

                else -> {
                    ContentNoteDefault(
                        note = note,
                        isPinned = isPinned,
                        isHiddenAndTrashed = isHiddenAndTrashed,
                        date = date
                    )
                }
            }
        }
    }
}
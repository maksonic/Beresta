package ru.maksonic.beresta.feature.notes_list.ui.core.card.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.common.ui_theme.colors.inverseOnSurface
import ru.maksonic.beresta.common.ui_theme.colors.onTertiary
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.noteUiCardState
import ru.maksonic.beresta.feature.notes_list.ui.core.card.inidcator.IndicationBottomPanel
import ru.maksonic.beresta.feature.notes_list.ui.core.card.inidcator.IndicationTopPanel
import ru.maksonic.beresta.feature.notes_list.ui.core.card.text.NoteDate
import ru.maksonic.beresta.feature.notes_list.ui.core.card.text.NoteMessage
import ru.maksonic.beresta.feature.notes_list.ui.core.card.text.NoteTitle
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper

/**
 * @Author maksonic on 01.11.2023
 */
@Composable
internal fun ContentNoteColorWallpaper(
    note: NoteUi,
    isPinned: Boolean,
    isHiddenAndTrashed: Boolean,
    date: String,
    modifier: Modifier = Modifier,
    cardBackground: @Composable (wallpaper: BaseWallpaper<Color>) -> Unit
) {
    val tint = if (note.style.isDarkWallpaper) inverseOnSurface else onTertiary
    val iconTint = if (note.style.isDarkWallpaper) inverseOnSurface else primary
    
    Box {
        cardBackground(note.wallpaper)

        Column(modifier.fillMaxWidth()) {
            val isEmptyNoteTags = true

            if (noteUiCardState.isVisibleColorMarker && note.style.markerColorId != 0L || isPinned) {
                IndicationTopPanel(
                    markerColor = note.style.markerColor,
                    isPinned = isPinned,
                    iconTint = iconTint,
                    isHiddenAndTrashed = isHiddenAndTrashed
                )
            } else {
                Spacer(modifier.size(dp8))
            }

            NoteTitle(note, tint = tint)

            NoteMessage(note, tint = tint)

            NoteDate(date, tint = tint.copy(0.85f))

            if (isEmptyNoteTags) {
                IndicationBottomPanel()
            } else {
                Spacer(modifier.size(dp8))
            }
        }
    }
}
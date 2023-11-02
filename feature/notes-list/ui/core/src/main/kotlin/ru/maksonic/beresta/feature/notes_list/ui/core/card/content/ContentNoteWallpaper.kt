package ru.maksonic.beresta.feature.notes_list.ui.core.card.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import ru.maksonic.beresta.common.ui_kit.text.TextLabelSmall
import ru.maksonic.beresta.common.ui_theme.colors.inverseOnSurface
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.provide.dp12
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.noteUiCardState
import ru.maksonic.beresta.feature.notes_list.ui.core.MAX_TEXT_LENGTH
import ru.maksonic.beresta.feature.notes_list.ui.core.card.ContainerTextNote
import ru.maksonic.beresta.feature.notes_list.ui.core.card.inidcator.IndicationBottomPanel
import ru.maksonic.beresta.feature.notes_list.ui.core.card.inidcator.IndicationTopPanel
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper

/**
 * @Author maksonic on 01.11.2023
 */
@Composable
fun ContentNoteWallpaper(
    note: NoteUi,
    isPinned: Boolean,
    isHiddenAndTrashed: Boolean,
    date: String,
    modifier: Modifier = Modifier,
    textTint: Color = inverseOnSurface,
    textDateTint: Color = inverseOnSurface,
    cardBackground: @Composable (wallpaper: BaseWallpaper<Color>) -> Unit,
) {
    val iconTint = if (note.style.isDarkWallpaper) inverseOnSurface else primary

    Box {
        cardBackground(note.wallpaper)

        Column(modifier.fillMaxWidth()) {
            val isEmptyNoteTags = true

            if (noteUiCardState.isVisibleColorMarker && note.style.markerColorId != 0L || isPinned) {
                IndicationTopPanel(
                    markerColor = note.style.markerColor,
                    iconTint = iconTint,
                    isPinned = isPinned,
                    isHiddenAndTrashed = isHiddenAndTrashed
                )
            } else {
                Spacer(modifier.size(dp8))
            }

            ContainerTextNote(modifier.padding(top = dp8, bottom = dp8)) {
                val noteTitle = note.title.take(MAX_TEXT_LENGTH)
                val noteMessage = note.message.take(MAX_TEXT_LENGTH)

                Text(
                    text = if (note.title.isBlank()) noteMessage else noteTitle,
                    style = TextDesign.titleMedium.copy(color = textTint),
                    maxLines = noteUiCardState.maxTitleLines,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = dp8,
                        end = dp8,
                        top = dp4,
                        bottom = dp4
                    )
                )
            }
            ContainerTextNote(modifier.padding(bottom = dp12)) {
                if (note.message.isNotBlank() && note.title.isNotBlank()) {
                    Text(
                        text = note.message.take(MAX_TEXT_LENGTH),
                        style = TextDesign.bodyMedium.copy(color = textTint),
                        maxLines = noteUiCardState.maxMessageLines,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(
                            start = dp8,
                            end = dp8,
                            top = dp4,
                            bottom = dp4
                        )
                    )
                }
            }

            ContainerTextNote {
                TextLabelSmall(
                    text = date,
                    color = textDateTint.copy(0.85f),
                    modifier = Modifier.padding(start = dp8, end = dp8, top = dp4, bottom = dp4)
                )
            }

            if (isEmptyNoteTags) {
                IndicationBottomPanel()
            } else {
                Spacer(modifier.size(dp8))
            }
        }
    }
}
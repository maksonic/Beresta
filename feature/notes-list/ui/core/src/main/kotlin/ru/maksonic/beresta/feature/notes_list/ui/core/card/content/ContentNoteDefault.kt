package ru.maksonic.beresta.feature.notes_list.ui.core.card.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.noteUiCardState
import ru.maksonic.beresta.feature.notes_list.ui.core.card.inidcator.IndicationBottomPanel
import ru.maksonic.beresta.feature.notes_list.ui.core.card.inidcator.IndicationTopPanel
import ru.maksonic.beresta.feature.notes_list.ui.core.card.text.NoteDate
import ru.maksonic.beresta.feature.notes_list.ui.core.card.text.NoteMessage
import ru.maksonic.beresta.feature.notes_list.ui.core.card.text.NoteTitle

/**
 * @Author maksonic on 31.10.2023
 */
@Composable
internal fun ContentNoteDefault(
    note: NoteUi,
    isPinned: Boolean,
    isHiddenAndTrashed: Boolean,
    date: String,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth()) {
        val isEmptyNoteTags = true

        if (noteUiCardState.isVisibleColorMarker && note.style.markerColorId != 0L || isPinned) {
            IndicationTopPanel(
                markerColor = note.style.markerColor,
                isPinned = isPinned,
                isHiddenAndTrashed = isHiddenAndTrashed
            )
        } else {
            Spacer(modifier.size(dp8))
        }

        NoteTitle(note)

        NoteMessage(note)

        NoteDate(date)

        if (isEmptyNoteTags) {
            IndicationBottomPanel()
        } else {
            Spacer(modifier.size(dp8))
        }
    }
}
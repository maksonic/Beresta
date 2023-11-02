package ru.maksonic.beresta.screen.settings.appearance.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardUiState
import ru.maksonic.beresta.screen.settings.appearance.core.ModalSheetContent
import ru.maksonic.beresta.screen.settings.appearance.ui.Send

/**
 * @Author maksonic on 08.07.2023
 */
@Composable
internal fun MultipleModalBottomSheetContent(
    send: Send,
    currentSheetContent: State<ModalSheetContent>,
    noteCardState: State<NoteCardUiState>,
    currentVelocityTitle: State<String>,
    modifier: Modifier = Modifier,
) {
    when (currentSheetContent.value) {

        ModalSheetContent.NOTE_CARD_LINES_PICKER -> {
            NoteCardLinesPickerSheetContent(send, noteCardState)
        }

        ModalSheetContent.ANIMATIONS_VELOCITY_PICKER -> {
            AnimationsVelocityPickerSheetContent(send, currentVelocityTitle)
        }
        ModalSheetContent.NOTHING -> Box(modifier.size(1.dp))
    }
}
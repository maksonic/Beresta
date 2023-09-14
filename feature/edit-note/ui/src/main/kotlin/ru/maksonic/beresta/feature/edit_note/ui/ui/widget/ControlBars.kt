package ru.maksonic.beresta.feature.edit_note.ui.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.edit_note.ui.core.Model
import ru.maksonic.beresta.feature.edit_note.ui.ui.SendMessage
import ru.maksonic.beresta.feature.notes.api.ui.isBlank
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 08.09.2023
 */
@Composable
internal fun ControlBars(
    model: State<Model>,
    send: SendMessage,
    isScrollUp: State<Boolean>,
    canScrollBackward: State<Boolean>,
    isVisibleAddNoteDialog: Boolean,
    isHiddenNote: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.statusBarsPadding().fillMaxHeight(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        EditNoteTopBar(
            model = model,
            send = send,
            isScrollUp = isScrollUp,
            canScrollBackward = canScrollBackward
        )

        AnimateFadeInOut(!isVisibleAddNoteDialog) {
            EditorBottomBar(
                send = send,
                isScrollUp = isScrollUp,
                isBlankNote = model.value.editableNote.isBlank(),
                isHiddenNote = isHiddenNote
            )
        }
    }
}
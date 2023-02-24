package ru.maksonic.beresta.feature.edit_note.core.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.edit_note.core.Model
import ru.maksonic.beresta.feature.edit_note.core.Msg

/**
 * @Author maksonic on 24.02.2023
 */
@Composable
fun EditNoteOverflowContainer(
    model: Model,
    send: SendMessage,
    isScrolledTopNotes: () -> Boolean,
    modifier: Modifier = Modifier
) {

    BackHandler(model.isExpandedEdit) {
        if (model.isExpandedEdit) {
            send(Msg.Ui.OnBackTopBarClicked)
        }
    }

    BoxWithConstraints(modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        val boxScope = this

        EditNoteExpandableFab(
            model = model,
            send = send,
            isScrolledTopNotes = isScrolledTopNotes,
            boxScope = boxScope
        )
    }
}
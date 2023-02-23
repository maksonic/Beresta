package ru.maksonic.beresta.feature.edit_note.core.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.edit_note.core.Model
import ru.maksonic.beresta.feature.edit_note.core.Msg

/**
 * @Author maksonic on 23.02.2023
 */
@Composable
internal fun ExpandableFabButton(model: Model, send: SendMessage, isScrollTop: () -> Boolean) {

    BackHandler(model.isExpandedEdit) {
        if (model.isExpandedEdit) {
            send(Msg.Ui.OnBackTopBarClicked)
        }
    }

    EditNoteOverflowContainer(send = send) {
        val boxScope = it

        EditNoteFabButton(
            model = model,
            send = send,
            isScrollTop = isScrollTop,
            boxScope = boxScope
        )
    }
}


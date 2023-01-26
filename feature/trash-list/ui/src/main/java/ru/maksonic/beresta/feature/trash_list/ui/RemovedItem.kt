package ru.maksonic.beresta.feature.trash_list.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import ru.maksonic.beresta.feature.trash_list.ui.core.Msg
import ru.maksonic.beresta.ui.theme.color.primaryContainer
import ru.maksonic.beresta.ui.theme.color.secondary
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.component.*
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick

/**
 * @Author maksonic on 24.01.2023
 */
@Composable
internal fun RemovedItem(
    note: NoteUi,
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    val backgroundColor = animateColorAsState(
        targetValue = if (note.isSelected) secondaryContainer else primaryContainer
    )

    BoxWithScaleInOutOnClick(
        onClick = {
            send(Msg.Ui.OnNoteClicked(note.id))
        },
        onLongClick = {
            send(Msg.Ui.OnNoteLongClicked(note.id))
        },
        backgroundColor = { backgroundColor.value },
        shape = Shape.cornerBig,
        modifier = modifier.padding(top = dp12, start = dp16, end = dp16)

    ) {
        Column(
            modifier
                .fillMaxWidth()
                .padding(start = dp16)
        ) {
            Text(
                text = note.title,
                style = TextDesign.title,
                modifier = modifier
            )
            Text(
                text = note.message,
                style = TextDesign.body,
                modifier = modifier.padding(top = dp8)
            )
            Text(
                text = note.dateCreation,
                style = TextDesign.captionSmall.copy(color = secondary),
                modifier = modifier.padding(top = dp16, bottom = dp8)
            )
        }
    }
}
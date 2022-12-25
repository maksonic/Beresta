package ru.maksonic.beresta.feature.notes_list.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.core.Feature
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.color.primaryContainer
import ru.maksonic.beresta.ui.theme.color.secondary
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.component.*
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick

/**
 * @Author maksonic on 25.12.2022
 */

@Preview
@Composable
private fun NoteItemPreview() {
    BerestaTheme {
        NoteItem(note = NoteUi.preview(), isSelectionState = false, msg = {})
    }
}

@Composable
internal fun NoteItem(
    note: NoteUi,
    isSelectionState: Boolean,
    msg: (Feature.Msg) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithScaleInOutOnClick(
        onClick = {
            if (isSelectionState) {
                val updated = note.copy(isSelected = !note.isSelected)
                msg(Feature.Msg.Ui.SelectItemForRemove(note.id, updated.isSelected))
            } else {
                msg(Feature.Msg.Ui.OnItemClicked(note.id))
            }
        },
        onLongClick = {
            val updated = note.copy(isSelected = !note.isSelected)
            msg(Feature.Msg.Ui.SelectItemForRemove(note.id, updated.isSelected))
        },
        backgroundColor = if (note.isSelected) secondaryContainer else primaryContainer,
        shape = Shape.cornerBig,
        modifier = modifier.padding(bottom = dp12, start = dp16, end = dp16)

    ) {
        Column(
            modifier
                .fillMaxWidth()
                .padding(start = dp16)
        ) {
            Text(
                text = note.title,
                style = TextDesign.title,
                modifier = modifier.padding(top = dp16)
            )
            Text(
                text = note.message,
                style = TextDesign.body,
                modifier = modifier.padding(top = dp8)
            )
            Text(
                text = note.dateCreation,
                style = TextDesign.caption.copy(color = secondary),
                modifier = modifier.padding(top = dp16, bottom = dp16)
            )
        }
    }
}
package ru.maksonic.beresta.feature.notes_list.ui.widget.note

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.SendMessage
import ru.maksonic.beresta.feature.notes_list.ui.core.Msg
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.R
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.primaryContainer
import ru.maksonic.beresta.ui.theme.color.secondary
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.component.*
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 25.12.2022
 */
@Composable
internal fun NoteItem(
    send: SendMessage,
    note: NoteUi,
    modifier: Modifier = Modifier
) {
    val backgroundColor = animateColorAsState(
        targetValue = if (note.isSelected) secondaryContainer else primaryContainer
    )

    BoxWithScaleInOutOnClick(
        onClick = { send(Msg.Ui.OnNoteClicked(note.id)) },
        onLongClick = { send(Msg.Ui.OnNoteLongClicked(note.id)) },
        backgroundColor = { backgroundColor.value },
        shape = Shape.cornerBig,
        modifier = modifier.padding(top = dp12, start = dp16, end = dp16)

    ) {
        Column(
            modifier
                .fillMaxWidth()
                .padding(start = dp16)
        ) {
            TopPanelIndication(isPinned = { note.isPinned })
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
                modifier = modifier.padding(top = dp16, bottom = dp24)
            )
        }
    }
}

@Composable
private fun TopPanelIndication(isPinned: () -> Boolean, modifier: Modifier = Modifier) {

    Row(
        modifier
            .height(dp24)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimateFadeInOut(
            visible = isPinned()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_pin),
                modifier = modifier
                    .padding(end = dp4)
                    .size(dp16),
                tint = primary,
                contentDescription = null
            )
        }
    }
}

@Composable
fun BottomPanelIndication(modifier: Modifier = Modifier) {
    Row(
        modifier
            .height(dp16)
            .fillMaxWidth()
    ) {

    }
}

@Preview
@Composable
private fun NoteItemPreview() {
    BerestaTheme { NoteItem({}, NoteUi.Preview) }
}

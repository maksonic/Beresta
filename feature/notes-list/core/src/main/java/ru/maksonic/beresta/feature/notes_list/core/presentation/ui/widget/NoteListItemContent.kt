package ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.theme.component.*
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Pin
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 21.02.2023
 */
private const val MAX_TITLE_LENGTH = 100
private const val MAX_MESSAGE_LENGTH = 200
@Composable
internal fun NoteListItemContent(
    onNoteClicked: (id: Long) -> Unit,
    onNoteLongClicked: (id: Long) -> Unit,
    note: NoteUi,
    modifier: Modifier = Modifier
) {
    val backgroundColor = animateColorAsState(
        targetValue = if (note.isSelected) secondary else primaryContainer
    )

    BoxWithScaleInOutOnClick(
        onClick = { onNoteClicked(note.id) },
        onLongClick = { onNoteLongClicked(note.id) },
        backgroundColor = { backgroundColor.value },
        shape = Shape.cornerBig,
        modifier = modifier.padding(bottom = dp12, start = dp6, end = dp6)

    ) {
        Column(
            modifier
                .fillMaxWidth()
                .padding(start = dp16)
        ) {
            TopPanelIndication(isPinned = { note.isPinned })
            Text(
                text = note.title.take(MAX_TITLE_LENGTH),
                style = TextDesign.title.copy(color = onPrimaryContainer),
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.padding(end = dp8)
            )
            Text(
                text = note.message.take(MAX_MESSAGE_LENGTH),
                style = TextDesign.bodyPrimary.copy(color = onPrimaryContainer),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.padding(top = dp8, end = dp8)
            )
            Text(
                text = note.dateCreation,
                style = TextDesign.captionSmall.copy(color = inverseSurface),
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
                imageVector = AppIcon.Pin,
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
    BerestaTheme {
        NoteListItemContent({}, {}, note = NoteUi.Preview)
    }
}

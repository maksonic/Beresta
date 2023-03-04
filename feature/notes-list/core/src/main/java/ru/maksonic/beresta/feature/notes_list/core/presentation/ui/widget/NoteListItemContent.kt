package ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.core.presentation.Msg
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.primaryContainer
import ru.maksonic.beresta.ui.theme.color.secondary
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp24
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Pin
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 21.02.2023
 */
@Composable
internal fun NoteListItemContent(
    onNoteClicked: (id: Long) -> Unit,
    onNoteLongClicked: (id: Long) -> Unit,
    note: NoteUi,
    modifier: Modifier = Modifier
) {
    val backgroundColor = animateColorAsState(
        targetValue = if (note.isSelected) secondaryContainer else primaryContainer
    )

    BoxWithScaleInOutOnClick(
        onClick = { onNoteClicked(note.id) },
        onLongClick = { onNoteLongClicked(note.id) },
        backgroundColor = { backgroundColor.value },
        shape = Shape.cornerBig,
        modifier = modifier.padding(bottom = dp12, start = dp16, end = dp16)

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
                style = TextDesign.bodyPrimary,
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
        NoteListItemContent({}, {}, note = NoteUi.preview)
    }
}

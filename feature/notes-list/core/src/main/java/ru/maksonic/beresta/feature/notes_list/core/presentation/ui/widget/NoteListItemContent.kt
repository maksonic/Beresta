package ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
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
@Composable
internal fun NoteListItemContent(
    note: NoteUi,
    selectedNotes: Set<NoteUi>,
    onNoteClicked: (id: Long) -> Unit,
    onNoteLongClicked: (id: Long) -> Unit,
    maxTitleLength: Int,
    maxMessageLength: Int,
    modifier: Modifier = Modifier
) {
    val isFocusedItem = rememberSaveable { mutableStateOf(false) }
    val isSelectedColors = if (isFocusedItem.value) tertiary else secondary
    val colors = if (isFocusedItem.value) outlineVariant else primaryContainer
    val backgroundColor =
        animateColorAsState(if (selectedNotes.contains(note)) isSelectedColors else colors)

    BoxWithScaleInOutOnClick(
        onClick = { onNoteClicked(note.id) },
        onLongClick = { onNoteLongClicked(note.id) },
        backgroundColor = backgroundColor,
        shape = Shape.cornerBig,
        modifier = modifier
            .padding(bottom = dp12, start = dp6, end = dp6)
            .onFocusChanged { isFocusedItem.value = it.isFocused }
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .padding(start = dp16)
        ) {
            TopPanelIndication(isPinned = { note.isPinned })
            CardContent(note, maxTitleLength, maxMessageLength)
        }
    }
}

@Composable
private fun CardContent(
    note: NoteUi,
    maxTitleLength: Int,
    maxMessageLength: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = note.title.take(maxTitleLength),
        style = TextDesign.title.copy(color = onPrimaryContainer),
        maxLines = 1,
        softWrap = false,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.padding(end = dp8)
    )
    Text(
        text = note.message.take(maxMessageLength),
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
private fun BottomPanelIndication(modifier: Modifier = Modifier) {
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
        NoteListItemContent(note = NoteUi.Preview, selectedNotes = emptySet(), {}, {}, 20, 20)
    }
}

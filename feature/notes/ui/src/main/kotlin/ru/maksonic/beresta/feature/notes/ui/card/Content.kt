package ru.maksonic.beresta.feature.notes.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import ru.maksonic.beresta.feature.notes.api.ui.noteUiCardState
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.inverseSurface
import ru.maksonic.beresta.ui.theme.color.onPrimaryContainer
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp24
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Pin
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 03.07.2023
 */
private const val MAX_TEXT_LENGTH = 500

@Composable
internal fun Content(note: NoteUi, modifier: Modifier) {

    val noteTitle = rememberUpdatedState(note.title.take(MAX_TEXT_LENGTH))
    val noteMessage = rememberUpdatedState(note.message.take(MAX_TEXT_LENGTH))
    val isPinned = rememberUpdatedState(note.isPinned)
    val hintPrefixRemovedDate = text.trash.hintRemovedDatePrefix
    val date =
        if (note.isMovedToTrash) "$hintPrefixRemovedDate ${note.dateMovedToTrash}" else note.dateCreation

    Column(
        modifier
            .fillMaxWidth()
            .padding(start = dp16)
    ) {
        TopPanelIndication(isPinned)

        Text(
            text = if (note.title.isBlank()) noteMessage.value else noteTitle.value,
            style = TextDesign.title.copy(color = onPrimaryContainer),
            maxLines = noteUiCardState.maxTitleLines,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.padding(end = dp8)
        )
        if (note.message.isNotBlank() && note.title.isNotBlank()) {
            Text(
                text = noteMessage.value,
                style = TextDesign.bodyPrimary.copy(color = onPrimaryContainer),
                maxLines = noteUiCardState.maxMessageLines,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.padding(top = dp8, end = dp8)
            )
        }
        Text(
            text = date,
            style = TextDesign.captionSmall.copy(color = inverseSurface),
            modifier = modifier.padding(top = dp16)
        )

        BottomPanelIndication()
    }
}

@Composable
private fun TopPanelIndication(isPinned: State<Boolean>, modifier: Modifier = Modifier) {
    Row(
        modifier
            .height(dp24)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimateFadeInOut(visible = isPinned.value) {
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
            .height(dp24)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

    }
}

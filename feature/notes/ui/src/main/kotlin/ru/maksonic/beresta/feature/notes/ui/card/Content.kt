package ru.maksonic.beresta.feature.notes.ui.card

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.notes.api.ui.noteUiCardState
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.color_palette.Palette
import ru.maksonic.beresta.ui.theme.color.inverseSurface
import ru.maksonic.beresta.ui.theme.color.onPrimaryContainer
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp24
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.LockOpen
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
    val isPinned = rememberUpdatedState(note.isPinned && !note.isMovedToTrash)
    val isHiddenAndTrashed = rememberUpdatedState(note.isHidden && note.isMovedToTrash)
    val hintPrefixRemovedDate = text.trash.hintRemovedDatePrefix
    val date = if (note.isMovedToTrash) "$hintPrefixRemovedDate ${note.dateMovedToTrash}"
    else note.dateCreation

    Column(
        modifier
            .fillMaxWidth()
            .padding(start = dp16)
    ) {

        TopPanelIndication(note.markerColorId, isPinned, isHiddenAndTrashed)

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
private fun TopPanelIndication(
    colorId: Long,
    isPinned: State<Boolean>,
    isHiddenAndTrashed: State<Boolean>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .height(dp24)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (noteUiCardState.isVisibleColorMarker && colorId != 0L) {
            Canvas(
                modifier
                    .weight(1f)
                    .padding(end = if (isPinned.value) 0.dp else dp16)
                    .size(dp4)
                    .clip(CircleShape),
                onDraw = { drawRect(Palette.markerColors[colorId] ?: Color.Transparent) })
        }

        AnimateFadeInOut(visible = isHiddenAndTrashed.value) {
            Icon(
                imageVector = AppIcon.LockOpen,
                modifier = modifier
                    .padding(end = dp4)
                    .size(dp16),
                tint = primary,
                contentDescription = null
            )
        }
        AnimateFadeInOut(visible = isPinned.value) {
            Icon(
                imageVector = AppIcon.Pin,
                modifier = modifier
                    .size(dp24)
                    .padding(dp4),
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

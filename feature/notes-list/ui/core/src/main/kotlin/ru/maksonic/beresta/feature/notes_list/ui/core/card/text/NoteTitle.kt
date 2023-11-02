package ru.maksonic.beresta.feature.notes_list.ui.core.card.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import ru.maksonic.beresta.common.ui_theme.colors.onPrimaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.noteUiCardState
import ru.maksonic.beresta.feature.notes_list.ui.core.MAX_TEXT_LENGTH

/**
 * @Author maksonic on 31.10.2023
 */
@Composable
internal fun NoteTitle(
    note: NoteUi,
    modifier: Modifier = Modifier,
    tint: Color = onPrimaryContainer
) {
    Text(
        text = if (note.title.isBlank()) note.message.take(MAX_TEXT_LENGTH) else note.title.take(MAX_TEXT_LENGTH),
        style = TextDesign.titleMedium.copy(color = tint),
        maxLines = noteUiCardState.maxTitleLines,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.padding(start = dp16, end = dp16)
    )
}

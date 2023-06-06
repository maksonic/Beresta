package ru.maksonic.beresta.feature.notes.list.core.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes.list.core.ui.NoteItemPlaceholder
import ru.maksonic.beresta.ui.widget.functional.animation.PlaceholderListWidget

/**
 * @Author maksonic on 25.04.2023
 */
private const val PLACEHOLDERS_COUNT = 10

@Composable
internal fun NotesLoaderWidgetContent(modifier: Modifier) {

    PlaceholderListWidget(
        placeholdersCount = PLACEHOLDERS_COUNT,
        modifier = modifier
    ) { animateColor ->
        NoteItemPlaceholder(animateColor)
    }
}

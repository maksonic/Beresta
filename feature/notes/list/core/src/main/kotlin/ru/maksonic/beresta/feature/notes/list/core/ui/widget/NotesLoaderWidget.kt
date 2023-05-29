package ru.maksonic.beresta.feature.notes.list.core.ui.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes.list.core.ui.NoteItemPlaceholder
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp10
import ru.maksonic.beresta.ui.widget.functional.animation.PlaceholderListWidget

/**
 * @Author maksonic on 25.04.2023
 */
private const val PLACEHOLDERS_COUNT = 10

@Composable
internal fun NotesLoaderWidget() {

    PlaceholderListWidget(
        placeholdersCount = PLACEHOLDERS_COUNT,
        modifier = Modifier.padding(
            top = Theme.widgetSize.topBarNormalHeight.plus(dp10),
            start = dp10,
            end = dp10
        )
    ) { animateColor ->
        NoteItemPlaceholder(animateColor)
    }
}

package ru.maksonic.beresta.feature.notes.list.core.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes.list.core.ui.NoteItemPlaceholder
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp10

/**
 * @Author maksonic on 25.04.2023
 */
private const val PLACEHOLDERS_COUNT = 5

@Composable
internal fun NotesLoaderWidget(modifier: Modifier = Modifier) {
    val delayList = listOf(300, 450, 600, 750, 900)

    Column(
        modifier.padding(
            top = Theme.widgetSize.topBarNormalHeight.plus(dp10),
            start = dp10,
            end = dp10
        )
    ) {
        repeat(PLACEHOLDERS_COUNT) {
            NoteItemPlaceholder(enterDelay = delayList[it], exitDelay = delayList.reversed()[it])
        }
    }
}

package ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesCollection
import ru.maksonic.beresta.feature.notes_list.core.presentation.Msg
import ru.maksonic.beresta.feature.notes_list.core.presentation.NotesListSandbox
import ru.maksonic.beresta.ui.theme.Theme

/**
 * @Author maksonic on 22.02.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun FetchedNotesWidgetContent(
    notes: NotesCollection,
    sandbox: NotesListSandbox = koinViewModel(),
    modifier: Modifier = Modifier
) {
    sandbox.sendMsg(Msg.Inner.FetchedNotesCollection(notes))

    val model = sandbox.model.collectAsState().value
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        modifier = modifier
            .systemBarsPadding()
            .fillMaxSize()
            .padding(top = Theme.widgetSize.topBarNormalHeight),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = model.notes.data, key = { note -> note.id }) { note ->
            NoteListItemContent(
                onNoteClicked = {},
                onNoteLongClicked = {},
                note = note,
                modifier = modifier.animateItemPlacement()
            )
        }
    }
}
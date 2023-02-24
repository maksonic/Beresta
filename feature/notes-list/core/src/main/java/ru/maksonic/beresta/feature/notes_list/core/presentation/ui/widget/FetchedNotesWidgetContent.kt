package ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesCollection
import ru.maksonic.beresta.feature.notes_list.core.presentation.Msg
import ru.maksonic.beresta.feature.notes_list.core.presentation.NotesListSandbox
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp24

/**
 * @Author maksonic on 22.02.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun FetchedNotesWidgetContent(
    modifier: Modifier = Modifier,
    notes: NotesCollection,
    scrollState: () -> LazyListState,
    sandbox: NotesListSandbox = koinViewModel(),
) {
    sandbox.sendMsg(Msg.Inner.FetchedNotesCollection(notes))
    val model = sandbox.model.collectAsState().value

    LazyColumn(
        state = scrollState(),
        modifier = modifier
            .statusBarsPadding()
            .fillMaxSize()
            .padding(top = Theme.widgetSize.topBarNormalHeight.plus(dp24)),
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

        item {
            Spacer(
                modifier
                    .navigationBarsPadding()
                    .height(Theme.widgetSize.bottomMainPanelHeight)
            )
        }
    }
}
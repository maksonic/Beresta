package ru.maksonic.beresta.feature.notes_list.ui.state

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.feature.notes_list.api.collection.FilterChipsCollection
import ru.maksonic.beresta.feature.notes_list.api.collection.NotesCollection
import ru.maksonic.beresta.feature.notes_list.ui.core.Feature
import ru.maksonic.beresta.feature.notes_list.ui.widget.NoteItem
import ru.maksonic.beresta.feature.notes_list.ui.widget.NotesFilterChips
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.widget.functional.isScrollUp
import ru.maksonic.beresta.ui.widget.functional.isScrolledBottom
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItem

/**
 * @Author maksonic on 25.12.2022
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SuccessViewState(
    notes: NotesCollection,
    filters: FilterChipsCollection,
    onFilterClick: (Int) -> Unit,
    msg: (Feature.Msg) -> Unit,
    showMainTopBar: (Boolean) -> Unit,
    showBottomPanel: (Boolean) -> Unit,
    isColoredTopBar: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val notesScrollState = rememberLazyListState()
    val firstVisibleNote = notesScrollState.isVisibleFirstItem()
    val isScrollUp = notesScrollState.isScrollUp()
    val isScrolledEnd = notesScrollState.isScrolledBottom()

    LaunchedEffect(notesScrollState) {
        snapshotFlow { notesScrollState.firstVisibleItemIndex }
            .map { index -> index == 0 }
            .distinctUntilChanged()
            .collect {
                isColoredTopBar(it)
            }
    }

    LaunchedEffect(isScrollUp) {
        showMainTopBar(isScrollUp)
        showBottomPanel(isScrollUp)
    }
    if (isScrolledEnd) {
        LaunchedEffect(Unit) {
            showBottomPanel(true)
        }
    }

    LazyColumn(
        modifier
            .navigationBarsPadding()
            .fillMaxSize(),
        state = notesScrollState,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        stickyHeader {
            NotesFilterChips(filters = filters, isVisibleFirstNote = { firstVisibleNote.value })
        }

        items(
            items = notes.notes,
            key = { note -> note.id }
        ) { note ->
            NoteItem(note = note, msg = msg)
        }

        item() {
            val bottomPadding = Theme.widgetSize.bottomPanelHeightIdle.plus(dp12)
            Spacer(
                modifier
                    .fillMaxWidth()
                    .height(bottomPadding)
            )
        }
    }
}

package ru.maksonic.beresta.feature.notes_list.ui.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.core.Feature
import ru.maksonic.beresta.feature.notes_list.ui.widget.NoteItem
import ru.maksonic.beresta.feature.notes_list.ui.widget.NotesFilterChips
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.widget.functional.isScrollUp
import ru.maksonic.beresta.ui.widget.functional.isScrolledBottom
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItem

/**
 * @Author maksonic on 25.12.2022
 */
@Composable
internal fun SuccessViewState(
    notes: List<NoteUi>,
    filters: List<NoteUi.Filter>,
    onFilterClick: (Int) -> Unit,
    msg: (Feature.Msg) -> Unit,
    isSelectionState: Boolean,
    updateState: (Boolean) -> Unit,
    updateBottomPanelVisibilityState: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val notesScrollState = rememberLazyListState()
    val isVisibleFirstNote = notesScrollState.isVisibleFirstItem()
    val isScrolledUp = notesScrollState.isScrollUp()
    val isScrolledBottom = notesScrollState.isScrolledBottom()

    LaunchedEffect(notesScrollState) {
        snapshotFlow { notesScrollState.firstVisibleItemIndex }
            .map { index -> index == 0 }
            .distinctUntilChanged()
            .collect {
                updateState(it)
            }
    }

    if (isScrolledUp) {
        LaunchedEffect(Unit) {
            updateBottomPanelVisibilityState(true)
        }
    } else {
        LaunchedEffect(Unit) {
            updateBottomPanelVisibilityState(false)
        }
    }

    if (isScrolledBottom) {
        LaunchedEffect(Unit) {
            updateBottomPanelVisibilityState(true)
        }
    }

    Box(modifier.fillMaxSize()) {
        val notesList = remember { mutableStateOf(notes) }
        LazyColumn(
            modifier
                .padding(top = Theme.widgetSize.topBarNormalHeight)
                .fillMaxSize(),
            state = notesScrollState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(notesList.value, key = { note ->
                note.id
            }
            ) { note ->
                NoteItem(note, msg = msg, isSelectionState = isSelectionState)
            }
        }
        NotesFilterChips(filters = filters, isTopScrollState = { isVisibleFirstNote.value })
    }
}

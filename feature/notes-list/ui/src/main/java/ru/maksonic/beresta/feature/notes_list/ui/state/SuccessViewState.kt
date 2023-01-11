package ru.maksonic.beresta.feature.notes_list.ui.state

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.NotesSharedState
import ru.maksonic.beresta.feature.notes_list.ui.core.Feature
import ru.maksonic.beresta.feature.notes_list.ui.widget.NoteItem
import ru.maksonic.beresta.feature.notes_list.ui.widget.NotesFilterChips
import ru.maksonic.beresta.ui.theme.Theme

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
    modifier: Modifier = Modifier
) {
    val notesScrollState = rememberLazyListState()
    val isVisibleFirstNote =
        remember { derivedStateOf { notesScrollState.firstVisibleItemIndex == 0 } }

    LaunchedEffect(!isVisibleFirstNote.value) {
        updateState(isVisibleFirstNote.value)
    }

    Box(modifier.fillMaxSize()) {
        LazyColumn(
            modifier
                .padding(top = Theme.widgetSize.topBarNormalHeight)
                .fillMaxSize(),
            state = notesScrollState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            notes.forEach { note ->
                item {
                    NoteItem(note, msg = msg, isSelectionState = isSelectionState)
                }
            }

            item {
                val dp = if (isSelectionState)
                    Theme.widgetSize.bottomPanelHeightSelected
                else
                    Theme.widgetSize.bottomPanelHeightIdle
                val spaceHeight = animateDpAsState(targetValue = dp)
                Spacer(modifier.height(spaceHeight.value))
            }
        }
        NotesFilterChips(filters = filters, isVisibleFirstNote = { isVisibleFirstNote.value })
    }
}

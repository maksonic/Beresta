package ru.maksonic.beresta.feature.notes_list.ui.state

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
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
    updateIsScrollUpState: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val notesScrollState = rememberLazyListState()
    val isTopScrollState by remember { derivedStateOf { notesScrollState.firstVisibleItemScrollOffset == 0 } }

    /*LaunchedEffect(!isTopScrollState) {
        updateState(isTopScrollState)
    }*/
    LaunchedEffect(!isTopScrollState) {
        updateState(isTopScrollState)
    }
   /* SideEffect {
        val isw =
            notesScrollState.firstVisibleItemScrollOffset == 0
        isTopScrollState.value = isw
    }*/
  //  val isNotesScrollUp = notesScrollState.isScrollUp()
/*
    val isTopScrollState =
        remember { derivedStateOf { notesScrollState.firstVisibleItemScrollOffset == 0 } }*/

   /* LaunchedEffect(!isTopScrollState.value) {
        updateState(isTopScrollState.value)
    }

    LaunchedEffect(!isNotesScrollUp) {
        updateIsScrollUpState(isNotesScrollUp)

    }*/


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
                    Text(note.title, modifier = modifier.padding(32.dp))
                   // NoteItem(note, msg = msg, isSelectionState = isSelectionState)
                }
            }
    }
     NotesFilterChips(filters = filters, isTopScrollState = { isTopScrollState })
}
/*
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
                    NoteItem(note, msg = msg, *//*isSelectionState = isSelectionState*//*)
                }
            }

           *//* item {
                val dp = if (isSelectionState)
                    Theme.widgetSize.bottomPanelHeightSelected
                else
                    Theme.widgetSize.bottomPanelHeightIdle
                val spaceHeight = animateDpAsState(targetValue = dp)
                Spacer(modifier.height(spaceHeight.value))
            }*//*
        }
       // NotesFilterChips(filters = filters, isVisibleFirstNote = { isTopScrollState.value })
    }*/
}

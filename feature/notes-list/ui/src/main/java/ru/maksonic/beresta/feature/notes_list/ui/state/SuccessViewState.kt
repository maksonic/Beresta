package ru.maksonic.beresta.feature.notes_list.ui.state

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.feature.notes_list.api.NotesSharedState
import ru.maksonic.beresta.feature.notes_list.api.collection.FilterChipsCollection
import ru.maksonic.beresta.feature.notes_list.api.collection.NotesCollection
import ru.maksonic.beresta.feature.notes_list.api.isColoredMainTopBar
import ru.maksonic.beresta.feature.notes_list.api.isVisibleBottomPanel
import ru.maksonic.beresta.feature.notes_list.api.isVisibleMainTopBar
import ru.maksonic.beresta.feature.notes_list.ui.core.Feature
import ru.maksonic.beresta.feature.notes_list.ui.widget.note.NoteItem
import ru.maksonic.beresta.feature.notes_list.ui.widget.NotesFilterChips
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior
import ru.maksonic.beresta.ui.widget.functional.isScrollUp
import ru.maksonic.beresta.ui.widget.functional.isScrolledEnd
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItem

/**
 * @Author maksonic on 25.12.2022
 */


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SuccessViewState(
    msg: (Feature.Msg) -> Unit,
    notes: NotesCollection,
    filters: FilterChipsCollection,
    mutableSharedNotesState: MutableStateFlow<NotesSharedState>,
    onFilterClick: (Int) -> Unit,
    isSelectionState: () -> Boolean,
    modifier: Modifier = Modifier
) {
    val notesScrollState = rememberLazyListState()
    val firstVisibleNote = notesScrollState.isVisibleFirstItem()
    val isScrollUp = notesScrollState.isScrollUp()
    val isScrolledEnd = notesScrollState.isScrolledEnd()

    LaunchedEffect(notesScrollState) {
        snapshotFlow { notesScrollState.firstVisibleItemIndex }
            .map { index -> index == 0 }
            .distinctUntilChanged()
            .collect { isColoredTopBar ->
                mutableSharedNotesState.isColoredMainTopBar(isColoredTopBar)
            }
    }

    LaunchedEffect(isSelectionState) {
        mutableSharedNotesState.isVisibleBottomPanel(true)
    }

    LaunchedEffect(isScrollUp) {
        mutableSharedNotesState.isVisibleMainTopBar(isScrollUp)

        if (isSelectionState()) {
            mutableSharedNotesState.isVisibleBottomPanel(true)
        } else {
            if (isScrolledEnd) {
                mutableSharedNotesState.isVisibleBottomPanel(true)
            } else {
                mutableSharedNotesState.isVisibleBottomPanel(isScrollUp)
            }
        }
    }

    if (isScrolledEnd) {
        LaunchedEffect(Unit) {
            mutableSharedNotesState.isVisibleBottomPanel(true)
        }
    }

    OverscrollBehavior {
        val bottomPaddingHeight = animateDpAsState(
            targetValue = if (isSelectionState()) {
                Theme.widgetSize.bottomPanelHeightSelected.plus(dp12)
            } else {
                Theme.widgetSize.bottomPanelHeightIdle.plus(dp12)
            }
        )

        LazyColumn(
            state = notesScrollState,
            modifier = modifier.fillMaxSize().navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            stickyHeader {
                NotesFilterChips(filters = filters, isVisibleFirstNote = { firstVisibleNote.value })
            }

            items(
                items = notes.notes,
                key = { note -> note.id }
            ) { note ->
                NoteItem(note = note, msg = msg, modifier.animateItemPlacement())
            }
            item {
                Spacer(
                    modifier
                        .fillMaxWidth()
                        .height(bottomPaddingHeight.value)
                )
            }
        }
    }
}


package ru.maksonic.beresta.feature.notes_list.ui.state

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.feature.notes_list.api.collection.FilterChipsCollection
import ru.maksonic.beresta.feature.notes_list.api.collection.NotesCollection
import ru.maksonic.beresta.feature.notes_list.api.feature.NotesSharedState
import ru.maksonic.beresta.feature.notes_list.api.feature.isColoredMainTopBar
import ru.maksonic.beresta.feature.notes_list.api.feature.isVisibleBottomPanel
import ru.maksonic.beresta.feature.notes_list.api.feature.isVisibleMainTopBar
import ru.maksonic.beresta.feature.notes_list.ui.core.Model
import ru.maksonic.beresta.feature.notes_list.ui.core.Msg
import ru.maksonic.beresta.feature.notes_list.ui.widget.NotesFilterChips
import ru.maksonic.beresta.feature.notes_list.ui.widget.note.NoteItem
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
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
    model: Model,
    send: (Msg) -> Unit,
    notes: NotesCollection,
    filters: FilterChipsCollection,
    mutableSharedNotesState: MutableStateFlow<NotesSharedState>,
    modifier: Modifier = Modifier
) {
    val notesScrollState = rememberLazyListState()
    val firstVisibleNote = notesScrollState.isVisibleFirstItem()
    val isScrollUp = notesScrollState.isScrollUp()
    val isScrolledEnd = notesScrollState.isScrolledEnd()

    LaunchedEffect(notesScrollState) {
        snapshotFlow { notesScrollState.firstVisibleItemIndex }.map { index -> index == 0 }
            .distinctUntilChanged().collect { isColoredTopBar ->
                mutableSharedNotesState.isColoredMainTopBar(isColoredTopBar)
            }
    }

    LaunchedEffect(model.isSelectionState) {
        mutableSharedNotesState.isVisibleBottomPanel(true)
    }

    LaunchedEffect(isScrollUp) {
        mutableSharedNotesState.isVisibleMainTopBar(isScrollUp)
        if (model.isSelectionState) {
            mutableSharedNotesState.isVisibleBottomPanel(true)
        } else {
            if (isScrolledEnd) {
                mutableSharedNotesState.isVisibleBottomPanel(true)
            } else {
                mutableSharedNotesState.isVisibleBottomPanel(isScrollUp)
            }
        }
    }

    LaunchedEffect(isScrolledEnd) {
        mutableSharedNotesState.isVisibleBottomPanel(true)
    }

    OverscrollBehavior {
        Box(
            modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            val bottomPaddingHeight = animateDpAsState(
                targetValue = if (model.isSelectionState) {
                    Theme.widgetSize.bottomPanelHeightSelected.plus(dp12)
                } else {
                    Theme.widgetSize.bottomPanelHeightIdle.plus(dp12)
                }
            )

            LazyColumn(
                state = notesScrollState,
                modifier = modifier.padding(top = Theme.widgetSize.topBarNormalHeight),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Box(
                        modifier
                            .fillMaxWidth()
                            .height(Theme.widgetSize.topBarNormalHeight)
                    )
                }

                items(items = notes.notes, key = { note -> note.id }) { note ->
                    NoteItem(
                        send = send, note = note, modifier = modifier.animateItemPlacement()
                    )
                }
                item {
                    Spacer(
                        modifier
                            .fillMaxWidth()
                            .height(bottomPaddingHeight.value)
                    )
                }
            }
            Header(
                filters = filters,
                isScrollUp = { isScrollUp },
                firstVisibleNote = { firstVisibleNote.value },
                modifier = modifier
            )
        }
    }
}

@Composable
private fun Header(
    filters: FilterChipsCollection,
    isScrollUp: () -> Boolean,
    firstVisibleNote: () -> Boolean,
    modifier: Modifier
) {
    val height = animateDpAsState(
        targetValue = if (isScrollUp()) Theme.widgetSize.topBarNormalHeight else 0.dp
    )

    Column {
        val backgroundColor = animateColorAsState(
            targetValue = if (firstVisibleNote()) background
            else tertiaryContainer
        )

        Box(
            modifier
                .fillMaxWidth()
                .height(height.value)
                .drawBehind { drawRect(backgroundColor.value) })
        NotesFilterChips(filters = filters, isVisibleFirstNote = { firstVisibleNote() })
    }
}
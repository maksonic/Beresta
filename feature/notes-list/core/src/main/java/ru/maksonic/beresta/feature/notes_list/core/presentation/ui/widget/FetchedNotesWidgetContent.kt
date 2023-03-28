package ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.notes_list.api.NotesListSharedScrollState
import ru.maksonic.beresta.feature.notes_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.filter.FilterChipsWidget
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp10
import ru.maksonic.beresta.ui.widget.functional.ANIMATION_DURATION_NORMAL

/**
 * @Author maksonic on 22.02.2023
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun FetchedNotesWidgetContent(
    modifier: Modifier = Modifier,
    notes: NoteUi.Collection,
    chips: FilterChipUi.Collection,
    onNoteClicked: (id: Long) -> Unit,
    onNoteLongPressed: (id: Long) -> Unit,
    onChipFilterClicked: (id: Int) -> Unit,
    sharedScroll: NotesListSharedScrollState
) {
    val topBarNormalHeight = Theme.widgetSize.topBarNormalHeight
    val chipsTransition = animateDpAsState(
        if (sharedScroll.isVisibleFirstNote()) 0.dp
        else if (sharedScroll.isScrollUp()) 0.dp else -topBarNormalHeight,
        animationSpec = tween(ANIMATION_DURATION_NORMAL)
    )

    Box(modifier = modifier.fillMaxSize()) {

        LazyVerticalStaggeredGrid(
            state = sharedScroll.state(),
            columns = StaggeredGridCells.Fixed(sharedScroll.gridCellsCount()),
            contentPadding = PaddingValues(all = dp10),
            modifier = modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = topBarNormalHeight)
        ) {

            items(sharedScroll.gridCellsCount()) {
                Box(modifier.height(Theme.widgetSize.noteChipsContainerHeight))
            }

            items(items = notes.data, key = { note -> note.id }) { note ->
                NoteListItemContent(
                    onNoteClicked = { id -> onNoteClicked(id) },
                    onNoteLongClicked = { id -> onNoteLongPressed(id) },
                    note = note,
                    modifier = modifier.animateContentSize()
                )
            }

            items(sharedScroll.gridCellsCount()) {
                val listBottomPadding = animateDpAsState(
                    if (sharedScroll.isSelectionState())
                        Theme.widgetSize.bottomPanelHeightSelected
                    else
                        Theme.widgetSize.bottomMainPanelHeight
                )

                Box(
                    modifier.height(listBottomPadding.value)
                )
            }
        }

        FilterChipsWidget(
            chipsCollection = chips,
            isVisibleFirstNote = sharedScroll.isVisibleFirstNoteOffset,
            onChipFilterClicked = onChipFilterClicked,
            modifier = modifier.graphicsLayer {
                translationY = chipsTransition.value.toPx()
            }
        )
    }

    if (notes.data.isEmpty()) {
        val searchBarContainerHeight = Theme.widgetSize.topBarNormalHeight
        val chipsContainerHeight = Theme.widgetSize.noteChipsContainerHeight
        val emptyNotesWidgetPadding = searchBarContainerHeight + chipsContainerHeight

        Box(
            modifier
                .statusBarsPadding()
                .padding(top = emptyNotesWidgetPadding)
        ) {
            EmptyNotesWidgetContent()
        }
    }
}
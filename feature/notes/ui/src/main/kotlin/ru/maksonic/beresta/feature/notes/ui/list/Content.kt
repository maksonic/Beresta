package ru.maksonic.beresta.feature.notes.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.NotesListUiState
import ru.maksonic.beresta.feature.notes.api.ui.SharedNotesUiState
import ru.maksonic.beresta.feature.notes.api.ui.updateScroll
import ru.maksonic.beresta.feature.notes.ui.NotesFilterUpdater
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp10
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.widget.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior

/**
 * @Author maksonic on 03.07.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Content(
    state: NotesListUiState,
    updater: NotesFilterUpdater,
    noteCard: NotesApi.Ui.Card,
    sharedUiState: SharedUiState<SharedNotesUiState>,
    onNoteClicked: (id: Long) -> Unit,
    onNoteLongClicked: (id: Long) -> Unit,
    chipsRowOffsetHeightPx: MutableState<Float>,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyStaggeredGridState()
    val scrollOffset = remember { mutableFloatStateOf(0f) }
    val isInitialScroll = remember {
        derivedStateOf { !scrollState.canScrollBackward && !scrollState.isScrollInProgress }
    }
    val isBottomScroll = remember {
        derivedStateOf { !scrollState.canScrollForward && !scrollState.isScrollInProgress }
    }
    val chipsHeight = Theme.widgetSize.topBarNormalHeight
    val chipsRowHeightPx = with(LocalDensity.current) { (chipsHeight).roundToPx().toFloat() }

    val scrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                scrollOffset.floatValue = delta
                val newOffset = scrollOffset.floatValue + delta
                val newChipsOffset = chipsRowOffsetHeightPx.value + delta

                if (scrollState.canScrollBackward) {
                    chipsRowOffsetHeightPx.value = newChipsOffset.coerceIn(-chipsRowHeightPx, 0f)
                } else {
                    chipsRowOffsetHeightPx.value = 0f
                }

                sharedUiState.updateScroll(newOffset > scrollOffset.floatValue)

                return Offset.Zero
            }
        }
    }

    LaunchedEffect(scrollState.canScrollBackward) {
        sharedUiState.update { it.copy(canScrollBackward = scrollState.canScrollBackward) }
    }

    LaunchedEffect(isInitialScroll.value) {
        if (isInitialScroll.value) sharedUiState.updateScroll(true)
    }

    LaunchedEffect(isBottomScroll.value) {
        if (isBottomScroll.value) sharedUiState.updateScroll(true)
    }

    Box(
        modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = Theme.widgetSize.topBarNormalHeight)
            .nestedScroll(scrollConnection)
    ) {
        OverscrollBehavior {
            val order = rememberUpdatedState(listUiSortState.order)
            val sort = rememberUpdatedState(listUiSortState.sort)
            val isSortPinned = rememberUpdatedState(listUiSortState.isSortPinned)

            LazyVerticalStaggeredGrid(
                state = scrollState,
                columns = StaggeredGridCells.Fixed(listUiSortState.gridCount),
                contentPadding = PaddingValues(
                    top = Theme.widgetSize.noteChipsContainerHeight.plus(dp4),
                    start = dp10,
                    end = dp10,
                    bottom = Theme.widgetSize.bottomMainBarHeight.plus(SystemNavigationBarHeight)
                ),
                modifier = modifier
                    .fillMaxSize()
                    .statusBarsPadding()
            ) {
                itemsIndexed(
                    items = updater.list(order.value, sort.value, isSortPinned.value),
                    key = { index, item -> if (index == 0) index else item.id }) { _, note ->
                    noteCard.Widget(
                        note = note,
                        isSelected = state.selectedList.contains(note),
                        onNoteClicked = onNoteClicked,
                        onNoteLongClicked = onNoteLongClicked,
                        modifier = modifier.animateItemPlacement()
                    )
                }
            }
        }
    }
}
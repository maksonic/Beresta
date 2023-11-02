package ru.maksonic.beresta.feature.notes_list.ui.core

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import ru.maksonic.beresta.common.core.ui.sorting.FilterDataSorter
import ru.maksonic.beresta.common.ui_kit.animation.OverscrollBehavior
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper

/**
 * @Author maksonic on 01.10.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Content(
    isPrimary: Boolean,
    sorter: State<FilterDataSorter<NoteUi>>,
    gridCells: Int,
    onNoteClicked: (id: Long) -> Unit,
    onNoteLongClicked: (id: Long) -> Unit,
    updateScrollUpValue: (Boolean) -> Unit,
    updateCanScrollBackwardValue: (Boolean) -> Unit,
    updateChipsOffset: (Float) -> Unit,
    chipsOffset: State<Float>,
    modifier: Modifier,
    contentPadding: PaddingValues,
    cardBackground: @Composable (wallpaper: BaseWallpaper<Color>) -> Unit
) {
    val scrollState = rememberLazyStaggeredGridState()
    val scrollOffset = remember { mutableFloatStateOf(0f) }
    val isInitialScroll = remember {
        derivedStateOf { !scrollState.canScrollBackward && !scrollState.isScrollInProgress }
    }
    val isBottomScroll = remember {
        derivedStateOf { !scrollState.canScrollForward && !scrollState.isScrollInProgress }
    }
    val chipsHeight = Theme.size.topBarSmallHeight
    val chipsHeightPx = with(LocalDensity.current) { (chipsHeight).roundToPx().toFloat() }
    val scrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                scrollOffset.floatValue = delta
                val newOffset = scrollOffset.floatValue + delta

                if (isPrimary) {
                    val newChipsOffset = chipsOffset.value + delta

                    if (scrollState.canScrollBackward) {
                        updateChipsOffset(newChipsOffset.coerceIn(-chipsHeightPx, 0f))
                    } else {
                        updateChipsOffset(0f)
                    }
                }
                updateScrollUpValue(newOffset > scrollOffset.floatValue)

                return Offset.Zero
            }
        }
    }

    LaunchedEffect(scrollState.canScrollBackward) {
        updateCanScrollBackwardValue(scrollState.canScrollBackward)
    }

    LaunchedEffect(isInitialScroll.value) {
        if (isInitialScroll.value) updateScrollUpValue(true)
    }

    LaunchedEffect(isBottomScroll.value) {
        if (isBottomScroll.value) updateScrollUpValue(true)
    }

    OverscrollBehavior {
        LazyVerticalStaggeredGrid(
            state = scrollState,
            columns = StaggeredGridCells.Fixed(gridCells),
            contentPadding = contentPadding,
            modifier = modifier
                .fillMaxSize()
                .nestedScroll(scrollConnection)
        ) {
            itemsIndexed(
                items = sorter.value.sortedByFilterList,
                key = { index, item -> if (index == 0) index else item.id }) { _, note ->

                CardContent(
                    note = note,
                    onNoteClicked = onNoteClicked,
                    onNoteLongClicked = onNoteLongClicked,
                    modifier = Modifier.animateItemPlacement(tween(Theme.animVelocity.common)),
                    cardBackground = cardBackground
                )
            }
        }
    }
}
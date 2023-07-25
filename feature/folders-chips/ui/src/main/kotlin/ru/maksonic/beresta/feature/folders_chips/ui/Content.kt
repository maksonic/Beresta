package ru.maksonic.beresta.feature.folders_chips.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.maksonic.beresta.feature.folders_chips.api.ui.ChipFeature
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FoldersSorter
import ru.maksonic.beresta.feature.folders_chips.ui.widget.AddNewChipButton
import ru.maksonic.beresta.feature.sorting_sheet.api.LocalListSortState
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.functional.animation.rowFadingEdge
import ru.maksonic.beresta.ui.widget.surface.SurfacePro
import kotlin.math.roundToInt

/**
 * @Author maksonic on 03.07.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Content(
    isColoredBackground: State<Boolean>,
    chips: FolderUi.Collection,
    chipsRowOffsetHeightPx: State<Float>,
    onAddNewChipClicked: () -> Unit,
    updateCurrentSelectedFolder: (Long) -> Unit,
    modifier: Modifier = Modifier,
    listSortUiState: SortingSheetApi.Ui
) {
    val listSortState = listSortUiState.state.state.collectAsStateWithLifecycle()
    val tonal = animateDp(if (isColoredBackground.value) Theme.tonal.Level2 else Theme.tonal.Level0)

    SurfacePro(
        tonalElevation = tonal.value,
        modifier = modifier
            .padding(top = Theme.widgetSize.topBarSmallHeight)
            .height(Theme.widgetSize.noteChipsContainerHeight)
            .offset { IntOffset(x = 0, y = chipsRowOffsetHeightPx.value.roundToInt()) }
    ) { resultColor ->
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(start = dp16),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val lazyRowState = rememberLazyListState()
                val currentSelectedId = rememberUpdatedState(ChipFeature.currentSelectedFolder)
                OverscrollBehavior {
                    CompositionLocalProvider(LocalListSortState provides listSortState.value) {
                        val chipsSorter = rememberUpdatedState(
                            FoldersSorter(
                                list = chips.data,
                                order = listUiSortState.folders.order,
                                isSortPinned = listUiSortState.folders.isSortPinned,
                                sort = listUiSortState.folders.sort
                            )
                        )

                        LaunchedEffect(currentSelectedId.value) {
                            lazyRowState.autoScrollToCurrentChip(
                                chipsSorter.value.sortedList, currentSelectedId.value
                            )
                        }

                        LazyRow(
                            state = lazyRowState,
                            modifier = modifier
                                .weight(1f, false)
                                .rowFadingEdge(
                                    startEdgeInitialColor = resultColor,
                                    isVisibleStartEdge = lazyRowState.canScrollBackward,
                                    isVisibleEndEdge = lazyRowState.canScrollForward,
                                ),
                            horizontalArrangement = Arrangement.spacedBy(dp8),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            items(
                                items = chipsSorter.value.sortedList,
                                key = { chip -> chip.id }) { item ->
                                ChipItem(
                                    item = item,
                                    isSelected = item.id == currentSelectedId.value,
                                    onChipClicked = { updateCurrentSelectedFolder(it) },
                                    modifier = modifier.animateItemPlacement()
                                )
                            }
                        }
                    }
                }
                AddNewChipButton(
                    onClick = onAddNewChipClicked,
                    modifier = modifier.padding(start = dp12, end = dp16)
                )
            }
        }
    }
}

private suspend fun LazyListState.autoScrollToCurrentChip(chips: List<FolderUi>, id: Long) {
    val currentIndex = chips.indexOf(chips.find { it.id == id })
    val targetIndex = if (currentIndex <= 0) 0 else currentIndex - 1
    this.animateScrollToItem(targetIndex)
}
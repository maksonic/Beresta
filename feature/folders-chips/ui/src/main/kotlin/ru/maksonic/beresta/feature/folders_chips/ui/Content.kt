package ru.maksonic.beresta.feature.folders_chips.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import ru.maksonic.beresta.feature.folders_chips.api.ui.ChipFeature
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FoldersSorter
import ru.maksonic.beresta.feature.folders_chips.ui.widget.AddNewChipButton
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp24
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior

/**
 * @Author maksonic on 03.07.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Content(
    chips: FolderUi.Collection,
    addNewChipBackgroundColor: State<Color>,
    onAddNewChipClicked: () -> Unit,
    updateCurrentSelectedFolder: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyRowState = rememberLazyListState()
    val currentSelectedId = rememberUpdatedState(ChipFeature.currentSelectedFolder)
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

    ConstraintLayout(
        modifier
            .fillMaxSize()
            .padding(end = dp32)
    ) {
        val (chipsRow, addBtn) = createRefs()

        OverscrollBehavior {
            LazyRow(
                state = lazyRowState,
                horizontalArrangement = Arrangement.spacedBy(dp8),
                contentPadding = PaddingValues(start = dp16, end = dp24),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.constrainAs(chipsRow) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
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

        AddNewChipButton(
            onClick = onAddNewChipClicked,
            color = addNewChipBackgroundColor,
            modifier = modifier.constrainAs(addBtn) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(chipsRow.end)
                end.linkTo(chipsRow.end)
            }
        )
    }
}

private suspend fun LazyListState.autoScrollToCurrentChip(chips: List<FolderUi>, id: Long) {
    val currentIndex = chips.indexOf(chips.find { it.id == id })
    val targetIndex = if (currentIndex <= 0) 0 else currentIndex - 1
    this.animateScrollToItem(targetIndex)
}
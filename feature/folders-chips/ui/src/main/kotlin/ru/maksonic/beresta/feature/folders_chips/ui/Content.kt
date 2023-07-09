package ru.maksonic.beresta.feature.folders_chips.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.notes.api.ui.SharedNotesUiState
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
    chips: FolderUi.Collection,
    currentSelectedId: MutableState<Long>,
    chipsRowOffsetHeightPx: MutableState<Float>,
    onAddNewChipClicked: () -> Unit,
    modifier: Modifier = Modifier,
    notesSharedUiState: State<SharedNotesUiState>
) {
    val tonal = animateDp(
        if (!notesSharedUiState.value.canScrollBackward) Theme.tonal.Level0 else Theme.tonal.Level2
    )

    SurfacePro(
        tonalElevation = tonal.value,
        modifier = modifier.offset {
            IntOffset(x = 0, y = chipsRowOffsetHeightPx.value.roundToInt())
        }
    ) { resultColor ->
        Row(
            modifier
                .fillMaxWidth()
                .padding(top = Theme.widgetSize.topBarNormalHeight)
                .height(Theme.widgetSize.noteChipsContainerHeight)
                .padding(start = dp16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val lazyRowState = rememberLazyListState()

            LaunchedEffect(currentSelectedId.value) {
                lazyRowState.autoScrollToCurrentChip(chips.data, currentSelectedId.value)
            }

            OverscrollBehavior {
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
                        items = chips.data,
                        key = { chip -> chip.id }) { item ->
                        ChipItem(
                            item = item,
                            isSelected = item.id == currentSelectedId.value,
                            onChipClicked = { currentSelectedId.value = it },
                            modifier = modifier.animateItemPlacement()
                        )
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

private suspend fun LazyListState.autoScrollToCurrentChip(chips: List<FolderUi>, id: Long) {
    val currentIndex = chips.indexOf(chips.find { it.id == id })
    val targetIndex = if (currentIndex <= 0) 0 else currentIndex - 1
    this.animateScrollToItem(targetIndex)
}
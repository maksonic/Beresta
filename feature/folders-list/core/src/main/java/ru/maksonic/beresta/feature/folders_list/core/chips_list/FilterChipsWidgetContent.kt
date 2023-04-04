package ru.maksonic.beresta.feature.folders_list.core.chips_list

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.theme.component.*
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior
import ru.maksonic.beresta.ui.widget.functional.animation.rowFadingEdge
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItemOffset
import ru.maksonic.beresta.ui.widget.functional.isVisibleLastItemOffset

/**
 * @Author maksonic on 30.03.2023
 */
@Composable
internal fun FilterChipsWidgetContent(
    chipsCollection: FilterChipUi.Collection,
    currentSelectedChipId: Long,
    isVisibleFirstNote: () -> Boolean,
    onChipFilterClicked: (id: Long) -> Unit,
    onAddNewFilterFolderClicked: () -> Unit,
    modifier: Modifier
) {
    val tonal =
        animateDpAsState(if (isVisibleFirstNote()) Theme.tonal.Level0 else Theme.tonal.Level4)

    SurfacePro(tonalElevation = tonal.value, modifier = modifier) { resultColor ->

        Column(Modifier.fillMaxWidth()) {
            val lazyRowState = rememberLazyListState()
            val isVisibleFirstChipOffset = lazyRowState.isVisibleFirstItemOffset()
            val isVisibleLastChipOffset = lazyRowState.isVisibleLastItemOffset()

            SystemStatusBar()
            Box(modifier.height(Theme.widgetSize.topBarNormalHeight))

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(Theme.widgetSize.noteChipsContainerHeight)
                    .padding(start = dp16),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OverscrollBehavior {
                    LazyRow(
                        state = lazyRowState,
                        modifier = Modifier
                            .weight(1f, false)
                            .rowFadingEdge(
                                startEdgeInitialColor = resultColor,
                                isVisibleStartEdge = !isVisibleFirstChipOffset,
                                isVisibleEndEdge = !isVisibleLastChipOffset,
                            ),
                        horizontalArrangement = Arrangement.spacedBy(dp8),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        items(chipsCollection.data, key = { chip -> chip.id }) { item ->
                            val update = item.copy(isSelected = item.id == currentSelectedChipId)
                            FilterChipItem(update, onChipFilterClicked)
                        }
                    }
                    AddNewFilterButton(
                        modifier = Modifier.padding(start = dp12, end = dp16),
                        onClick = onAddNewFilterFolderClicked
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterChipItem(
    item: FilterChipUi,
    onChipFilterClicked: (id: Long) -> Unit,
) {
    FilterChip(
        selected = item.isSelected,
        onClick = { onChipFilterClicked(item.id) },
        label = { Text(item.title) },
        shape = Shape.cornerRound,
        border = FilterChipDefaults.filterChipBorder(borderColor = outline),
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = tertiaryContainer,
            labelColor = onBackground,
            selectedLabelColor = onTertiaryContainer
        ),
    )
}

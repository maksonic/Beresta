package ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.filter

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes_list.api.ui.FilterChipUi
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior
import ru.maksonic.beresta.ui.widget.functional.animation.rowFadingEdge
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItemOffset
import ru.maksonic.beresta.ui.widget.functional.isVisibleLastItemOffset

/**
 * @Author maksonic on 02.03.2023
 */
@Composable
internal fun FilterChipsWidget(
    chipsCollection: FilterChipUi.Collection,
    isVisibleFirstNote: () -> Boolean,
    onChipFilterClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier
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

                            FilterChipItem(item, onChipFilterClicked)
                        }
                    }
                    AddNewFilterButton(modifier = Modifier.padding(start = dp12, end = dp16))
                }
            }
        }
    }
}
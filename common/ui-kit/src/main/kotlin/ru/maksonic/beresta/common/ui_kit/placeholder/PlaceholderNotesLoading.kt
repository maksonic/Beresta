package ru.maksonic.beresta.common.ui_kit.placeholder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_kit.placeholder.base.PlaceholderBaseLoadingContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp12
import ru.maksonic.beresta.common.ui_theme.provide.dp16

/**
 * @Author maksonic on 15.10.2023
 */
private const val PLACEHOLDERS_COUNT = 15
private val dpsData = listOf(140.dp, 120.dp, 100.dp, 80.dp)


@Composable
fun PlaceholderNotesLoading(gridCellsCount: Int, cardShape: Dp, modifier: Modifier) {
    val itemsHeight = remember { mutableStateOf(dpsData) }

    PlaceholderBaseLoadingContainer(
        placeholdersCount = PLACEHOLDERS_COUNT,
        modifier = modifier.padding(start = dp16, end = dp16)
    ) { animateColor ->

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(gridCellsCount),
            userScrollEnabled = false
        ) {

            items(PLACEHOLDERS_COUNT) {
                Box(
                    Modifier
                        .padding(bottom = dp12)
                        .height(itemsHeight.value.random())
                        .clip(RoundedCornerShape(cardShape))
                        .drawBehind { drawRect(animateColor.value) })
            }
        }
    }
}
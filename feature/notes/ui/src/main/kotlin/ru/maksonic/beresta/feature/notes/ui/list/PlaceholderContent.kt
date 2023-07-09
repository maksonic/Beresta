package ru.maksonic.beresta.feature.notes.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.component.dp10
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.widget.functional.animation.PlaceholderListWidget

/**
 * @Author maksonic on 25.04.2023
 */
private const val PLACEHOLDERS_COUNT = 15
private val dpsData = listOf(140.dp, 120.dp, 100.dp, 80.dp)

@Composable
internal fun PlaceholderContent(gridCellsCount: Int, modifier: Modifier = Modifier) {
    val dps = remember { mutableStateOf(dpsData) }

    PlaceholderListWidget(
        placeholdersCount = PLACEHOLDERS_COUNT,
        modifier = modifier.fillMaxSize()
    ) { animateColor ->

        LazyVerticalStaggeredGrid(
            modifier = modifier.fillMaxWidth(),
            columns = StaggeredGridCells.Fixed(gridCellsCount),
            contentPadding = PaddingValues(start = dp10, end = dp10),
            userScrollEnabled = false
        ) {

            items(PLACEHOLDERS_COUNT) {
                Box(
                    modifier
                        .padding(start = dp6, end = dp6, top = dp12)
                        .height(dps.value.random())
                        .clip(RoundedCornerShape(dp16))
                        .drawBehind { drawRect(animateColor.value) })
            }

        }
    }
}
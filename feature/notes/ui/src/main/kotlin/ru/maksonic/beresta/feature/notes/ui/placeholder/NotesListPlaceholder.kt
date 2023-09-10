package ru.maksonic.beresta.feature.notes.ui.placeholder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.LocalNoteCardState
import ru.maksonic.beresta.feature.notes.api.ui.noteUiCardState
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.widget.functional.animation.PlaceholderListWidget

/**
 * @Author maksonic on 10.09.2023
 */
private const val PLACEHOLDERS_COUNT = 15
private val dpsData = listOf(140.dp, 120.dp, 100.dp, 80.dp)

class NotesListPlaceholder : NotesApi.ListPlaceholder.Ui {
    @Composable
    override fun Placeholder(gridCellsCount: Int, modifier: Modifier) {
        PlaceholderContent(gridCellsCount, modifier)
    }
}

@Composable
internal fun PlaceholderContent(
    gridCellsCount: Int,
    modifier: Modifier,
    noteCardApi: NotesApi.Card.Ui = koinInject()
) {
    val itemsHeight = remember { mutableStateOf(dpsData) }

    PlaceholderListWidget(
        placeholdersCount = PLACEHOLDERS_COUNT,
        modifier = modifier
    ) { animateColor ->

        CompositionLocalProvider(LocalNoteCardState provides noteCardApi.sharedState.value) {
            LazyVerticalStaggeredGrid(
                modifier = Modifier.fillMaxWidth(),
                columns = StaggeredGridCells.Fixed(gridCellsCount),
                userScrollEnabled = false
            ) {

                items(PLACEHOLDERS_COUNT) {
                    Box(
                        Modifier
                            .padding(start = dp6, end = dp6, bottom = dp12)
                            .height(itemsHeight.value.random())
                            .clip(RoundedCornerShape(noteUiCardState.shape.dp))
                            .drawBehind { drawRect(animateColor.value) })
                }
            }
        }
    }
}
package ru.maksonic.beresta.feature.notes_list.ui.state

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.core.Feature
import ru.maksonic.beresta.feature.notes_list.ui.widget.NoteItem
import ru.maksonic.beresta.feature.notes_list.ui.widget.NotesFilterChips
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp24

/**
 * @Author maksonic on 25.12.2022
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SuccessViewState(
    notes: List<NoteUi>,
    notesScrollState: LazyListState,
    filters: List<NoteUi.Filter>,
    onFilterClick: (Int) -> Unit,
    msg: (Feature.Msg) -> Unit,
    isSelectionState: Boolean,
    isScrolledTop: () -> Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier
                .fillMaxWidth()
                .padding(top = Theme.widgetSize.topBarNormalHeight),
            state = notesScrollState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /* stickyHeader {
            NotesFilterChips(
                filters = filters,
                isScrolledTop = isScrolledTop
            )
        }*/

            items(notes) { note ->
                NoteItem(
                    note = note,
                    msg = msg,
                    isSelectionState = isSelectionState,
                )

            }
            item {
                val dp = if (isSelectionState)
                    Theme.widgetSize.bottomPanelHeightSelected
                else
                    Theme.widgetSize.bottomPanelHeightIdle
                val spaceHeight = animateDpAsState(targetValue = dp)
                Spacer(modifier.height(spaceHeight.value))
            }
        }
    }
    NotesFilterChips(
        filters = filters,
        isScrolledTop = isScrolledTop
    )
}

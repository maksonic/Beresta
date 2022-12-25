package ru.maksonic.beresta.feature.notes_list.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.core.Feature
import ru.maksonic.beresta.feature.notes_list.ui.widget.NoteItem
import ru.maksonic.beresta.feature.notes_list.ui.widget.NotesFilterChips
import ru.maksonic.beresta.ui.theme.Theme

/**
 * @Author maksonic on 25.12.2022
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SuccessViewState(
    notes: List<NoteUi>,
    msg: (Feature.Msg) -> Unit,
    isSelectionState: Boolean,
    lazyListState: LazyListState,
    firstVisibleItem: State<Int>,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier
            .systemBarsPadding()
            .fillMaxSize(),
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            NotesFilterChips(firstVisibleItem.value > 0)
        }

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
package ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.get
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.folders_list.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesListSharedScrollState
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp10
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.widget.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.functional.ANIMATION_DURATION_NORMAL

/**
 * @Author maksonic on 22.02.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun FetchedNotesWidgetContent(
    modifier: Modifier = Modifier,
    notes: NoteUi.Collection,
    chips: FilterChipUi.Collection,
    selectedNotes: Set<NoteUi>,
    currentSelectedChipId: Long,
    onNoteClicked: (id: Long) -> Unit,
    onNoteLongPressed: (id: Long) -> Unit,
    onChipFilterClicked: (id: Long) -> Unit,
    sharedScroll: NotesListSharedScrollState,
    maxTitleLength: Int,
    maxMessageLength: Int,
    filters: FoldersListApi.Ui = get()
) {
    val topBarNormalHeight = Theme.widgetSize.topBarNormalHeight
    val chipsTransition = animateDpAsState(
        if (sharedScroll.isVisibleFirstNote.value) 0.dp
        else if (sharedScroll.isScrollUp.value) 0.dp else -topBarNormalHeight,
        animationSpec = tween(ANIMATION_DURATION_NORMAL)
    )

    val listBottomPadding = animateDpAsState(
        if (sharedScroll.isSelectionState.value)
            Theme.widgetSize.bottomPanelHeightSelected.plus(SystemNavigationBarHeight)
        else
            Theme.widgetSize.bottomMainPanelHeight.plus(SystemNavigationBarHeight)
    )

    Box(modifier = modifier.fillMaxSize()) {

        LazyVerticalStaggeredGrid(
            state = sharedScroll.state(),
            columns = StaggeredGridCells.Fixed(sharedScroll.gridCellsCount.value),
            contentPadding = PaddingValues(
                top = Theme.widgetSize.noteChipsContainerHeight.plus(dp4),
                start = dp10,
                end = dp10,
                bottom = listBottomPadding.value
            ),
            modifier = modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = topBarNormalHeight)
        ) {

            items(items = notes.data, key = { note -> note.id }) { note ->
                NoteListItemContent(
                    selectedNotes = selectedNotes,
                    note = note,
                    onNoteClicked = { id -> onNoteClicked(id) },
                    onNoteLongClicked = { id -> onNoteLongPressed(id) },
                    maxTitleLength = maxTitleLength,
                    maxMessageLength = maxMessageLength,
                    modifier = modifier.animateContentSize()
                )
            }
        }

        filters.FolderChipsWidget(
            chipsCollection = chips,
            currentSelectedChipId = currentSelectedChipId,
            isVisibleFirstNote = sharedScroll.isVisibleFirstNoteOffset,
            onChipFilterClicked = onChipFilterClicked,
            modifier = modifier.graphicsLayer {
                translationY = chipsTransition.value.toPx()
            }
        )
    }

    if (notes.data.isEmpty()) {
        EmptyNotesWidgetContent()
    }
}
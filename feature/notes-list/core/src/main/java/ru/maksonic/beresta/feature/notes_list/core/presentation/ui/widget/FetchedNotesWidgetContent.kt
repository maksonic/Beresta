package ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.core.presentation.Msg
import ru.maksonic.beresta.feature.notes_list.core.presentation.NotesListSandbox
import ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.filter.FilterChipsWidget
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.widget.functional.isScrollUp
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItem

/**
 * @Author maksonic on 22.02.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun FetchedNotesWidgetContent(
    modifier: Modifier = Modifier,
    notes: NoteUi.Collection,
    scrollState: () -> LazyListState,
    sandbox: NotesListSandbox = koinViewModel(),
) {
    LaunchedEffect(Unit) {
        /**
         * Fetched notes from MainSandbox are passed here and apply to:
         * @see [ru.maksonic.beresta.feature.notes_list.core.presentation.Model.notes]
         * */
        sandbox.sendMsg(Msg.Inner.FetchedNotesCollection(notes))
    }

    val model = sandbox.model.collectAsState().value
    val isVisibleFirstNote = scrollState().isVisibleFirstItem()
    val isScrollUp = scrollState().isScrollUp()
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val topBarMediumHeight = Theme.widgetSize.topBarMediumHeight
    val chipsTrans = animateDpAsState(
        if (isVisibleFirstNote.value) 0.dp else if (isScrollUp) 0.dp else -topBarMediumHeight,
        animationSpec = tween()
    )

    Box(modifier = modifier.fillMaxSize()) {

        LazyColumn(
            state = scrollState(),
            modifier = modifier.padding(top = topBarMediumHeight),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(
                    modifier.height(
                        Theme.widgetSize.topBarNormalHeight.plus(statusBarHeight).plus(dp12)
                    )
                )
            }
            items(items = model.notes.data, key = { note -> note.id }) { note ->
                NoteListItemContent(
                    onNoteClicked = {},
                    onNoteLongClicked = {},
                    note = note,
                    modifier = modifier.animateItemPlacement()
                )
            }

            item {
                Spacer(
                    modifier
                        .navigationBarsPadding()
                        .height(Theme.widgetSize.bottomMainPanelHeight)
                )
            }
        }
        FilterChipsWidget(
            chipsCollection = model.filters,
            isVisibleFirstNote = { isVisibleFirstNote.value },
            send = sandbox::sendMsg,
            modifier = modifier.graphicsLayer {
                translationY = chipsTrans.value.toPx()
            }
        )
    }
}
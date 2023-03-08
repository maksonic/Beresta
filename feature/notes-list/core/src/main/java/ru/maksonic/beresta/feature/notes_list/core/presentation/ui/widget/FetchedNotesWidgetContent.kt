package ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.core.presentation.Eff
import ru.maksonic.beresta.feature.notes_list.core.presentation.Msg
import ru.maksonic.beresta.feature.notes_list.core.presentation.NotesListSandbox
import ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.filter.FilterChipsWidget
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
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
    router: MainScreenRouter,
    sandbox: NotesListSandbox = koinViewModel(),
) {
    HandleEffects(sandbox.effects, router)

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
    val topBarNormalHeight = Theme.widgetSize.topBarNormalHeight
    val chipsTransition = animateDpAsState(
        if (isVisibleFirstNote.value) 0.dp else if (isScrollUp) 0.dp else -topBarNormalHeight,
        animationSpec = tween()
    )

    Box(modifier = modifier.fillMaxSize()) {

        LazyColumn(
            state = scrollState(),
            modifier = modifier.padding(top = Theme.widgetSize.noteChipsContainerHeight),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(
                    modifier.height(
                        Theme.widgetSize.noteChipsContainerHeight.plus(statusBarHeight)
                    )
                )
            }
            items(items = model.notes.data, key = { note -> note.id }) { note ->
                NoteListItemContent(
                    onNoteClicked = { id -> sandbox.sendMsg(Msg.Ui.OnNoteClicked(id)) },
                    onNoteLongClicked = { id -> sandbox.sendMsg(Msg.Ui.OnNoteLongClicked(id)) },
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
                translationY = chipsTransition.value.toPx()
            }
        )
    }
}

@Composable
private fun HandleEffects(effects: Flow<Eff>, router: MainScreenRouter) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.ShowNoteForEdit -> router.toNoteEditor(eff.id)
        }
    }
}
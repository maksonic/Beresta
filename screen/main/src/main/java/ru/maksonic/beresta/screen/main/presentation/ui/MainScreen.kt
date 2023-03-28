package ru.maksonic.beresta.screen.main.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.notes_list.api.NotesListApi
import ru.maksonic.beresta.feature.notes_list.api.NotesListSharedScrollState
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter
import ru.maksonic.beresta.screen.main.presentation.core.Eff
import ru.maksonic.beresta.screen.main.presentation.core.MainSandbox
import ru.maksonic.beresta.screen.main.presentation.core.Msg
import ru.maksonic.beresta.screen.main.presentation.ui.widget.bottom_bar.MainBottomBar
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.widget.LoadingViewState
import ru.maksonic.beresta.ui.widget.functional.*
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 15.02.2023
 */
internal typealias SendMessage = (Msg) -> Unit

private const val FAB_VISIBILITY_DURATION = 400

@Composable
fun MainScreen(router: MainScreenRouter) {
    Content(router = router)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(
    modifier: Modifier = Modifier,
    notesList: NotesListApi.Ui = get(),
    searchBar: SearchBarApi.Ui = get(),
    editNote: EditNoteApi.Ui = get(),
    sandbox: MainSandbox = koinViewModel(),
    router: MainScreenRouter
) {
    val model = sandbox.model.collectAsStateWithLifecycle().value
    val scrollState = rememberLazyStaggeredGridState()
    val isScrollUp = scrollState.isScrollUp()
    val isVisibleFirstNote = scrollState.isVisibleFirstItem()
    val isVisibleFirstNoteOffset = scrollState.isVisibleFirstItemOffset()
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val searchBarVisibility = remember { mutableStateOf(false) }
    val searchBarScrollOffset = animateDpAsState(
        if (isVisibleFirstNote.value) 0.dp else {
            if (isScrollUp) 0.dp else -Theme.widgetSize.topBarMediumHeight.plus(statusBarHeight)
        },
        animationSpec = tween(ANIMATION_DURATION_NORMAL)
    )


    HandleUiEffects(sandbox.effects, router)

    BackHandler(model.isSelectionState) {
        sandbox.send(Msg.Ui.OnCancelSelectionClicked)
    }

    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        when {
            model.base.isLoading -> LoadingViewState()
            model.base.isSuccessLoading -> {

                notesList.FetchedNotesWidget(
                    notes = model.notes,
                    chips = model.filters,
                    onNoteClicked = { noteId -> sandbox.send(Msg.Ui.OnNoteClicked(noteId)) },
                    onNoteLongPressed = { noteId -> sandbox.send(Msg.Ui.OnNoteLongPressed(noteId)) },
                    onChipFilterClicked = { id -> sandbox.send(Msg.Ui.OnChipFilterClicked(id)) },
                    sharedScroll = NotesListSharedScrollState(
                        state = { scrollState },
                        isVisibleFirstNote = { isVisibleFirstNote.value },
                        isVisibleFirstNoteOffset = { isVisibleFirstNoteOffset.value },
                        isScrollUp = { isScrollUp },
                        isSelectionState = { model.isSelectionState },
                        gridCellsCount = { model.notesGridCount }
                    )
                )
            }
        }
        MainBottomBar(
            state = model.bottomBarState,
            send = sandbox::send,
            isScrollUp = { isScrollUp },
            isSelectionState = { model.isSelectionState },
            selectedCount = { model.selectedNotesCount },
            isShowUnpinItem = model.isShowBottomBarUnpinBtn
        )

        AnimatedVisibility(
            visible = !model.isSelectionState,
            enter = slideIn(
                animationSpec = tween(FAB_VISIBILITY_DURATION),
                initialOffset = { IntOffset(0, 300) }) + fadeIn(tween(FAB_VISIBILITY_DURATION)),
            exit = slideOut(
                animationSpec = tween(FAB_VISIBILITY_DURATION),
                targetOffset = { IntOffset(0, 300) }) + fadeOut(tween(FAB_VISIBILITY_DURATION))
        ) {
            editNote.NewNoteFabWidget(isNotesScrollUp = { isScrollUp }, modifier)
        }

        AnimateFadeInOut(!searchBarVisibility.value) {
            searchBar.Widget(
                notesCollection = model.notes,
                isVisibleFirstNote = { isVisibleFirstNoteOffset.value },
                isSelectedNotesState = { model.isSelectionState },
                isScrollInProgress = { scrollState.isScrollInProgress },
                modifier = Modifier.graphicsLayer {
                    translationY = searchBarScrollOffset.value.toPx()
                }
            )
        }
    }
}

@Composable
private fun HandleUiEffects(effects: Flow<Eff>, router: MainScreenRouter) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateToAddNewNote -> router.toNoteEditor(0)
            is Eff.NavigateToSettings -> router.toSettings()
            is Eff.NavigateToTrash -> router.toTrash()
            is Eff.ShowNoteForEdit -> router.toNoteEditor(eff.id)
        }
    }
}
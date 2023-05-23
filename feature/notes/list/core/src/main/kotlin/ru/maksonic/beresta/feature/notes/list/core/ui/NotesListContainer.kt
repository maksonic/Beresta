package ru.maksonic.beresta.feature.notes.list.core.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.notes.list.api.BaseBottomBarItem
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListSharedUiState
import ru.maksonic.beresta.feature.notes.list.core.Eff
import ru.maksonic.beresta.feature.notes.list.core.Model
import ru.maksonic.beresta.feature.notes.list.core.Msg
import ru.maksonic.beresta.feature.notes.list.core.NotesListSandbox
import ru.maksonic.beresta.feature.notes.list.core.ui.widget.NotesLoaderWidget
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.feature.top_bar_counter.api.intiClickActions
import ru.maksonic.beresta.feature.top_bar_counter.api.updateCounterValue
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp10
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Lock
import ru.maksonic.beresta.ui.theme.icons.MoveFolder
import ru.maksonic.beresta.ui.theme.icons.MoveTrash
import ru.maksonic.beresta.ui.theme.icons.Pin
import ru.maksonic.beresta.ui.theme.icons.Unpin
import ru.maksonic.beresta.ui.widget.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.bar.SnackBarAction
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior
import ru.maksonic.beresta.ui.widget.functional.isScrollUp
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItem
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItemOffset

/**
 * @Author maksonic on 24.04.2023
 */
private typealias SendMessage = (Msg) -> Unit

private const val STICKY_START_FOLDER_ID = 1L
private const val STICKY_END_FOLDER_ID = 2L
private const val DEFAULT_NOTE_FOLDER_ID = 2L

@Composable
internal fun NotesListContainer(
    modifier: Modifier = Modifier,
    sandbox: NotesListSandbox = koinViewModel(),
    notesFoldersFeatureApi: FoldersListApi.Ui = get(),
    topBarCounterFeatureApi: TopBarCounterApi.Ui = get(),
    sharedUiState: SharedUiState<NotesListSharedUiState>,
    router: MainScreenRouter
) {
    val model = sandbox.model.collectAsStateWithLifecycle()
    val foldersState = notesFoldersFeatureApi.sharedUiState.state.collectAsStateWithLifecycle()

    HandleUiEffects(effects = sandbox.effects, router = router, sharedUiState = sharedUiState)

    LaunchedEffect(Unit) {
        topBarCounterFeatureApi.topBarCounterSharedUiState.intiClickActions(
            onCancelClicked = { sandbox.send(Msg.Ui.CancelSelectionState) },
            onSelectAllClicked = { sandbox.send(Msg.Ui.OnSelectAllNotesClicked) }
        )
    }

    LaunchedEffect(foldersState.value.currentFolderId) {
        sandbox.send(
            Msg.Inner.FetchedCurrentFolderIdByPassedState(foldersState.value.currentFolderId)
        )
    }

    LaunchedEffect(model.value.selectedNotes) {
        val count = if (model.value.isSelectionState) model.value.selectedNotes.count() else 1
        topBarCounterFeatureApi.topBarCounterSharedUiState.updateCounterValue(count)
    }

    Box(modifier.padding(top = Theme.widgetSize.topBarNormalHeight)) {
        when {
            model.value.base.isLoading -> NotesLoaderWidget()
            model.value.base.isSuccessLoading -> {
                NotesResultDataContent(
                    model = model.value,
                    send = sandbox::send,
                    sharedUiState = sharedUiState
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NotesResultDataContent(
    modifier: Modifier = Modifier,
    model: Model,
    send: SendMessage,
    sharedUiState: SharedUiState<NotesListSharedUiState>
) {
    val scrollState = rememberLazyStaggeredGridState()
    val isScrollUp = scrollState.isScrollUp()
    val isVisibleFirstNoteOffset = scrollState.isVisibleFirstItemOffset()
    val isVisibleFirstNote = scrollState.isVisibleFirstItem()

    val sharedBottomBarMessages = arrayOf(
        BaseBottomBarItem(
            label = text.shared.btnTitleHide,
            icon = AppIcon.Lock,
            action = { send(Msg.Ui.OnBarMoveToSecureFolderClicked) }),
        BaseBottomBarItem(
            label = if (model.isShowUnpinMainBarIcon) text.shared.btnTitleUnpin
            else text.shared.btnTitlePin,
            icon = if (model.isShowUnpinMainBarIcon) AppIcon.Unpin else AppIcon.Pin,
            action = {
                send(Msg.Ui.OnBarPinToTopOfListClicked)
                send(Msg.Ui.CancelSelectionState)
            }),
        BaseBottomBarItem(
            label = text.shared.btnTitleReplace,
            icon = AppIcon.MoveFolder,
            action = {
                send(Msg.Ui.OnBarReplaceToFolderClicked)
                send(Msg.Ui.CancelSelectionState)
            }),
        BaseBottomBarItem(
            label = text.shared.btnTitleRemove,
            icon = AppIcon.MoveTrash,
            action = { send(Msg.Ui.OnBarMoveToTrashClicked) })
    )

    BackHandler(model.isSelectionState) {
        send(Msg.Ui.CancelSelectionState)
    }

    LaunchedEffect(Unit) {
        sharedUiState.update { state ->
            state.copy(
                selectBarActions = sharedBottomBarMessages,
                onChangeGridCount = { send(Msg.Ui.OnChangeGridCountClicked) }
            )
        }
    }

    LaunchedEffect(model.isShowUnpinMainBarIcon) {
        sharedUiState.update { it.copy(selectBarActions = sharedBottomBarMessages) }
    }

    LaunchedEffect(isScrollUp.value) {
        sharedUiState.update { it.copy(isScrollUp = isScrollUp.value) }
    }

    LaunchedEffect(isVisibleFirstNoteOffset.value) {
        sharedUiState.update { it.copy(isVisibleFirstNoteOffset = isVisibleFirstNoteOffset.value) }
    }

    LaunchedEffect(isVisibleFirstNote.value) {
        sharedUiState.update { it.copy(isVisibleFirstNote = isVisibleFirstNote.value) }
    }

    Box(contentAlignment = Alignment.BottomCenter) {
        OverscrollBehavior {
            LazyVerticalStaggeredGrid(
                state = scrollState,
                columns = StaggeredGridCells.Fixed(model.gridCount),
                contentPadding = PaddingValues(
                    top = Theme.widgetSize.topBarNormalHeight.plus(dp10),
                    start = dp10,
                    end = dp10,
                    bottom = Theme.widgetSize.bottomMainBarHeight.plus(SystemNavigationBarHeight)
                ),
                modifier = modifier.fillMaxSize()
            ) {
                items(
                    items = filterNotesByCurrentFolder(
                        folderId = model.currentSelectedFolderId, notes = model.notes.data
                    ),
                    key = { note -> note.id }) { note ->
                    NoteListItemContent(
                        isSelected = model.selectedNotes.contains(note),
                        note = note,
                        onNoteClicked = { id -> send(Msg.Ui.OnNoteClicked(id)) },
                        onNoteLongClicked = { id -> send(Msg.Ui.OnNoteLongClicked(id)) },
                        currentAppLang = model.currentAppLanguage,
                        modifier = modifier.animateContentSize()
                    )
                }
            }
        }

        AnimateFadeInOut(
            filterNotesByCurrentFolder(
                model.currentSelectedFolderId,
                model.notes.data
            ).isEmpty()
        ) {
            EmptyListContent()
        }

        AnimatedVisibility(model.isVisibleRemovedSnackBar) {
            val paddingBottom = animateDpAsState(
                if (model.isSelectionState) Theme.widgetSize.bottomBarNormalHeight
                else Theme.widgetSize.bottomMainBarHeight, label = ""
            )
            SnackBarAction(
                message = text.shared.hintRemovedNotesCount.plus(
                    " ${model.removedNotes.count()}"
                ),
                actionTitle = text.shared.btnTitleCancel,
                onClick = { send(Msg.Ui.OnSnackUndoRemoveNotesClicked) },
                modifier = modifier
                    .navigationBarsPadding()
                    .padding(bottom = paddingBottom.value)
            )
        }
    }
}

private fun filterNotesByCurrentFolder(folderId: Long, notes: List<NoteUi>): List<NoteUi> {
    return notes.filter { note ->
        when (folderId) {
            // When ID == 1L - Showed all notes.
            STICKY_START_FOLDER_ID -> note.id == note.id
            // When ID == 2L - Showed notes without folder (category).
            STICKY_END_FOLDER_ID -> note.folderId == DEFAULT_NOTE_FOLDER_ID
            else -> note.folderId == folderId
        }
    }
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: MainScreenRouter,
    sharedUiState: SharedUiState<NotesListSharedUiState>
) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateToEditNote -> router.toNoteEditor(eff.id)
            is Eff.NavigateToFoldersWithMovingState -> {
                router.toFoldersList(true, eff.currentSelectedFolderId)
            }

            is Eff.UpdateSharedUiIsSelectedState -> {
                sharedUiState.update { it.copy(isSelectionState = eff.isSelectionState) }
            }

            is Eff.UpdateSharedUiIsEnabledBottomBarState -> {
                sharedUiState.update { it.copy(isEnabledBottomBar = eff.isEnabled) }
            }

            is Eff.UpdatePassedNotesSharedState -> {
                sharedUiState.update { it.copy(passedToFolderNotes = eff.notes) }
            }

        }
    }
}
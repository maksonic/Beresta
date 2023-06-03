package ru.maksonic.beresta.screen.trash_list.folders.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.trash_list.folders.core.Model
import ru.maksonic.beresta.screen.trash_list.folders.core.Msg
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.component.dp10
import ru.maksonic.beresta.ui.theme.images.AppImage
import ru.maksonic.beresta.ui.theme.images.EmptyTrash
import ru.maksonic.beresta.ui.widget.bar.TrashBottomBar
import ru.maksonic.beresta.ui.widget.dialog.TrashDialogAcceptDeleteData
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.placeholder.ScreenPlaceholder
import ru.maksonic.beresta.ui.widget.sheet.ModalBottomSheetDefault
import ru.maksonic.beresta.ui.widget.sheet.TrashDeleteModalSheetContent

/**
 * @Author maksonic on 30.05.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TrashFoldersScreenContent(
    model: State<Model>,
    send: SendMessage,
    foldersListApi: FoldersListApi.Ui,
    topBarCounterApi: TopBarCounterApi.Ui,
    modifier: Modifier = Modifier
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val scrollState = rememberLazyListState()

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Scaffold(
            topBar = {
                topBarCounterApi.LargeWidget(
                    scrollBehavior = scrollBehavior,
                    isSelectionState = model.value.isSelectionState,
                    count = model.value.selectedFolders.count(),
                    idleTitle = text.trash.topBarTitleTrashedFolders,
                    onBackClicked = { send(Msg.Ui.OnTopBarBackPressed) },
                    onCancelSelectStateClicked = { send(Msg.Ui.CancelSelectionState) },
                    onSelectAllItemsClicked = { send(Msg.Ui.OnSelectAllFoldersClicked) })
            },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->
            FoldersList(
                model = model.value,
                send = send,
                scrollState = scrollState,
                foldersListApi = foldersListApi,
                modifier = modifier.padding(paddings)
            )
        }

        TrashBottomBar(
            onRestoreClicked = { send(Msg.Ui.OnBottomBarRestoreSelectedFoldersClicked) },
            onDeleteClicked = { send(Msg.Ui.OnBottomBarDeleteSelectedFoldersClicked) },
            scrollState = scrollState,
            isSelectionState = model.value.isSelectionState,
            isDisabledBottomBar = model.value.selectedFolders.isEmpty() && model.value.isSelectionState,
        )
    }

    if (model.value.isVisibleModalSheet) {
        ModalBottomSheetDefault(
            sheetState = model.value.modalBottomSheetState,
            onDismissRequest = { send(Msg.Inner.UpdatedModalSheetState(false)) },
        ) {
            TrashDeleteModalSheetContent(
                hideSheet = { send(Msg.Ui.HideModalBottomSheet) },
                onRestoreClicked = { send(Msg.Ui.OnModalSheetRestoreClicked) },
                onDeleteClicked = { send(Msg.Ui.OnModalSheetDeleteClicked) },
            )
        }
    }

    TrashDialogAcceptDeleteData(
        isVisible = model.value.isVisibleAcceptDeleteFoldersDialog,
        onCancelClicked = { send(Msg.Ui.OnCancelDeleteWarningDialogClicked) },
        onAcceptClicked = { send(Msg.Ui.OnAcceptDeleteWarningDialogClicked) },
        isSingleItemAction = model.value.isSingleItemAction,
        isNotesDialog = false
    )
}

@Composable
private fun FoldersList(
    model: Model,
    send: SendMessage,
    scrollState: LazyListState,
    foldersListApi: FoldersListApi.Ui,
    modifier: Modifier
) {
    val bottomContentPadding =
        animateDp(if (model.isSelectionState) Theme.widgetSize.bottomBarNormalHeight else 0.dp)

    LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(
            start = dp10,
            end = dp10,
            bottom = bottomContentPadding.value
        ),
        modifier = modifier
    ) {

        if (model.base.isLoading) {
            item {
                foldersListApi.FoldersLoaderWidget(modifier)
            }
        } else {
            if (model.folders.data.isEmpty()) {
                item {
                    ScreenPlaceholder(
                        imageVector = AppImage.EmptyTrash,
                        message = text.trash.messageEmptyTrashFoldersList,
                        modifier = Modifier.fillParentMaxHeight()
                    )
                }
            } else {
                items(model.folders.data) { folder ->
                    foldersListApi.FolderListItem(
                        folder = folder,
                        isSelected = model.selectedFolders.contains(folder),
                        isCurrent = false,
                        onFolderClicked = { id -> send(Msg.Ui.OnFolderClicked(id)) },
                        onFolderLongPressed = { id -> send(Msg.Ui.OnFolderLongClicked(id)) },
                        isTrashPlacement = true,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}


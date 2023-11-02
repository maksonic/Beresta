package ru.maksonic.beresta.screen.trash.folders.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import ru.maksonic.beresta.common.ui_kit.placeholder.PlaceholderEmptyState
import ru.maksonic.beresta.common.ui_kit.sheet.ModalBottomSheetContainer
import ru.maksonic.beresta.common.ui_kit.widget.trash_screen.BottomAppBarTrash
import ru.maksonic.beresta.common.ui_kit.widget.trash_screen.TrashModalSheetDeleteDataContent
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.background
import ru.maksonic.beresta.common.ui_theme.provide.dp10
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp6
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersListUiApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.trash.folders.core.Model
import ru.maksonic.beresta.screen.trash.folders.core.Msg
import ru.maksonic.beresta.screen.trash.folders.core.TrashFoldersSorter
import ru.maksonic.beresta.screen.trash.folders.core.rememberTrashListState
import ru.maksonic.beresta.screen.trash.folders.ui.widget.DialogAcceptDeleteItems
import ru.maksonic.beresta.screen.trash.folders.ui.widget.TopBar

/**
 * @Author maksonic on 30.05.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    model: Model,
    send: Send,
    foldersListApi: FoldersListUiApi,
    modalBottomSheetState: SheetState,
    modifier: Modifier = Modifier
) {
    val foldersState = rememberTrashListState(model)
    val sorter = rememberUpdatedState(TrashFoldersSorter(model.folders.data))
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

        Scaffold(
            topBar = { TopBar(model, send, scrollBehavior) },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->

            Box(modifier.padding(paddings), contentAlignment = Alignment.BottomEnd) {
                foldersListApi.List(
                    state = foldersState.value,
                    sorter = sorter,
                    onFolderClicked = { send(Msg.Ui.OnFolderClicked(it)) },
                    onFolderLongClicked = { send(Msg.Ui.OnFolderLongClicked(it)) },
                    onErrorRetryClicked = { send(Msg.Ui.OnRetryFetchDataClicked) },
                    onToHiddenNotesClicked = {},
                    isTrashPlacement = true,
                    modifier = modifier,
                    contentPadding = PaddingValues(
                        top = dp10,
                        start = dp16,
                        end = dp16,
                        bottom = Theme.size.bottomMainBarHeight.plus(dp6)
                    ),
                    loadingModifier = Modifier,
                    emptyListPlaceholder = {
                        PlaceholderEmptyState(
                            painter = painterResource(Theme.image.imageEmptyTrash),
                            message = text.trash.messageEmptyTrashFoldersList
                        )
                    }
                )
            }
        }

        BottomAppBarTrash(
            isEmptyList = model.folders.data.isEmpty(),
            isSelection = model.isSelection,
            isDisabled = model.folders.data.none { it.isSelected } && model.isSelection,
            onRestoreClicked = { send(Msg.Ui.OnBottomBarRestoreFoldersClicked) },
            onDeleteClicked = { send(Msg.Ui.OnBottomBarDeleteFoldersClicked) },
            onDeleteAllClicked = { send(Msg.Ui.OnDeleteAllFoldersClicked) },
        )

        if (model.isVisibleModalSheet) {
            ModalBottomSheetContainer(
                sheetState = modalBottomSheetState,
                onDismissRequest = { send(Msg.Inner.UpdatedModalSheetState(false)) },
            ) {
                TrashModalSheetDeleteDataContent(
                    hideSheet = { send(Msg.Ui.HideModalBottomSheet) },
                    onRestoreClicked = { send(Msg.Ui.OnModalSheetRestoreClicked) },
                    onDeleteClicked = { send(Msg.Ui.OnModalSheetDeleteClicked) },
                )
            }
        }

        DialogAcceptDeleteItems(model, send)
    }
}


package ru.maksonic.beresta.screen.folders.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Wallpaper
import ru.maksonic.beresta.common.ui_kit.placeholder.PlaceholderEmptyState
import ru.maksonic.beresta.common.ui_kit.sheet.ModalBottomSheetContainer
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.background
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp6
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersListUiApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.HiddenNotesDialogUiApi
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.SortingSheetUiApi
import ru.maksonic.beresta.feature.ui.add_folder_dialog.api.AddFolderDialogUiApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.folders.core.Model
import ru.maksonic.beresta.screen.folders.core.Msg
import ru.maksonic.beresta.screen.folders.ui.widget.BottomBar
import ru.maksonic.beresta.screen.folders.ui.widget.MultipleModalBottomSheetContent
import ru.maksonic.beresta.screen.folders.ui.widget.TopBar

/**
 * @Author maksonic on 09.10.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    model: Model,
    send: Send,
    addFolderDialogApi: AddFolderDialogUiApi,
    foldersListApi: FoldersListUiApi,
    hiddenNotesPinInputDialogUiApi: HiddenNotesDialogUiApi.PinInputDialog,
    sortingSheetApi: SortingSheetUiApi,
    modalBottomSheetState: SheetState,
    modifier: Modifier = Modifier
) {
    val sorter = rememberFoldersSorter(model.folders.collection)
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val barHeight = Theme.size.bottomMainBarHeight
    val paddingFoldersBottom = animateDpAsState(
        if (model.folders.isSelection) barHeight.plus(dp6) else barHeight,
        tween(Theme.animVelocity.common), label = ""
    )

    BackHandler(model.folders.isSelection) {
        send(Msg.Ui.CancelSelectionState)
    }

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Scaffold(
            topBar = { TopBar(model, send, scrollBehavior) },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->
            Box(modifier.padding(paddings)) {
                foldersListApi.List(
                    state = model.folders,
                    sorter = sorter,
                    onFolderClicked = { send(Msg.Ui.OnFolderClicked(it)) },
                    onFolderLongClicked = { send(Msg.Ui.OnFolderLongClicked(it)) },
                    onErrorRetryClicked = { send(Msg.Ui.OnRetryFetchDataClicked) },
                    onToHiddenNotesClicked = {
                        send(Msg.Inner.UpdatedHiddenNotesDialogVisibility(true))
                    },
                    isTrashPlacement = false,
                    modifier = modifier,
                    contentPadding = PaddingValues(
                        top = dp16,
                        start = dp16,
                        end = dp16,
                        bottom = paddingFoldersBottom.value
                    ),
                    loadingModifier = Modifier.padding(top = dp8),
                    emptyListPlaceholder = {
                        PlaceholderEmptyState(
                            imageVector = AppIcon.Wallpaper, message = text.shared.hintNoFolders
                        )
                    }
                )
            }
        }

        AnimateFadeInOut(model.folders.state.successAfterLoading) {
            BottomBar(model, send)
        }

        if (model.modalSheet.isVisible) {
            ModalBottomSheetContainer(
                sheetState = modalBottomSheetState,
                onDismissRequest = { send(Msg.Inner.HiddenModalBottomSheet) },
            ) {
                MultipleModalBottomSheetContent(model, sortingSheetApi)
            }
        }

        addFolderDialogApi.Widget(
            isVisible = model.isVisibleEditFolderDialog,
            hideDialog = { send(Msg.Ui.OnCloseEditFolderDialogClicked) }
        )

        hiddenNotesPinInputDialogUiApi.Widget(
            isVisible = model.isVisibleHiddenNotesDialog,
            onSuccessPin = { send(Msg.Inner.NavigatedToHiddenNotes) },
            hideDialog = { send(Msg.Inner.UpdatedHiddenNotesDialogVisibility(false)) },
            isBlocked = false,
            onBlockedBackPressed = {}
        )
    }
}
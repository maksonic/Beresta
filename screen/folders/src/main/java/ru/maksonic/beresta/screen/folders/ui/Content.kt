package ru.maksonic.beresta.screen.folders.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalView
import ru.maksonic.beresta.core.VibrationPerformer
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.hidden_notes.api.HiddenNotesApi
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.folders.core.Model
import ru.maksonic.beresta.screen.folders.core.Msg
import ru.maksonic.beresta.screen.folders.ui.widget.BottomBarContent
import ru.maksonic.beresta.screen.folders.ui.widget.FoldersList
import ru.maksonic.beresta.screen.folders.ui.widget.MultipleModalBottomSheetContent
import ru.maksonic.beresta.screen.folders.ui.widget.TopBar
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.bar.SnackBarAction
import ru.maksonic.beresta.ui.widget.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.sheet.ModalBottomSheetDefault

/**
 * @Author maksonic on 15.05.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    foldersUiItemApi: FoldersApi.Ui.FolderItem,
    foldersPlaceholderApi: FoldersApi.Ui.Placeholder,
    chipsDialogApi: FoldersApi.Ui.AddChipDialog,
    hiddenNotesEnterPasswordDialog: HiddenNotesApi.Ui.EnterPasswordDialog,
    model: State<Model>,
    send: SendMessage,
    listSortUiState: SortingSheetApi.Ui,
    vibrationPerformer: VibrationPerformer,
    modifier: Modifier = Modifier
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val isVisibleFirstFolderOffset = remember { mutableStateOf(true) }
    val isCanScrollForward = remember { mutableStateOf(true) }
    val isSelectionState = rememberUpdatedState(model.value.isSelectionState)
    val view = LocalView.current
    
    BackHandler(model.value.isSelectionState) {
        send(Msg.Ui.CancelSelectionState)
    }

    LaunchedEffect(model.value.isSelectionState) {
        if (model.value.isSelectionState) {
            vibrationPerformer.keyboardTapVibration(view)
        }
    }

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Scaffold(
            topBar = { TopBar(scrollBehavior, model, send) },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->
            Box(modifier.padding(paddings)) {
                FoldersList(
                    folderUiItemApi = foldersUiItemApi,
                    foldersPlaceholderApi = foldersPlaceholderApi,
                    model = model,
                    send = send,
                    updateFirstVisibleFolderOffset = { isVisibleFirstFolderOffset.value = it },
                    updateCanScrollForwardState = { isCanScrollForward.value = it },
                    listSortUiState = listSortUiState
                )
            }
        }

        AnimateFadeInOut(model.value.base.successAfterLoading) {
            BottomBarContent(model, send, isVisibleFirstFolderOffset, isCanScrollForward)
        }

        AnimatedVisibility(model.value.isVisibleRemovedSnackBar) {
            val idlePadding =
                Theme.widgetSize.btnPrimaryHeight.plus(dp16).plus(SystemNavigationBarHeight)
            val selectionPadding =
                Theme.widgetSize.bottomBarNormalHeight.plus(SystemNavigationBarHeight)
            val snackBarPadding =
                animateDp(if (isSelectionState.value) selectionPadding else idlePadding)

            SnackBarAction(
                message = text.folders.hintRemovedFoldersCount.plus(
                    " ${model.value.removedList.count()}"
                ),
                actionTitle = text.shared.btnTitleCancel,
                onClick = { send(Msg.Ui.OnSnackUndoRemoveFoldersClicked) },
                modifier = Modifier.padding(bottom = snackBarPadding.value)
            )
        }

        if (model.value.modalSheet.isVisible) {
            ModalBottomSheetDefault(
                sheetState = model.value.modalSheet.state,
                onDismissRequest = { send(Msg.Inner.HiddenModalBottomSheet) },
            ) {
                MultipleModalBottomSheetContent(model, send)
            }
        }

        chipsDialogApi.Widget()

        hiddenNotesEnterPasswordDialog.Widget { send(Msg.Inner.NavigatedToHiddenNotes) }
    }
}
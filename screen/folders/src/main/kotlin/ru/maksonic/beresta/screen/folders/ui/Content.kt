package ru.maksonic.beresta.screen.folders.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.screen.folders.core.Model
import ru.maksonic.beresta.screen.folders.core.Msg
import ru.maksonic.beresta.screen.folders.ui.widget.BottomBarContent
import ru.maksonic.beresta.screen.folders.ui.widget.FoldersList
import ru.maksonic.beresta.screen.folders.ui.widget.HiddenNotesDialog
import ru.maksonic.beresta.screen.folders.ui.widget.MultipleModalBottomSheetContent
import ru.maksonic.beresta.screen.folders.ui.widget.TopBar
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.sheet.ModalBottomSheetDefault

/**
 * @Author maksonic on 15.05.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    foldersUiItemApi: FoldersApi.Ui.FolderItem,
    foldersPlaceholderApi: FoldersApi.Ui.Placeholder,
    chipsDialogApi: FoldersApi.Ui.AddChipDialog,
    chipsRowApi: FoldersApi.Ui.ChipsRow,
    model: State<Model>,
    send: SendMessage,
    listSortUiState: SortingSheetApi.Ui,
    modifier: Modifier = Modifier
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val currentSelectedFolder = chipsRowApi.currentSelectedId.state.collectAsStateWithLifecycle()

    BackHandler(model.value.isSelectionState) {
        send(Msg.Ui.CancelSelectionState)
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
                    currentSelectedFolder = currentSelectedFolder,
                    listSortUiState = listSortUiState
                )
            }
        }

        AnimateFadeInOut(model.value.base.successAfterLoading) {
            BottomBarContent(model, send)
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

        HiddenNotesDialog(isVisible = model.value.isVisibleHiddenNotesDialog, send)
    }
}
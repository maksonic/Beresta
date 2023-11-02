package ru.maksonic.beresta.screen.main.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import ru.maksonic.beresta.common.ui_kit.sheet.ModalBottomSheetContainer
import ru.maksonic.beresta.common.ui_theme.colors.surface
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersChipsRowUiApi
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersFeature
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.HiddenNotesDialogUiApi
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesListUiApi
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.SortingSheetUiApi
import ru.maksonic.beresta.feature.ui.add_folder_dialog.api.AddFolderDialogUiApi
import ru.maksonic.beresta.screen.main.core.Model
import ru.maksonic.beresta.screen.main.core.Msg
import ru.maksonic.beresta.screen.main.core.sorter.rememberChipsSorter
import ru.maksonic.beresta.screen.main.ui.widget.EditNoteExpandableFab
import ru.maksonic.beresta.screen.main.ui.widget.MainBottomBar
import ru.maksonic.beresta.screen.main.ui.widget.MainBottomBarState
import ru.maksonic.beresta.screen.main.ui.widget.MainSearchBar
import ru.maksonic.beresta.screen.main.ui.widget.MultipleModalBottomSheetContent
import ru.maksonic.beresta.screen.main.ui.widget.NotesList

/**
 * @Author maksonic on 02.10.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(
    model: Model,
    send: Send,
    addFolderDialogUiApi: AddFolderDialogUiApi,
    chipsRowUiApi: FoldersChipsRowUiApi,
    hiddenNotesPinInputDialogUiApi: HiddenNotesDialogUiApi.PinInputDialog,
    notesListUiApi: NotesListUiApi,
    sortingSheetUiApi: SortingSheetUiApi,
    modalBottomSheetState: SheetState,
    modifier: Modifier = Modifier,
) {
    val mainBottomBarState = rememberSaveable { mutableStateOf(MainBottomBarState.IDLE) }
    val isNotInitialFolder = rememberUpdatedState(FoldersFeature.currentSelected != 1L)
    val isVisibleBottomBar = remember { mutableStateOf(true) }
    val isCanScrollBackward = rememberSaveable { mutableStateOf(false) }
    val chipsRowOffset = remember { mutableFloatStateOf(0f) }
    val chipsSorter = rememberChipsSorter(model.chips.collection.data)

    BackHandler(model.notes.isSelection.or(isNotInitialFolder.value)) {
        when {
            model.notes.isSelection && isNotInitialFolder.value -> send(Msg.Ui.CancelNotesSelection)
            model.notes.isSelection && !isNotInitialFolder.value -> send(Msg.Ui.CancelNotesSelection)
            else -> send(Msg.Inner.ResetCurrentSelectedFolder)
        }
    }

    LaunchedEffect(model.notes.isSelection) {
        mainBottomBarState.value = if (model.notes.isSelection) MainBottomBarState.SELECTION
        else MainBottomBarState.IDLE
    }

    LaunchedEffect(model.notes.collection) {
        if (model.notes.collection.data.isEmpty()) {
            chipsRowOffset.floatValue = 0f
        }
    }

    Box(
        modifier
            .fillMaxSize()
            .background(surface), contentAlignment = Alignment.BottomEnd
    ) {
        NotesList(
            model = model,
            send = send,
            api = notesListUiApi,
            updateScrollUpValue = { isVisibleBottomBar.value = it },
            updatedCanScrollBackwardValue = { isCanScrollBackward.value = it },
            chipsRowOffset = chipsRowOffset,
            updateChipsRowOffset = { chipsRowOffset.floatValue = it }
        )

        Column {
            chipsRowUiApi.Widget(
                state = model.chips.state,
                sorter = chipsSorter,
                isColoredBackground = isCanScrollBackward,
                onAddNewChipClicked = { send(Msg.Ui.OnAddNewChipClicked) },
                onRetryFetchChipsClicked = { send(Msg.Ui.OnRetryFetchChipsClicked) },
                onChipClicked = { send(Msg.Ui.OnChipClicked(it)) },
                modifier = modifier.graphicsLayer {
                    translationY = chipsRowOffset.floatValue
                }
            )

            Spacer(modifier.weight(1f))

            MainBottomBar(
                model = model,
                send = send,
                state = mainBottomBarState,
                isVisibleBottomBar = isVisibleBottomBar,
            )
        }

        MainSearchBar(model, send, isCanScrollBackward)

        EditNoteExpandableFab(model, send, modifier)

        if (model.modalSheet.isVisible) {
            ModalBottomSheetContainer(
                sheetState = modalBottomSheetState,
                onDismissRequest = { send(Msg.Inner.HiddenModalBottomSheet) },
            ) {
                MultipleModalBottomSheetContent(model, sortingSheetUiApi)
            }
        }

        hiddenNotesPinInputDialogUiApi.Widget(
            isVisible = model.isVisibleHiddenNotesDialog,
            onSuccessPin = { send(Msg.Inner.NavigatedToHiddenNotes) },
            hideDialog = { send(Msg.Inner.UpdatedHiddenNotesDialogVisibility(false)) },
            isBlocked = false,
            onBlockedBackPressed = {}
        )

        addFolderDialogUiApi.Widget(
            isVisible = model.isVisibleAddChipDialog,
            hideDialog = { send(Msg.Ui.OnCloseAddChipDialogClicked) }
        )
    }
}
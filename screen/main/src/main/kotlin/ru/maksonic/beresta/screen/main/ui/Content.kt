package ru.maksonic.beresta.screen.main.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.LocalCurrentSelectedFolderState
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.sorting_sheet.api.LocalListSortState
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.screen.main.core.Model
import ru.maksonic.beresta.screen.main.core.Msg
import ru.maksonic.beresta.screen.main.ui.widget.ChipsRow
import ru.maksonic.beresta.screen.main.ui.widget.EditNoteExpandableFab
import ru.maksonic.beresta.screen.main.ui.widget.HiddenNotesDialog
import ru.maksonic.beresta.screen.main.ui.widget.MainSearchBar
import ru.maksonic.beresta.screen.main.ui.widget.NotesList
import ru.maksonic.beresta.screen.main.ui.widget.bottombar.MainBottomBar
import ru.maksonic.beresta.screen.main.ui.widget.bottombar.MainBottomBarState
import ru.maksonic.beresta.screen.main.ui.widget.sheet.MultipleModalBottomSheetContent
import ru.maksonic.beresta.ui.widget.sheet.ModalBottomSheetDefault

/**
 * @Author maksonic on 22.06.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    model: State<Model>,
    send: SendMessage,
    modifier: Modifier = Modifier,
    notesListApi: NotesApi.Ui.List,
    chipsRowApi: FoldersApi.Ui.ChipsRow,
    chipsDialogApi: FoldersApi.Ui.AddChipDialog,
    listSortUiState: SortingSheetApi.Ui,
) {
    val sharedNotesUiState = notesListApi.sharedUiState.state.collectAsStateWithLifecycle()
    val mainBottomBarState = rememberSaveable { mutableStateOf(MainBottomBarState.IDLE) }
    val isVisibleBottomBar = remember { mutableStateOf(true) }
    val isSelectionState = rememberUpdatedState(model.value.notes.isSelection)
    val isEnabledBottomBar = rememberUpdatedState(model.value.notes.selectedList.isNotEmpty())
    val isShowUnpinBtn = rememberUpdatedState(model.value.notes.isVisibleUnpinMainBarIcon)
    val chipsRowOffsetHeightPx = remember { mutableFloatStateOf(0f) }
    val currentSelectedChipId = chipsRowApi.currentSelectedId.state.collectAsStateWithLifecycle()
    val sortState = listSortUiState.state.state.collectAsStateWithLifecycle()


    BackHandler(isSelectionState.value) {
        send(Msg.Ui.CancelNotesSelection)
    }

    BackHandler(currentSelectedChipId.value != 1L) {
        send(Msg.Inner.ResetCurrentSelectedFolder)
    }

    LaunchedEffect(sharedNotesUiState.value.isScrollUp) {
        isVisibleBottomBar.value =
            if (model.value.notes.isSelection) true else sharedNotesUiState.value.isScrollUp
    }

    LaunchedEffect(isSelectionState.value) {
        val value =
            if (isSelectionState.value) MainBottomBarState.SELECTION else MainBottomBarState.IDLE

        mainBottomBarState.value = value
    }

    LaunchedEffect(model.value.notes.collection) {
        if (model.value.notes.collection.data.isEmpty()) {
            chipsRowOffsetHeightPx.floatValue = 0f
        }
    }

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        val isCanScrollBackwardState = rememberSaveable { mutableStateOf(false) }

        CompositionLocalProvider(
            LocalListSortState provides sortState.value,
            LocalCurrentSelectedFolderState provides currentSelectedChipId.value,
        ) {
            NotesList(
                model = model,
                send = send,
                api = notesListApi,
                chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
                updateChipsRowOffsetHeight = { chipsRowOffsetHeightPx.floatValue = it },
                updatedCanScrollBackwardValue = { isCanScrollBackwardState.value = it }
            )

            ChipsRow(
                model = model,
                send = send,
                api = chipsRowApi,
                isColoredBackground = isCanScrollBackwardState,
                chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
            )

            MainBottomBar(
                state = mainBottomBarState,
                send = send,
                isSelectionState = isSelectionState,
                isVisibleBottomBar = isVisibleBottomBar,
                isEnabledBar = isEnabledBottomBar,
                isShowUnpinBtn = isShowUnpinBtn,
                sharedNotesUiScrollState = notesListApi.sharedUiState
            )

            MainSearchBar(model, send, isCanScrollBackwardState)

            EditNoteExpandableFab(model, send, modifier)

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
}
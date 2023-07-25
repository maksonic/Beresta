package ru.maksonic.beresta.screen.hidden_notes.ui

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
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.maksonic.beresta.core.VibrationPerformer
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.sorting_sheet.api.LocalListSortState
import ru.maksonic.beresta.feature.sorting_sheet.api.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.screen.hidden_notes.core.Model
import ru.maksonic.beresta.screen.hidden_notes.core.Msg
import ru.maksonic.beresta.screen.hidden_notes.ui.widget.EditNoteExpandableFab
import ru.maksonic.beresta.screen.hidden_notes.ui.widget.BottomBar
import ru.maksonic.beresta.screen.hidden_notes.ui.widget.SearchBar
import ru.maksonic.beresta.screen.hidden_notes.ui.widget.NotesList
import ru.maksonic.beresta.ui.widget.sheet.ModalBottomSheetDefault

/**
 * @Author maksonic on 18.07.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    model: State<Model>,
    send: SendMessage,
    modifier: Modifier = Modifier,
    notesListApi: NotesApi.Ui.List,
    counterApi: TopBarCounterApi.Ui,
    sortedSheetApi: SortingSheetApi.Ui,
    listSortUiState: SortingSheetApi.Ui,
    vibrationPerformer: VibrationPerformer
) {
    val sharedNotesUiState = notesListApi.sharedUiState.state.collectAsStateWithLifecycle()
    val isVisibleBottomBar = remember { mutableStateOf(true) }
    val isSelectionState = rememberUpdatedState(model.value.notes.isSelection)
    val isEnabledBottomBar = rememberUpdatedState(model.value.notes.selectedList.isNotEmpty())
    val isShowUnpinBtn = rememberUpdatedState(model.value.notes.isVisibleUnpinMainBarIcon)
    val isVisibleEditFab = rememberUpdatedState(model.value.isVisibleEditFab)
    val selectedNotesCount = rememberUpdatedState(model.value.notes.selectedList.count())
    val chipsRowOffsetHeightPx = remember { mutableFloatStateOf(0f) }
    val notesSortState = listSortUiState.state.state.collectAsStateWithLifecycle()
    val view = LocalView.current

    LaunchedEffect(sharedNotesUiState.value.isScrollUp) {
        isVisibleBottomBar.value =
            if (model.value.notes.isSelection) true else sharedNotesUiState.value.isScrollUp
    }
    BackHandler(isSelectionState.value) {
        send(Msg.Ui.CancelNotesSelection)
    }

    LaunchedEffect(isSelectionState.value) {
        if (isSelectionState.value) {
            vibrationPerformer.keyboardTapVibration(view)
        }
        isVisibleBottomBar.value = isSelectionState.value
    }

    LaunchedEffect(model.value.notes.collection) {
        if (model.value.notes.collection.data.isEmpty()) {
            chipsRowOffsetHeightPx.floatValue = 0f
        }
    }

    LaunchedEffect(selectedNotesCount.value) {
        counterApi.counterState.value = selectedNotesCount.value
    }

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        val isCanScrollBackwardState = rememberSaveable { mutableStateOf(false) }

        CompositionLocalProvider(LocalListSortState provides notesSortState.value) {
            NotesList(
                model = model,
                send = send,
                api = notesListApi,
                chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
                updateChipsRowOffsetHeight = { chipsRowOffsetHeightPx.floatValue = it },
                updatedCanScrollBackwardValue = { isCanScrollBackwardState.value = it }
            )

            BottomBar(
                send = send,
                isSelectionState = isSelectionState,
                isVisibleBottomBar = isVisibleBottomBar,
                isEnabledBar = isEnabledBottomBar,
                isShowUnpinBtn = isShowUnpinBtn,
                sharedNotesUiScrollState = notesListApi.sharedUiState
            )

            SearchBar(model, send, isColoredBackplate = isCanScrollBackwardState)

            EditNoteExpandableFab(isVisibleEditFab, modifier)

            if (model.value.modalSheet.isVisible) {
                ModalBottomSheetDefault(
                    sheetState = model.value.modalSheet.state,
                    onDismissRequest = { send(Msg.Inner.HiddenModalBottomSheet) },
                ) {
                    sortedSheetApi.SheetContent(
                        sortDataKey = SortDataKey.NOTES,
                        hideSheet = { send(Msg.Ui.OnHideModalBottomSheet) }
                    )
                }
            }
        }
    }
}
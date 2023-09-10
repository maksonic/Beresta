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
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.sorting_sheet.api.LocalListSortState
import ru.maksonic.beresta.feature.sorting_sheet.api.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.screen.hidden_notes.core.Model
import ru.maksonic.beresta.screen.hidden_notes.core.Msg
import ru.maksonic.beresta.screen.hidden_notes.ui.widget.BottomBar
import ru.maksonic.beresta.screen.hidden_notes.ui.widget.EditNoteExpandableFab
import ru.maksonic.beresta.screen.hidden_notes.ui.widget.NotesList
import ru.maksonic.beresta.screen.hidden_notes.ui.widget.SearchBar
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
    notesListApi: NotesApi.List.Ui = koinInject(),
    listSortApi: SortingSheetApi.Ui = koinInject(),
) {
    val isSelectionState = rememberUpdatedState(model.value.notes.isSelection)
    val isEnabledBottomBar = rememberUpdatedState(model.value.notes.selectedList.isNotEmpty())
    val isShowUnpinBtn = rememberUpdatedState(model.value.notes.isVisibleUnpinMainBarIcon)
    val chipsRowOffsetHeightPx = remember { mutableFloatStateOf(0f) }

    BackHandler(isSelectionState.value) {
        send(Msg.Ui.CancelNotesSelection)
    }

    LaunchedEffect(model.value.notes.collection) {
        if (model.value.notes.collection.data.isEmpty()) {
            chipsRowOffsetHeightPx.floatValue = 0f
        }
    }

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        val isCanScrollBackwardState = rememberSaveable { mutableStateOf(false) }

        CompositionLocalProvider(LocalListSortState provides listSortApi.sharedState.value) {
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
                isVisibleBottomBar = isSelectionState,
                isEnabledBar = isEnabledBottomBar,
                isShowUnpinBtn = isShowUnpinBtn,
                updateIsScrollUpSharedScrollState = { notesListApi.updateScrollState(it) },
            )

            SearchBar(model, send, isColoredBackplate = isCanScrollBackwardState)

            EditNoteExpandableFab(model, send, modifier)

            if (model.value.modalSheet.isVisible) {
                ModalBottomSheetDefault(
                    sheetState = model.value.modalSheet.state,
                    onDismissRequest = { send(Msg.Inner.HiddenModalBottomSheet) },
                ) {
                    listSortApi.SheetContent(
                        sortDataKey = SortDataKey.HIDDEN_NOTES,
                        hideSheet = { send(Msg.Ui.OnHideModalBottomSheet) }
                    )
                }
            }
        }
    }
}
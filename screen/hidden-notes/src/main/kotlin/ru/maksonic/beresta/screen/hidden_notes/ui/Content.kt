package ru.maksonic.beresta.screen.hidden_notes.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_kit.sheet.ModalBottomSheetContainer
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesListUiApi
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.SortingSheetUiApi
import ru.maksonic.beresta.screen.hidden_notes.core.Model
import ru.maksonic.beresta.screen.hidden_notes.core.Msg
import ru.maksonic.beresta.screen.hidden_notes.ui.widget.BottomBar
import ru.maksonic.beresta.screen.hidden_notes.ui.widget.EditNoteExpandableFab
import ru.maksonic.beresta.screen.hidden_notes.ui.widget.NotesList
import ru.maksonic.beresta.screen.hidden_notes.ui.widget.SearchBar

/**
 * @Author maksonic on 18.07.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    model: Model,
    send: Send,
    modalBottomSheetState: SheetState,
    modifier: Modifier = Modifier,
    notesListUiApi: NotesListUiApi = koinInject(),
    sortingSheetUiApi: SortingSheetUiApi = koinInject(),
) {
    val chipsRowOffsetHeightPx = remember { mutableFloatStateOf(0f) }

    BackHandler(model.notes.isSelection) {
        send(Msg.Ui.CancelNotesSelection)
    }

    LaunchedEffect(model.notes.collection) {
        if (model.notes.collection.data.isEmpty()) {
            chipsRowOffsetHeightPx.floatValue = 0f
        }
    }

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        val isCanScrollBackwardState = rememberSaveable { mutableStateOf(false) }

            NotesList(
                model = model,
                send = send,
                api = notesListUiApi,
                updatedCanScrollBackwardValue = { isCanScrollBackwardState.value = it }
            )

            BottomBar(model, send)

            SearchBar(model, send, isColoredBackplate = isCanScrollBackwardState)

            EditNoteExpandableFab(model, send, modifier)

            if (model.modalSheet.isVisible) {
                ModalBottomSheetContainer(
                    sheetState = modalBottomSheetState,
                    onDismissRequest = { send(Msg.Inner.HiddenModalBottomSheet) },
                ) {
                    sortingSheetUiApi.SheetContent(SortDataKey.HIDDEN_NOTES)
                }
        }
    }
}
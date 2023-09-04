package ru.maksonic.beresta.screen.trash_list.notes.ui

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
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.screen.trash_list.notes.core.Model
import ru.maksonic.beresta.screen.trash_list.notes.core.Msg
import ru.maksonic.beresta.screen.trash_list.notes.ui.widget.NotesList
import ru.maksonic.beresta.screen.trash_list.notes.ui.widget.TopBar
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.bar.bottom.TrashBottomBar
import ru.maksonic.beresta.ui.widget.dialog.TrashDialogAcceptDeleteData
import ru.maksonic.beresta.ui.widget.sheet.ModalBottomSheetDefault
import ru.maksonic.beresta.ui.widget.sheet.TrashDeleteModalSheetContent

/**
 * @Author maksonic on 30.05.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    model: State<Model>,
    send: SendMessage,
    notesListUiApi: NotesApi.Ui.List,
    noteCardUiApi: NotesApi.Ui.Card,
    modifier: Modifier = Modifier
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Scaffold(
            topBar = { TopBar(scrollBehavior, model, send) },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->
            NotesList(
                model = model,
                send = send,
                notesListUiApi = notesListUiApi,
                noteCardUiApi = noteCardUiApi,
                modifier = modifier.padding(paddings)
            )
        }
        TrashBottomBar(
            onRestoreClicked = { send(Msg.Ui.OnBottomBarRestoreSelectedNotesClicked) },
            onDeleteClicked = { send(Msg.Ui.OnBottomBarDeleteSelectedNotesClicked) },
            isSelectionState = model.value.isSelectionState,
            isDisabledBottomBar = model.value.selectedList.isEmpty() && model.value.isSelectionState,
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
        isVisible = model.value.isVisibleAcceptDeleteNotesDialog,
        onCancelClicked = { send(Msg.Ui.OnCancelDeleteWarningDialogClicked) },
        onAcceptClicked = { send(Msg.Ui.OnAcceptDeleteWarningDialogClicked) },
        isSingleItemAction = model.value.isSingleItemAction,
    )
}

package ru.maksonic.beresta.screen.main.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBarDuration
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBarHostState
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBarResult
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersChipsRowUiApi
import ru.maksonic.beresta.feature.folders_list.ui.api.LocalCurrentSelectedFolderState
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.HiddenNotesDialogUiApi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NotesCardUiApi
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesListUiApi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.LocalNoteCardState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.LocalListFoldersSortState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.LocalListNotesSortState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.SortingSheetUiApi
import ru.maksonic.beresta.feature.ui.add_folder_dialog.api.AddFolderDialogUiApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.routes.MainRouter
import ru.maksonic.beresta.platform.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.screen.main.core.Eff
import ru.maksonic.beresta.screen.main.core.MainSandbox
import ru.maksonic.beresta.screen.main.core.Msg
import ru.maksonic.beresta.screen.main.ui.SnackBarKey

/**
 * @Author maksonic on 02.10.2023
 */
internal typealias Send = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Container(
    router: MainRouter,
    sandbox: MainSandbox = koinViewModel(),
    addFolderDialogUiApi: AddFolderDialogUiApi = koinInject(),
    chipsRowUiApi: FoldersChipsRowUiApi = koinInject(),
    currentFolderStoreUiApi: FoldersChipsRowUiApi.CurrentSelectedFolderStore = koinInject(),
    hiddenNotesDialogUiApi: HiddenNotesDialogUiApi.PinInputDialog = koinInject(),
    notesCardStateStoreUiApi: NotesCardUiApi.CardState = koinInject(),
    notesListApi: NotesListUiApi = koinInject(),
    sortingSheetUiApi: SortingSheetUiApi = koinInject()
) {
    val model by sandbox.model.collectAsStateWithLifecycle()
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = model.modalSheet.skipPartiallyExpanded
    )

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        addFolderDialogUiApi = addFolderDialogUiApi,
        currentFolderStoreUiApi = currentFolderStoreUiApi,
        modalBottomSheetState = modalBottomSheetState,
        snackState = model.snackNotesState,
        hideModalSheet = { sandbox.send(Msg.Inner.HiddenModalBottomSheet) },
        send = sandbox::send
    )

    CompositionLocalProvider(
        LocalListNotesSortState provides model.sortState.notes,
        LocalListFoldersSortState provides model.sortState.chips,
        LocalCurrentSelectedFolderState provides currentFolderStoreUiApi.id.value,
        LocalNoteCardState provides notesCardStateStoreUiApi.sharedState.value,
    ) {
        Content(
            model = model,
            send = sandbox::send,
            addFolderDialogUiApi = addFolderDialogUiApi,
            chipsRowUiApi = chipsRowUiApi,
            hiddenNotesPinInputDialogUiApi = hiddenNotesDialogUiApi,
            notesListUiApi = notesListApi,
            sortingSheetUiApi = sortingSheetUiApi,
            modalBottomSheetState = modalBottomSheetState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: MainRouter,
    addFolderDialogUiApi: AddFolderDialogUiApi,
    currentFolderStoreUiApi: FoldersChipsRowUiApi.CurrentSelectedFolderStore,
    modalBottomSheetState: SheetState,
    snackState: SnackBarHostState,
    hideModalSheet: () -> Unit,
    send: Send
) {
    val scope = rememberCoroutineScope()
    val snackScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    // Snack messages
    val removedNotesPrefix = text.shared.hintRemovedNotesCount

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateToEditNote -> {
                focusManager.clearFocus().let { router.toNoteEditor(eff.id) }
            }

            is Eff.HideModalSheet -> {
                scope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
                    if (!modalBottomSheetState.isVisible) {
                        hideModalSheet()
                    }
                }
            }

            is Eff.NavigateToSettings -> router.toSettings()
            is Eff.NavigateToFolders -> router.toFoldersList(eff.ids)
            is Eff.NavigateToHiddenNotes -> router.toHiddenNotes()
            is Eff.NavigateToTrash -> router.toTrash()
            is Eff.ShowAddNewChipDialog -> addFolderDialogUiApi.addFolder()
            is Eff.UpdateCurrentSelectedFolder -> currentFolderStoreUiApi.updateId(eff.id)
            is Eff.ShowSnackBar -> {
                snackScope.launch {
                    when (eff.key) {
                        SnackBarKey.REMOVED_NOTES -> {
                            val result = snackState.showSnackBar(
                                message = "$removedNotesPrefix ${eff.message}",
                                actionLabel = eff.key.id.toString(),
                                duration = SnackBarDuration.Normal
                            )

                            if (result == SnackBarResult.ActionPerformed) {
                                send(Msg.Ui.OnSnackUndoRemoveNotesClicked)
                            }
                        }
                    }
                }
            }
        }
    }
}


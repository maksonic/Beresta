package ru.maksonic.beresta.screen.main.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.core.VibrationPerformer
import ru.maksonic.beresta.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.showForEdit
import ru.maksonic.beresta.feature.hidden_notes.api.HiddenNotesApi
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter
import ru.maksonic.beresta.screen.main.core.Eff
import ru.maksonic.beresta.screen.main.core.MainSandbox
import ru.maksonic.beresta.screen.main.core.Msg

/**
 * @Author maksonic on 22.06.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Container(
    router: MainScreenRouter,
    notesListApi: NotesApi.Ui.List = koinInject(),
    counterApi: TopBarCounterApi.Ui = koinInject(),
    chipsRowApi: FoldersApi.Ui.ChipsRow = koinInject(),
    chipsDialogApi: FoldersApi.Ui.AddChipDialog = koinInject(),
    sandbox: MainSandbox = koinViewModel(),
    listSortUiState: SortingSheetApi.Ui = koinInject(),
    hiddenNotesEnterPasswordDialog: HiddenNotesApi.Ui.EnterPasswordDialog = koinInject(),
    vibrationPerformer: VibrationPerformer = koinInject(),
) {
    val model = sandbox.model.collectAsStateWithLifecycle()

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        modalBottomSheetState = model.value.modalSheet.state,
        hideSheet = { sandbox.send(Msg.Inner.HiddenModalBottomSheet) },
        chipsDialogApi = chipsDialogApi,
        hiddenNotesEnterPasswordDialog = hiddenNotesEnterPasswordDialog,
        )

    Content(
        model = model,
        send = sandbox::send,
        notesListApi = notesListApi,
        counterApi = counterApi,
        chipsRowApi = chipsRowApi,
        chipsDialogApi = chipsDialogApi,
        listSortUiState = listSortUiState,
        hiddenNotesEnterPasswordDialog = hiddenNotesEnterPasswordDialog,
        vibrationPerformer = vibrationPerformer
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: MainScreenRouter,
    modalBottomSheetState: SheetState,
    chipsDialogApi: FoldersApi.Ui.AddChipDialog,
    hiddenNotesEnterPasswordDialog: HiddenNotesApi.Ui.EnterPasswordDialog,
    hideSheet: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.HideModalSheet -> {
                scope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
                    if (!modalBottomSheetState.isVisible) {
                        hideSheet()
                    }
                }
            }

            is Eff.NavigateToEditNote -> {
                focusManager.clearFocus().let { router.toNoteEditor(eff.id) }
            }

            is Eff.NavigateToSettings -> router.toSettings()
            is Eff.NavigateToFolders -> router.toFoldersList(eff.ids)
            is Eff.NavigateToTrash -> router.toTrash()
            is Eff.NavigateToHiddenNotes -> router.toHiddenNotes()
            is Eff.ShowAddNewChipDialog -> chipsDialogApi.sharedUiState.showForEdit(0L)
            is Eff.ShowedHiddenNotesEnterPasswordDialog -> {
                hiddenNotesEnterPasswordDialog.visibility.update(true)
            }
        }
    }
}
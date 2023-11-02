package ru.maksonic.beresta.screen.folders.ui

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBarDuration
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBarHostState
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBarResult
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersChipsRowUiApi
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersListUiApi
import ru.maksonic.beresta.feature.folders_list.ui.api.LocalCurrentSelectedFolderState
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.HiddenNotesDialogUiApi
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.LocalListFoldersSortState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.SortingSheetUiApi
import ru.maksonic.beresta.feature.ui.add_folder_dialog.api.AddFolderDialogUiApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.routes.FoldersRouter
import ru.maksonic.beresta.platform.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.screen.folders.core.Eff
import ru.maksonic.beresta.screen.folders.core.FoldersScreenSandbox
import ru.maksonic.beresta.screen.folders.core.Msg

/**
 * @Author maksonic on 09.10.2023
 */
internal typealias Send = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Container(
    router: FoldersRouter,
    sandbox: FoldersScreenSandbox = koinViewModel(),
    foldersListApi: FoldersListUiApi = koinInject(),
    hiddenNotesPinInputDialogUiApi: HiddenNotesDialogUiApi.PinInputDialog = koinInject(),
    currentFolderStoreUiApi: FoldersChipsRowUiApi.CurrentSelectedFolderStore = koinInject(),
    addFolderDialogApi: AddFolderDialogUiApi = koinInject(),
    sortingSheetApi: SortingSheetUiApi = koinInject()
) {
    val model by sandbox.model.collectAsStateWithLifecycle()
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = model.modalSheet.skipPartiallyExpanded
    )

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        send = sandbox::send,
        addFolderDialogApi = addFolderDialogApi,
        modalBottomSheetState = modalBottomSheetState,
        snackState = model.snackState,
        hideModalSheet = { sandbox.send(Msg.Inner.HiddenModalBottomSheet) }
    )

    CompositionLocalProvider(
        LocalListFoldersSortState provides model.sortState,
        LocalCurrentSelectedFolderState provides currentFolderStoreUiApi.id.value
    ) {
        Content(
            model = model,
            send = sandbox::send,
            addFolderDialogApi = addFolderDialogApi,
            foldersListApi = foldersListApi,
            hiddenNotesPinInputDialogUiApi = hiddenNotesPinInputDialogUiApi,
            sortingSheetApi = sortingSheetApi,
            modalBottomSheetState = modalBottomSheetState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: FoldersRouter,
    send: Send,
    addFolderDialogApi: AddFolderDialogUiApi,
    modalBottomSheetState: SheetState,
    snackState: SnackBarHostState,
    hideModalSheet: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackScope = rememberCoroutineScope()
    // Snack messages
    val removedFoldersPrefix = text.folders.hintRemovedFoldersCount

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.AddNewFolder -> addFolderDialogApi.addFolder()
            is Eff.UpdateFolder -> {
                Log.e("AAA", "${eff.id}")

                addFolderDialogApi.updateFolder(eff.id)
            }
            is Eff.HideModalSheet -> {
                scope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
                    if (!modalBottomSheetState.isVisible) {
                        hideModalSheet()
                    }
                }
            }

            is Eff.NavigateToHiddenNotes -> router.toHiddenNotes()

            is Eff.ShowSnackBar -> {
                snackScope.launch {
                    val result = snackState.showSnackBar(
                        message = "$removedFoldersPrefix ${eff.message}",
                        actionLabel = "Undo remove selected folders",
                        duration = SnackBarDuration.Normal
                    )

                    if (result == SnackBarResult.ActionPerformed) {
                        send(Msg.Ui.OnSnackUndoRemoveFoldersClicked)
                    }
                }
            }
        }
    }
}
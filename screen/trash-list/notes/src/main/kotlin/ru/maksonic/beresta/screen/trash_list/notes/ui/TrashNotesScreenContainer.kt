package ru.maksonic.beresta.screen.trash_list.notes.ui

import androidx.activity.compose.BackHandler
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.navigation.router.router.trash.TrashNotesScreenRouter
import ru.maksonic.beresta.screen.trash_list.notes.core.Eff
import ru.maksonic.beresta.screen.trash_list.notes.core.Msg
import ru.maksonic.beresta.screen.trash_list.notes.core.NotesTrashSandbox
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle

/**
 * @Author maksonic on 30.05.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TrashNotesScreenContainer(
    router: TrashNotesScreenRouter,
    notesListApi: NotesListApi.Ui = get(),
    topBarCounterApi: TopBarCounterApi.Ui = get(),
    sandbox: NotesTrashSandbox = koinViewModel(),
) {
    val model = sandbox.model.collectAsStateWithLifecycle()

    BackHandler(model.value.isSelectionState) {
            sandbox.send(Msg.Ui.CancelSelectionState)
    }

    BackHandler(model.value.modalBottomSheetState.isVisible) {
        sandbox.send(Msg.Ui.HideModalBottomSheet)
    }



    HandleUiEffects(
        effects = sandbox.effects,
        modalBottomSheetState = model.value.modalBottomSheetState,
        hideSheet = { sandbox.send(Msg.Inner.UpdatedModalSheetState(false))},
        router = router
    )

    TrashNotesScreenContent(
        model = model,
        send = sandbox::send,
        notesListApi = notesListApi,
        topBarCounterApi = topBarCounterApi,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    modalBottomSheetState: SheetState,
    hideSheet: () -> Unit,
    router: TrashNotesScreenRouter
) {
    val scope = rememberCoroutineScope()

    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.NavigateToTrashedFoldersList -> {
                hideSheet()
                router.toTrashedFoldersList()
            }
            is Eff.HideModalSheet ->{
                scope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
                    if (!modalBottomSheetState.isVisible) {
                        hideSheet()
                    }
                }
            }
        }
    }
}
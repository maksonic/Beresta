package ru.maksonic.beresta.screen.hidden_notes.ui

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
import ru.maksonic.beresta.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.navigation.router.router.HiddenNotesScreenRouter
import ru.maksonic.beresta.screen.hidden_notes.core.Eff
import ru.maksonic.beresta.screen.hidden_notes.core.HiddenNotesSandbox
import ru.maksonic.beresta.screen.hidden_notes.core.Msg

/**
 * @Author maksonic on 18.07.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Container(
    router: HiddenNotesScreenRouter,
    notesListApi: NotesApi.Ui.List = koinInject(),
    counterApi: TopBarCounterApi.Ui = koinInject(),
    sortedSheetApi: SortingSheetApi.Ui = koinInject(),
    sandbox: HiddenNotesSandbox = koinViewModel(),
    listSortUiState: SortingSheetApi.Ui = koinInject()
) {
    val model = sandbox.model.collectAsStateWithLifecycle()

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        modalBottomSheetState = model.value.modalSheet.state,
        hideSheet = { sandbox.send(Msg.Inner.HiddenModalBottomSheet) },
    )

    Content(
        model = model,
        send = sandbox::send,
        notesListApi = notesListApi,
        counterApi = counterApi,
        sortedSheetApi = sortedSheetApi,
        listSortUiState = listSortUiState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: HiddenNotesScreenRouter,
    modalBottomSheetState: SheetState,
    hideSheet: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
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
        }
    }
}
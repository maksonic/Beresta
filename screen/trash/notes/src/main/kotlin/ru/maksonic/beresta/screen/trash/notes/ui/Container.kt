package ru.maksonic.beresta.screen.trash.notes.ui

import androidx.activity.compose.BackHandler
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
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NotesCardUiApi
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesListUiApi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.LocalNoteCardState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.LocalListNotesSortState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.NotesSortUi
import ru.maksonic.beresta.navigation.router.routes.trash.TrashNotesRouter
import ru.maksonic.beresta.platform.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.screen.trash.notes.core.Eff
import ru.maksonic.beresta.screen.trash.notes.core.Msg
import ru.maksonic.beresta.screen.trash.notes.core.NotesTrashSandbox

/**
 * @Author maksonic on 30.05.2023
 */
internal typealias Send = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Container(
    router: TrashNotesRouter,
    sandbox: NotesTrashSandbox = koinViewModel(),
    notesListUiApi: NotesListUiApi = koinInject(),
    notesCardStateStoreUiApi: NotesCardUiApi.CardState = koinInject(),
) {
    val model by sandbox.model.collectAsStateWithLifecycle()
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = model.modalSheet.skipPartiallyExpanded
    )

    BackHandler(model.isSelection) {
        sandbox.send(Msg.Ui.CancelSelectionState)
    }

    HandleUiEffects(
        effects = sandbox.effects,
        modalBottomSheetState = modalBottomSheetState,
        hideSheet = { sandbox.send(Msg.Inner.UpdatedModalSheetState(false)) },
        router = router
    )

    CompositionLocalProvider(
        LocalListNotesSortState provides NotesSortUi.Default,
        LocalNoteCardState provides notesCardStateStoreUiApi.sharedState.value,
    ) {
        Content(model, sandbox::send, notesListUiApi, modalBottomSheetState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    modalBottomSheetState: SheetState,
    hideSheet: () -> Unit,
    router: TrashNotesRouter
) {
    val scope = rememberCoroutineScope()

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.NavigateToTrashedFoldersList -> {
                hideSheet()
                router.toTrashedFoldersList()
            }

            is Eff.HideModalSheet -> {
                scope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
                    if (!modalBottomSheetState.isVisible) {
                        hideSheet()
                    }
                }
            }
        }
    }
}
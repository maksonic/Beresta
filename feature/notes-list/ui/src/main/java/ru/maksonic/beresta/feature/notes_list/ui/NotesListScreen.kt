package ru.maksonic.beresta.feature.notes_list.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelSharedState
import ru.maksonic.beresta.feature.notes_list.api.collection.FilterChipsCollection
import ru.maksonic.beresta.feature.notes_list.api.collection.NotesCollection
import ru.maksonic.beresta.feature.notes_list.api.feature.NotesListFeature
import ru.maksonic.beresta.feature.notes_list.api.feature.NotesSharedState
import ru.maksonic.beresta.feature.notes_list.ui.core.Eff
import ru.maksonic.beresta.feature.notes_list.ui.core.Model
import ru.maksonic.beresta.feature.notes_list.ui.core.Msg
import ru.maksonic.beresta.feature.notes_list.ui.core.NotesListSandbox
import ru.maksonic.beresta.feature.notes_list.ui.state.EmptyNotesViewState
import ru.maksonic.beresta.feature.notes_list.ui.state.SuccessViewState
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.widget.LoadingViewState
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle

/**
 * @Author maksonic on 24.12.2022
 */
internal typealias SendMessage = (Msg) -> Unit

class NotesListScreen : NotesListFeature {
    private val mutableSharedNotesState = MutableStateFlow(NotesSharedState())
    override val state: StateFlow<NotesSharedState>
        get() = mutableSharedNotesState.asStateFlow()

    @Composable
    override fun Screen(router: MainScreenRouter) {
        val sandbox: NotesListSandbox = koinViewModel()
        val model = sandbox.model.collectAsState().value

        HandleEffects(effects = sandbox.effects, router)
        Content(model = model, send = sandbox::sendMsg)
    }

    @Composable
    private fun Content(model: Model, send: SendMessage) {
        send(Msg.Inner.SelectPanelVisibility(model.isSelectionState))

        BackHandler(model.isSelectionState) {
            send(Msg.Ui.CancelSelection)
        }

        when {
            model.base.isLoading -> LoadingViewState()
            model.notes.isEmpty() -> EmptyNotesViewState(mutableSharedNotesState)
            model.base.isSuccessLoading -> {
                SuccessViewState(
                    model = model,
                    send = send,
                    notes = NotesCollection(model.notes),
                    filters = FilterChipsCollection(model.chipsNotesFilter),
                    mutableSharedNotesState = mutableSharedNotesState,
                )
            }
        }
    }

    @Composable
    private fun HandleEffects(effects: Flow<Eff>, router: MainScreenRouter) {
        HandleEffectsWithLifecycle(effects) { eff ->
            when (eff) {
                is Eff.ShowNoteForEdit -> router.toNoteEditor(eff.id)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun NotesListScreenPreview() {
        BerestaTheme {
            NotesListScreen().Content(model = Model(bottomPanelState = BottomPanelSharedState()), {} )
        }
    }
}


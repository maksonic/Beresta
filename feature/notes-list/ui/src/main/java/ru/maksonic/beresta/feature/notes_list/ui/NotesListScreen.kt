package ru.maksonic.beresta.feature.notes_list.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.*
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.notes_list.api.collection.NotesCollection
import ru.maksonic.beresta.feature.notes_list.api.NotesListFeature
import ru.maksonic.beresta.feature.notes_list.api.NotesSharedState
import ru.maksonic.beresta.feature.notes_list.api.collection.FilterChipsCollection
import ru.maksonic.beresta.feature.notes_list.ui.core.Feature
import ru.maksonic.beresta.feature.notes_list.ui.core.NotesListSandbox
import ru.maksonic.beresta.feature.notes_list.ui.state.SuccessViewState
import ru.maksonic.beresta.ui.widget.LoadingViewState
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle

/**
 * @Author maksonic on 24.12.2022
 */
internal typealias SendMessage = (Feature.Msg) -> Unit

class NotesListScreen : NotesListFeature {
    private val mutableSharedNotesState = MutableStateFlow(NotesSharedState())
    override val state: StateFlow<NotesSharedState>
        get() = mutableSharedNotesState.asStateFlow()

    @Composable
    override fun Screen() {
        val sandbox: NotesListSandbox = koinViewModel()
        val model = sandbox.model.collectAsState().value

        HandleEffects(effects = sandbox.effects)
        Content(model = model, msg = sandbox::sendMsg)
    }

    @Composable
    private fun Content(
        model: Feature.Model,
        msg: (Feature.Msg) -> Unit,
    ) {
        msg(Feature.Msg.Inner.SelectPanelVisibility(model.isSelectionState))

        BackHandler(model.isSelectionState) {
            msg(Feature.Msg.Ui.CancelNotesSelection)
        }

        when {
            model.base.isLoading -> {
                LoadingViewState()
            }
            model.notes.isEmpty() -> {
                EmptyNotesViewState()
            }
            model.base.isSuccessLoading -> {
                SuccessViewState(
                    notes = NotesCollection(model.notes),
                    filters = FilterChipsCollection(model.chipsNotesFilter),
                    onFilterClick = { index -> msg(Feature.Msg.Ui.OnSelectNotesFilter(index)) },
                    msg = msg,
                    showMainTopBar = { isShow ->
                        mutableSharedNotesState.update { state ->
                            state.copy(isShowMainToolbar = isShow)
                        }
                    },
                    showBottomPanel = { isShow ->
                        mutableSharedNotesState.update { state ->
                            state.copy(isShowBottomPanel = isShow)
                        }
                    },
                    isColoredTopBar = { isColored ->
                        mutableSharedNotesState.update { state ->
                            state.copy(isColoredTopBar = isColored)
                        }
                    },
                    isSelectionState = { model.isSelectionState },
                    selectedCounter = { model.selectedCount }
                )
            }
        }

    }

    @Composable
    private fun HandleEffects(effects: Flow<Feature.Eff>) {
        HandleEffectsWithLifecycle(effects) { eff ->
            when (eff) {
                is Feature.Eff.ShowNoteForEdit -> {

                }
            }
        }
    }
}

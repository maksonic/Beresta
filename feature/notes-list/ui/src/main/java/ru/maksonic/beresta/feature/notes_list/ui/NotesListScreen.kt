package ru.maksonic.beresta.feature.notes_list.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.*
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.notes_list.api.NotesListFeature
import ru.maksonic.beresta.feature.notes_list.api.NotesSharedState
import ru.maksonic.beresta.feature.notes_list.ui.core.Feature
import ru.maksonic.beresta.feature.notes_list.ui.core.NotesListSandbox
import ru.maksonic.beresta.feature.notes_list.ui.state.SuccessViewState
import ru.maksonic.beresta.ui.widget.LoadingViewState
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle

/**
 * @Author maksonic on 24.12.2022
 */
class NotesListScreen : NotesListFeature {
    private val mutableSharedNotesState = MutableStateFlow(NotesSharedState())
    override val state: StateFlow<NotesSharedState>
        get() = mutableSharedNotesState.asStateFlow()

    @Composable
    override fun Screen() {
        val sandbox: NotesListSandbox = koinViewModel()
        val model = sandbox.model.collectAsState().value
        val notesScrollState = rememberLazyListState()
        val isScrolledTop =
            remember { derivedStateOf { notesScrollState.firstVisibleItemScrollOffset > 0 } }

        SideEffect {
            mutableSharedNotesState.update { state ->
                state.copy(isTopListScrollState = !isScrolledTop.value)
            }
        }
        HandleEffects(effects = sandbox.effects)

        Content(model = model, msg = sandbox::sendMsg, notesScrollState, !isScrolledTop.value)
    }

    @Composable
    private fun Content(
        model: Feature.Model,
        msg: (Feature.Msg) -> Unit,
        notesScrollState: LazyListState,
        isScrolledTop: Boolean
    ) {

        when {
            model.base.isLoading -> LoadingViewState()
            model.notes.isEmpty() -> EmptyNotesViewState()
            model.base.isSuccessLoading -> {
                SuccessViewState(
                    notes = model.notes,
                    filters = model.notesFilter,
                    notesScrollState = notesScrollState,
                    isScrolledTop = { isScrolledTop },
                    onFilterClick = {/* index -> msg(Feature.Msg.Ui.OnSelectNotesFilter(index)) */ },
                    msg = msg,
                    isSelectionState = model.isSelectionState,
                    // updateBackgroundColor = { isTopScroll -> updateBackgroundColor(isTopScroll) },
                )
            }
        }
    }

    private fun updateBackgroundColor(isScrolledTop: Boolean) {
        mutableSharedNotesState.update { state ->
            state.copy(isTopListScrollState = isScrolledTop)
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
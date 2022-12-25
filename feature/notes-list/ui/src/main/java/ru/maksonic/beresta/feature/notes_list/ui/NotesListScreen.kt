package ru.maksonic.beresta.feature.notes_list.ui

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.notes_list.api.NotesListFeature
import ru.maksonic.beresta.feature.notes_list.ui.core.Feature
import ru.maksonic.beresta.feature.notes_list.ui.core.NotesListSandbox
import ru.maksonic.beresta.ui.widget.LoadingViewState
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle

/**
 * @Author maksonic on 24.12.2022
 */
class NotesListScreen : NotesListFeature {

    @Composable
    override fun Screen() {
        val sandbox: NotesListSandbox = koinViewModel()
        val model = sandbox.model.collectAsState().value

        HandleEffects(effects = sandbox.effects)

        Content(model = model, msg = sandbox::sendMsg)
    }

    @Composable
    private fun Content(model: Feature.Model, msg: (Feature.Msg) -> Unit) {
        val lazyListState = rememberLazyListState()
        val firstVisibleItem = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }

        when {
            model.base.isLoading -> LoadingViewState()
            model.base.isSuccessLoading -> {
                SuccessViewState(
                    notes = model.notes,
                    msg = msg,
                    isSelectionState = model.isSelectionState,
                    lazyListState = lazyListState,
                    firstVisibleItem = firstVisibleItem,
                )
            }
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
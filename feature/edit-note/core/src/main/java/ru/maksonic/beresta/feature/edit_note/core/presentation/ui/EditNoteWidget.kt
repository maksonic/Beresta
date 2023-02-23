package ru.maksonic.beresta.feature.edit_note.core.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.edit_note.core.EditNoteSandbox
import ru.maksonic.beresta.feature.edit_note.core.Eff
import ru.maksonic.beresta.feature.edit_note.core.Msg
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle

/**
 * @Author maksonic on 23.02.2023
 */
internal typealias SendMessage = (Msg) -> Unit

class EditNoteWidget : EditNoteApi.Ui {

    @Composable
    override fun Widget(isNotesScrollTop: () -> Boolean, modifier: Modifier) {
        Content(isScrollTop = isNotesScrollTop)
    }
}

@Composable
private fun Content(
    isScrollTop: () -> Boolean,
    searchBar: SearchBarApi.Ui = get(),
    sandbox: EditNoteSandbox = koinViewModel()
) {
    val model = sandbox.model.collectAsState().value

    HandleUiEffects(
        effects = sandbox.effects,
        showSearchBar = { searchBar.searchBarVisibilityState.update { true } },
        hideSearchBar = { searchBar.searchBarVisibilityState.update { false } }
    )

    ExpandableFabButton(model = model, send = sandbox::sendMsg, isScrollTop = isScrollTop)
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    showSearchBar: () -> Unit,
    hideSearchBar: () -> Unit,
) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.ShowSearchBar -> showSearchBar()
            is Eff.HideSearchBar -> hideSearchBar()
        }
    }
}


package ru.maksonic.beresta.screen.main.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.notes_list.api.NotesListApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter
import ru.maksonic.beresta.screen.main.presentation.core.Eff
import ru.maksonic.beresta.screen.main.presentation.core.MainSandbox
import ru.maksonic.beresta.screen.main.presentation.core.Msg
import ru.maksonic.beresta.screen.main.presentation.ui.widget.MainBottomIdlePanelWidget
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.LoadingViewState
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.isScrollUp

/**
 * @Author maksonic on 15.02.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
fun MainScreen(
    router: MainScreenRouter,
) {
    Content(router)
}

@Composable
private fun Content(
    router: MainScreenRouter,
    notesList: NotesListApi.Ui = get(),
    searchBar: SearchBarApi.Ui = get(),
    editNote: EditNoteApi.Ui = get(),
    sandbox: MainSandbox = koinViewModel(),
    modifier: Modifier = Modifier
) {
    HandleUiEffects(sandbox.effects, router)

    val model = sandbox.model.collectAsState().value
    val scrollState = rememberLazyListState()
    val isScrollTop = scrollState.isScrollUp()
    val statusBarColor = background

    Box(
        modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
    ) {
        val state = remember { mutableStateOf(false) }

        when {
            model.base.isLoading -> LoadingViewState()
            model.notes.data.isEmpty() -> notesList.EmptyNotesListWidget(modifier)
            model.base.isSuccessLoading -> {
                notesList.FetchedNotesWidget(
                    notes = model.notes,
                    scrollState = { scrollState },
                    modifier = modifier
                )
            }
        }

        Column(modifier.fillMaxSize()) {
            SystemStatusBar(backgroundColor = { statusBarColor })
            Spacer(modifier.weight(1f))
            MainBottomIdlePanelWidget(sandbox::sendMsg, isScrollTop = { isScrollTop })
        }

        editNote.Widget(isNotesScrollTop = { isScrollTop }, modifier)

        AnimateFadeInOut(!state.value) {
            searchBar.Widget(notesCollection = model.notes, modifier = modifier)
        }
    }
}

@Composable
private fun HandleUiEffects(effects: Flow<Eff>, router: MainScreenRouter) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateToAddNewNote -> router.toNoteEditor(0)
            is Eff.NavigateToSettings -> router.toSettings()
            is Eff.NavigateToTrash -> router.toTrash()
        }
    }
}



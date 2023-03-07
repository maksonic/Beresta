package ru.maksonic.beresta.screen.main.presentation.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.widget.LoadingViewState
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.isScrollUp
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItem

/**
 * @Author maksonic on 15.02.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
fun MainScreen(router: MainScreenRouter) {
    Content(router = router)
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    notesList: NotesListApi.Ui = get(),
    searchBar: SearchBarApi.Ui = get(),
    editNote: EditNoteApi.Ui = get(),
    sandbox: MainSandbox = koinViewModel(),
    router: MainScreenRouter
) {
    HandleUiEffects(sandbox.effects, router)

    val model = sandbox.model.collectAsStateWithLifecycle().value
    val scrollState = rememberLazyListState()
    val isScrollUp = scrollState.isScrollUp()
    val isVisibleFirstNote = scrollState.isVisibleFirstItem()
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val searchBarVisibility = remember { mutableStateOf(false) }
    val searchBarBackgroundColor =
        animateColorAsState(if (isVisibleFirstNote.value) background else tertiaryContainer)
    val searchBarColor = animateColorAsState(
        if (isVisibleFirstNote.value) surfaceVariant else onTertiaryContainer
    )
    val searchBarScrollOffset = animateDpAsState(
        if (isVisibleFirstNote.value) 0.dp else {
            if (isScrollUp) 0.dp else -Theme.widgetSize.topBarMediumHeight.plus(statusBarHeight)
        },
        animationSpec = tween()
    )

    Box(
        modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
    ) {
        when {
            model.base.isLoading -> LoadingViewState()
            model.notes.data.isEmpty() -> notesList.EmptyNotesListWidget(modifier)
            model.base.isSuccessLoading -> {
                notesList.FetchedNotesWidget(
                    notes = model.notes,
                    scrollState = { scrollState },
                    router = router,
                    modifier = Modifier
                )
            }
        }

        MainBottomIdlePanelWidget(sandbox::sendMsg, isScrollUp = { isScrollUp })

        editNote.Widget(isNotesScrollUp = { isScrollUp }, modifier)

        AnimateFadeInOut(!searchBarVisibility.value) {
            searchBar.Widget(
                notesCollection = model.notes,
                searchTopBarBackground = { searchBarBackgroundColor.value },
                searchBarCollapsedColor = { searchBarColor.value },
                modifier = modifier.graphicsLayer {
                    translationY = searchBarScrollOffset.value.toPx()
                }
            )
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
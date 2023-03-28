package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.notes_list.api.NotesListApi
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.core.presentation.Msg
import ru.maksonic.beresta.feature.search_bar.core.presentation.SearchBarSandbox
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 21.02.2023
 */
internal typealias SendMessage = (Msg) -> Unit

class SearchBarWidget : SearchBarApi.Ui {
    override val searchBarVisibilityState = MutableStateFlow(true)
    private val searchBarVisibility = searchBarVisibilityState.asStateFlow()

    @Composable
    override fun Widget(
        notesCollection: NoteUi.Collection,
        isVisibleFirstNote: () -> Boolean,
        isSelectedNotesState: () -> Boolean,
        isScrollInProgress: () -> Boolean,
        modifier: Modifier
    ) {
        val isVisible = searchBarVisibility.collectAsStateWithLifecycle().value
        AnimateFadeInOut(visible = isVisible) {
            Content(
                notesCollection = notesCollection,
                isVisibleFirstNote = isVisibleFirstNote,
                isSelectedNotesState = isSelectedNotesState,
                isScrollInProgress = isScrollInProgress,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    sandbox: SearchBarSandbox = koinViewModel(),
    notesList: NotesListApi.Ui = get(),
    notesCollection: NoteUi.Collection,
    isVisibleFirstNote: () -> Boolean,
    isScrollInProgress: () -> Boolean,
    isSelectedNotesState: () -> Boolean
) {
    sandbox.send(Msg.Inner.FetchedNotesCollection(notesCollection))
    val model = sandbox.model.collectAsStateWithLifecycle().value

    Box(modifier.fillMaxSize()) {
        BackgroundCollapsedSearchBarWithUserIcon(isSelectedNotesState)

        SearchBarOverflowContainer(
            model = model,
            send = sandbox::send,
            notesList = notesList,
            isVisibleFirstNote = isVisibleFirstNote,
            isSelectedNotesState = isSelectedNotesState,
            isScrollInProgress = isScrollInProgress,
            modifier = Modifier.navigationBarsPadding()
        )
    }
}



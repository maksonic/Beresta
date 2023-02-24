package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.notes_list.api.NotesListApi
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesCollection
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
    override fun Widget(notesCollection: NotesCollection, modifier: Modifier) {
        val isVisible = searchBarVisibility.collectAsState().value
        AnimateFadeInOut(visible = isVisible) {
            Content(notesCollection = notesCollection, modifier = modifier)
        }
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    sandbox: SearchBarSandbox = koinViewModel(),
    notesCollection: NotesCollection,
    notesList: NotesListApi.Ui = get(),
) {
    sandbox.sendMsg(Msg.Inner.FetchedNotesCollection(notesCollection))
    val model = sandbox.model.collectAsState().value

    Box(modifier.fillMaxSize()) {
        BackgroundCollapsedSearchBarWithUserIcon()

        SearchBarOverflowContainer(
            model = model,
            send = sandbox::sendMsg,
            notesList = notesList,
            modifier = modifier
        )
    }
}



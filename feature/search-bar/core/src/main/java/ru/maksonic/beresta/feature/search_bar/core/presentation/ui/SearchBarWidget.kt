package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.notes_list.api.NotesListApi
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesCollection
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.core.presentation.Msg
import ru.maksonic.beresta.feature.search_bar.core.presentation.SearchBarSandbox

/**
 * @Author maksonic on 21.02.2023
 */
internal typealias SendMessage = (Msg) -> Unit

class SearchBarWidget : SearchBarApi.Ui {
    @Composable
    override fun Widget(notesCollection: NotesCollection, modifier: Modifier) {
        Content(notesCollection = notesCollection)
    }
}

@Composable
private fun Content(
    sandbox: SearchBarSandbox = koinViewModel(),
    notesCollection: NotesCollection,
    notesList: NotesListApi.Ui = get(),
    modifier: Modifier = Modifier
) {
    sandbox.sendMsg(Msg.Inner.FetchedNotesCollection(notesCollection))
    val model = sandbox.model.collectAsState().value

    Box(modifier.fillMaxSize()) {
        BackgroundCollapsedSearchBarWithUserIcon()
        SearchBar(
            model = model,
            send = sandbox::sendMsg,
            notesList = notesList
        )
    }
}



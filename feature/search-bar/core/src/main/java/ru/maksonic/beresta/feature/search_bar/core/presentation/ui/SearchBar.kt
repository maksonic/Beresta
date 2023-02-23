package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes_list.api.NotesListApi
import ru.maksonic.beresta.feature.search_bar.core.presentation.Model
import ru.maksonic.beresta.feature.search_bar.core.presentation.Msg

/**
 * @Author maksonic on 22.02.2023
 */
@Composable
internal fun SearchBar(
    model: Model,
    send: SendMessage,
    notesList: NotesListApi.Ui,
    modifier: Modifier = Modifier
) {

    BackHandler(model.isExpandedBar) {
        if (model.isExpandedBar) {
            send(Msg.Ui.OnCollapseSearchBarClicked)
        }
    }

    BoxWithConstraints(modifier.fillMaxSize()) {
        val boxScope = this

        SearchBarOverflowContainer(
            isExpanded = { model.isExpandedBar },
            send = send,
            boxScope = boxScope,
            modifier = modifier
        ) {
            SearchBarContent(model = model, send = send, notesList = notesList)
        }
    }
}
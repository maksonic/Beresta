package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.notes_list.api.NotesListApi
import ru.maksonic.beresta.feature.search_bar.core.presentation.Model
import ru.maksonic.beresta.feature.search_bar.core.presentation.Msg

/**
 * @Author maksonic on 22.02.2023
 */
@Composable
internal fun SearchBarOverflowContainer(
    model: Model,
    send: SendMessage,
    notesList: NotesListApi.Ui,
    searchBarCollapsedColor: () -> Color,
    modifier: Modifier,
) {

    BackHandler(model.isExpandedBar) {
        if (model.isExpandedBar) {
            send(Msg.Ui.OnCollapseSearchBarClicked)
        }
    }

    BoxWithConstraints(modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        val boxScope = this
        ExpandableSearchBar(
            model = model,
            send = send,
            boxScope = boxScope,
            notesList = notesList,
            searchBarCollapsedColor = searchBarCollapsedColor
        )
    }
}

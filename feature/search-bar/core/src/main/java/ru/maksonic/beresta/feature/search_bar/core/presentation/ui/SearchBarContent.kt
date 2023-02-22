package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.notes_list.api.NotesListApi
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.search_bar.core.presentation.Model

/**
 * @Author maksonic on 22.02.2023
 */
@Composable
internal fun SearchBarContent(
    model: Model,
    notesList: NotesListApi.Ui,
    send: SendMessage,
) {
    if (model.isExpandedBar)
        ExpandedSearchQueryFiled(model = model, send = send, notesList = notesList)
    else
        SearchBarCollapsedContent()

}
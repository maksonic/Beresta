package ru.maksonic.beresta.feature.notes_list.core.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes_list.api.NotesListApi
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesCollection
import ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.EmptyNotesWidgetContent
import ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.FetchedNotesWidgetContent
import ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.NoteListItemContent

/**
 * @Author maksonic on 21.02.2023
 */
class NotesListWidget : NotesListApi.Ui {

    @Composable
    override fun FetchedNotesWidget(notes: NotesCollection, modifier: Modifier) {
        FetchedNotesWidgetContent(notes)
    }

    @Composable
    override fun EmptyNotesListWidget(modifier: Modifier) {
        EmptyNotesWidgetContent()
    }

    @Composable
    override fun NoteItem(
        onNoteClicked: () -> Unit,
        onNoteLongClicked: () -> Unit,
        note: NoteUi,
        modifier: Modifier
    ) {
        NoteListItemContent(onNoteClicked, onNoteLongClicked, note, modifier)
    }
}


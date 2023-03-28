package ru.maksonic.beresta.feature.notes_list.core.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.maksonic.beresta.feature.notes_list.api.NotesListApi
import ru.maksonic.beresta.feature.notes_list.api.NotesListSharedScrollState
import ru.maksonic.beresta.feature.notes_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.notes_list.api.ui.MainBottomBarState
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.EmptyNotesWidgetContent
import ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.FetchedNotesWidgetContent
import ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.NoteListItemContent

/**
 * @Author maksonic on 21.02.2023
 */
class NotesListWidget : NotesListApi.Ui {
    private val mutableMainBottomBarState = MutableStateFlow(MainBottomBarState.IDLE)
    override val sharedMainBottomBarState = mutableMainBottomBarState.asStateFlow()

    @Composable
    override fun FetchedNotesWidget(
        notes: NoteUi.Collection,
        chips: FilterChipUi.Collection,
        onNoteClicked: (id: Long) -> Unit,
        onNoteLongPressed: (id: Long) -> Unit,
        onChipFilterClicked: (id: Int) -> Unit,
        sharedScroll: NotesListSharedScrollState,
    ) {
        FetchedNotesWidgetContent(
            notes = notes,
            chips = chips,
            onNoteClicked = onNoteClicked,
            onNoteLongPressed = onNoteLongPressed,
            onChipFilterClicked = onChipFilterClicked,
            sharedScroll = sharedScroll
        )
    }

    @Composable
    override fun EmptyNotesListWidget(modifier: Modifier) {
        EmptyNotesWidgetContent()
    }

    @Composable
    override fun NoteItem(
        onNoteClicked: (id: Long) -> Unit,
        onNoteLongClicked: (id: Long) -> Unit,
        note: NoteUi,
        modifier: Modifier
    ) {
        NoteListItemContent(onNoteClicked, onNoteLongClicked, note, modifier)
    }
}




package ru.maksonic.beresta.feature.notes.list.core

import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.list.api.ui.SortedNotes
import ru.maksonic.beresta.feature.notes.list.api.ui.sortByAlphabetAsc
import ru.maksonic.beresta.feature.notes.list.api.ui.sortByAlphabetDesk
import ru.maksonic.beresta.feature.notes.list.api.ui.sortByCreationDateAsc
import ru.maksonic.beresta.feature.notes.list.api.ui.sortByCreationDateDesk
import ru.maksonic.beresta.feature.notes.list.api.ui.sortUpdateDateAsc
import ru.maksonic.beresta.feature.notes.list.api.ui.sortUpdateDateDesk

/**
 * @Author maksonic on 26.05.2023
 */
class NotesFilterUpdater(list: List<NoteUi>, private val currentFolderId: Long) {
    companion object {
        private const val STICKY_START_FOLDER_ID = 1L
        private const val STICKY_END_FOLDER_ID = 2L
        private const val DEFAULT_NOTE_FOLDER_ID = 2L
    }

    private val filteredList: NoteUi.Collection = NoteUi.Collection(list.filter { note ->
        when (currentFolderId) {
            // When ID == 1L - Showed all notes.
            STICKY_START_FOLDER_ID -> true
            // When ID == 2L - Showed notes without folder (category).
            STICKY_END_FOLDER_ID -> note.folderId == DEFAULT_NOTE_FOLDER_ID
            else -> note.folderId == currentFolderId
        }
    })

    private fun NoteUi.Collection.applySort(
        current: SortedNotes,
        isSortPinned: Boolean
    ): NoteUi.Collection = this.copy(
        when (current) {
            SortedNotes.ALPHABET_ASC -> this.data.sortByAlphabetAsc(isSortPinned)
            SortedNotes.ALPHABET_DESC -> this.data.sortByAlphabetDesk(isSortPinned)
            SortedNotes.DATE_CREATION_ASC -> this.data.sortByCreationDateAsc(isSortPinned)
            SortedNotes.DATE_CREATION_DESC -> this.data.sortByCreationDateDesk(isSortPinned)
            SortedNotes.DATE_UPDATE_ASC -> this.data.sortUpdateDateAsc(isSortPinned)
            SortedNotes.DATE_UPDATE_DESC -> this.data.sortUpdateDateDesk(isSortPinned)
        }
    )

    fun notesList(current: SortedNotes, isSortPinned: Boolean) =
        filteredList.applySort(current, isSortPinned)

    val isEmptyFilterList = filteredList.data.isEmpty()
}
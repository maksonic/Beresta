package ru.maksonic.beresta.feature.notes.ui

import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.sortByAlphabetAsc
import ru.maksonic.beresta.feature.notes.api.ui.sortByAlphabetDesk
import ru.maksonic.beresta.feature.notes.api.ui.sortByCreationDateAsc
import ru.maksonic.beresta.feature.notes.api.ui.sortByCreationDateDesk
import ru.maksonic.beresta.feature.notes.api.ui.sortUpdateDateAsc
import ru.maksonic.beresta.feature.notes.api.ui.sortUpdateDateDesk
import ru.maksonic.beresta.feature.sorting_sheet.api.Order
import ru.maksonic.beresta.feature.sorting_sheet.api.Sort

/**
 * @Author maksonic on 26.05.2023
 */
class NotesFilterUpdater(notes: List<NoteUi>, private val currentFolderId: Long) {
    companion object {
        private const val STICKY_START_FOLDER_ID = 1L
        private const val STICKY_END_FOLDER_ID = 2L
        private const val DEFAULT_NOTE_FOLDER_ID = 2L
    }

    private val filteredList: List<NoteUi> = notes.filter { note ->
        when (currentFolderId) {
            // When ID == 1L - Showed all notes.
            STICKY_START_FOLDER_ID -> true
            // When ID == 2L - Showed notes without folder (category).
            STICKY_END_FOLDER_ID -> note.folderId == DEFAULT_NOTE_FOLDER_ID
            else -> note.folderId == currentFolderId
        }
    }

    private fun List<NoteUi>.applySort(
        order: Order,
        sort: Sort,
        isSortPinned: Boolean
    ): List<NoteUi> = when (order) {
        Order.ASCENDING -> {
            when (sort) {
                Sort.ALPHABET -> this.sortByAlphabetAsc(isSortPinned)
                Sort.DATE_CREATION -> this.sortByCreationDateAsc(isSortPinned)
                Sort.DATE_UPDATE -> this.sortUpdateDateAsc(isSortPinned)
            }
        }

        Order.DESCENDING -> {
            when (sort) {
                Sort.ALPHABET -> this.sortByAlphabetDesk(isSortPinned)
                Sort.DATE_CREATION -> this.sortByCreationDateDesk(isSortPinned)
                Sort.DATE_UPDATE -> this.sortUpdateDateDesk(isSortPinned)
            }
        }
    }

    fun list(order: Order, sort: Sort, isSortPinned: Boolean) =
        filteredList.applySort(order, sort, isSortPinned)

    val isEmptyFilterList = filteredList.isEmpty()
}
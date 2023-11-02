package ru.maksonic.beresta.feature.notes_list.ui.api.list

import ru.maksonic.beresta.common.core.ui.sorting.FilterDataSorter
import ru.maksonic.beresta.common.core.ui.sorting.Order
import ru.maksonic.beresta.common.core.ui.sorting.Sort
import ru.maksonic.beresta.common.core.ui.sorting.isAscending
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi

/**
 * @Author maksonic on 16.10.2023
 */
class NotesFilterSorter(
    list: List<NoteUi>,
    order: Order,
    isSortPinned: Boolean,
    private val sort: Sort,
    private val currentFolderId: Long
) : FilterDataSorter<NoteUi> {
    companion object {
        private const val STICKY_START_FOLDER_ID = 1L
        private const val STICKY_END_FOLDER_ID = 2L
        private const val DEFAULT_NOTE_FOLDER_ID = 2L
    }

    private fun List<NoteUi>.applyFilter(): List<NoteUi> = this.filter { note ->
        when (currentFolderId) {
            // When ID == 1L - Showed all notes.
            STICKY_START_FOLDER_ID -> true
            // When ID == 2L - Showed notes without folder (category).
            STICKY_END_FOLDER_ID -> note.folderId == DEFAULT_NOTE_FOLDER_ID
            else -> note.folderId == currentFolderId
        }
    }

    private val pinnedSublist = list.filter { it.style.isPinned }.sortedWith(
        if (isSortPinned) {
            if (order.isAscending) compareBy {
                when (sort) {
                    Sort.ALPHABET -> it.title
                    Sort.DATE_CREATION -> it.dateCreationRaw
                    Sort.DATE_UPDATE -> it.dateLastUpdateRaw
                    Sort.TRASHED -> it.dateMovedToTrashRaw
                }
            }
            else compareByDescending {
                when (sort) {
                    Sort.ALPHABET -> it.title
                    Sort.DATE_CREATION -> it.dateCreationRaw
                    Sort.DATE_UPDATE -> it.dateLastUpdateRaw
                    Sort.TRASHED -> it.dateMovedToTrashRaw
                }
            }
        } else {
            compareByDescending { it.pinTime }
        }
    )

    private val notPinnedSublist = list.filter { !it.style.isPinned }.sortedWith(
        if (order.isAscending) {
            compareBy {
                when (sort) {
                    Sort.ALPHABET -> it.title
                    Sort.DATE_CREATION -> it.dateCreationRaw
                    Sort.DATE_UPDATE -> it.dateLastUpdateRaw
                    Sort.TRASHED -> it.dateMovedToTrashRaw
                }
            }
        } else {
            compareByDescending {
                when (sort) {
                    Sort.ALPHABET -> it.title
                    Sort.DATE_CREATION -> it.dateCreationRaw
                    Sort.DATE_UPDATE -> it.dateLastUpdateRaw
                    Sort.TRASHED -> it.dateMovedToTrashRaw
                }
            }
        }
    )

    override val sortedByFilterList get() = (pinnedSublist + notPinnedSublist).applyFilter()
    override val isEmptyList = sortedByFilterList.isEmpty()
}
package ru.maksonic.beresta.feature.notes.api.ui

import ru.maksonic.beresta.feature.sorting_sheet.api.Order
import ru.maksonic.beresta.feature.sorting_sheet.api.Sort
import ru.maksonic.beresta.feature.sorting_sheet.api.isAscending


/**
 * @Author maksonic on 10.07.2023
 */
class NotesSorter(
    list: List<NoteUi>,
    order: Order,
    isSortPinned: Boolean,
    private val sort: Sort,
    private val currentFolderId: Long
) {
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

    private val pinnedSublist = list.filter { it.isPinned }.sortedWith(
        if (isSortPinned) {
            if (order.isAscending) compareBy {
                when (sort) {
                    Sort.ALPHABET -> it.title
                    Sort.DATE_CREATION -> it.dateCreationRaw
                    Sort.DATE_UPDATE -> it.dateLastUpdateRaw
                }
            }
            else compareByDescending {
                when (sort) {
                    Sort.ALPHABET -> it.title
                    Sort.DATE_CREATION -> it.dateCreationRaw
                    Sort.DATE_UPDATE -> it.dateLastUpdateRaw
                }
            }
        } else {
            compareByDescending { it.pinTime }
        }
    )

    private val notPinnedSublist = list.filter { !it.isPinned }.sortedWith(
        if (order.isAscending) {
            compareBy {
                when (sort) {
                    Sort.ALPHABET -> it.title
                    Sort.DATE_CREATION -> it.dateCreationRaw
                    Sort.DATE_UPDATE -> it.dateLastUpdateRaw
                }
            }
        } else {
            compareByDescending {
                when (sort) {
                    Sort.ALPHABET -> it.title
                    Sort.DATE_CREATION -> it.dateCreationRaw
                    Sort.DATE_UPDATE -> it.dateLastUpdateRaw
                }
            }
        }
    )

    val sortedWithFilterList get() = (pinnedSublist + notPinnedSublist).applyFilter()
    val isEmptyList = sortedWithFilterList.isEmpty()
}
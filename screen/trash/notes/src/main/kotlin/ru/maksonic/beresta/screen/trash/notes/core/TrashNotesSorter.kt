package ru.maksonic.beresta.screen.trash.notes.core

import ru.maksonic.beresta.common.core.ui.sorting.FilterDataSorter
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi

/**
 * @Author maksonic on 20.10.2023
 */
class TrashNotesSorter(list: List<NoteUi>) : FilterDataSorter<NoteUi> {
    override val sortedByFilterList: List<NoteUi> =
        list.sortedByDescending { it.dateMovedToTrashRaw }

    override val isEmptyList: Boolean get() = sortedByFilterList.isEmpty()

}
package ru.maksonic.beresta.feature.notes_list.api.ui

import ru.maksonic.beresta.core.Mapper
import ru.maksonic.beresta.core.domain.DateFormatter
import ru.maksonic.beresta.feature.notes_list.api.domain.NoteDomain

/**
 * @Author maksonic on 21.02.2023
 */
class NoteUiMapper(private val dateFormatter: DateFormatter) : Mapper<NoteDomain, NoteUi> {
    override fun mapFrom(o: NoteUi) = NoteDomain(
        id = o.id,
        title = o.title,
        message = o.message,
        currentFolder = o.currentFolder,
        isPinned = o.isPinned,
        isMovedToTrash = o.isMovedToTrash
    )

    override fun mapTo(i: NoteDomain) = NoteUi(
        id = i.id,
        title = i.title,
        message = i.message,
        dateCreation = dateFormatter.fetchFormattedDate(i.dateCreation),
        currentFolder = i.currentFolder,
        isPinned = i.isPinned,
        isMovedToTrash = i.isMovedToTrash
    )
}
package ru.maksonic.beresta.feature.notes_list.ui.core

import ru.maksonic.beresta.base_domain.DateFormatter
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.domain.NoteDomain

/**
 * @Author maksonic on 24.01.2023
 */
class NotesUiMapperImpl(private val dateFormatter: DateFormatter) : NoteUiMapper {
    override fun mapFrom(o: NoteUi) =
        NoteDomain(id = o.id, title = o.title, message = o.message, isMovedToTrash = o.isMovedToTrash)

    override fun mapTo(i: NoteDomain) = NoteUi(
        id = i.id,
        title = i.title,
        message = i.message,
        dateCreation = dateFormatter.fetchFormattedDate(i.dateCreation),
        isMovedToTrash = i.isMovedToTrash
    )
}
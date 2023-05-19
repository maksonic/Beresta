package ru.maksonic.beresta.feature.notes.list.api.ui

import ru.maksonic.beresta.core.Mapper
import ru.maksonic.beresta.feature.notes.list.api.domain.NoteDomain
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi

/**
 * @Author maksonic on 21.02.2023
 */
class NoteUiMapper : Mapper<NoteDomain, NoteUi> {
    override fun mapTo(i: NoteDomain) = NoteUi(
        id = i.id,
        title = i.title,
        message = i.message,
        dateCreationRaw = i.dateCreation,
        dateCreation = "",
        currentFolder = i.currentFolder,
        isPinned = i.isPinned,
        pinTime = i.pinTime,
        isMovedToTrash = i.isMovedToTrash
    )

    override fun mapFrom(o: NoteUi) = NoteDomain(
        id = o.id,
        title = o.title,
        message = o.message,
        dateCreation = o.dateCreationRaw,
        currentFolder = o.currentFolder,
        isPinned = o.isPinned,
        pinTime = o.pinTime,
        isMovedToTrash = o.isMovedToTrash
    )
}
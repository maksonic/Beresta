package ru.maksonic.beresta.feature.notes.api.ui

import ru.maksonic.beresta.core.Mapper
import ru.maksonic.beresta.feature.notes.api.domain.NoteDomain

/**
 * @Author maksonic on 21.02.2023
 */
class NoteUiMapper : Mapper<NoteDomain, NoteUi> {
    override fun mapTo(i: NoteDomain) = NoteUi(
        id = i.id,
        title = i.title,
        message = i.message,
        dateCreationRaw = i.dateCreationRaw,
        dateMovedToTrashRaw = i.dateMovedToTrash,
        dateLastUpdateRaw = i.dateLastUpdateRaw,
        isPinned = i.isPinned,
        isHidden = i.isHidden,
        pinTime = i.pinTime,
        isMovedToTrash = i.isMovedToTrash,
        folderId = i.folderId,
        markerColorId = i.markerColorId
    )

    override fun mapFrom(o: NoteUi) = NoteDomain(
        id = o.id,
        title = o.title,
        message = o.message,
        dateCreationRaw = o.dateCreationRaw,
        dateMovedToTrash = o.dateMovedToTrashRaw,
        dateLastUpdateRaw = o.dateLastUpdateRaw,
        isPinned = o.isPinned,
        isHidden = o.isHidden,
        pinTime = o.pinTime,
        isMovedToTrash = o.isMovedToTrash,
        folderId = o.folderId,
        markerColorId = o.markerColorId
    )
}
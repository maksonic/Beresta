package ru.maksonic.beresta.feature.notes.api.ui

import ru.maksonic.beresta.core.Mapper
import ru.maksonic.beresta.feature.notes.api.domain.NoteDomain

/**
 * @Author maksonic on 21.02.2023
 */
class NoteUiMapper : Mapper<NoteDomain, NoteUi> {
    override fun mapTo(i: NoteDomain) = NoteUi(
        id = i.id,
        folderId = i.folderId,
        title = i.title,
        message = i.message,
        dateCreationRaw = i.dateCreationRaw,
        dateLastUpdateRaw = i.dateLastUpdateRaw,
        pinTime = i.pinTime,
        dateMovedToTrashRaw = i.dateMovedToTrash,
        isHidden = i.isHidden,
        isMovedToTrash = i.isMovedToTrash,
        style = Style(
            isPinned = i.isPinned,
            markerColorId = i.markerColorId,
            wallpaperId = i.wallpaperId
        )
    )

    override fun mapFrom(o: NoteUi) = NoteDomain(
        id = o.id,
        folderId = o.folderId,
        title = o.title,
        message = o.message,
        dateCreationRaw = o.dateCreationRaw,
        dateLastUpdateRaw = o.dateLastUpdateRaw,
        pinTime = o.pinTime,
        dateMovedToTrash = o.dateMovedToTrashRaw,
        isPinned = o.style.isPinned,
        isHidden = o.isHidden,
        isMovedToTrash = o.isMovedToTrash,
        markerColorId = o.style.markerColorId,
        wallpaperId = o.style.wallpaperId
    )
}
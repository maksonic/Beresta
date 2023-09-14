package ru.maksonic.beresta.data.notes.cache

import ru.maksonic.beresta.data.common.DataMapper
import ru.maksonic.beresta.data.database.notes.NoteCache
import ru.maksonic.beresta.feature.notes.api.domain.NoteDomain

/**
 * @Author maksonic on 21.02.2023
 */
class NoteCacheMapper : DataMapper<NoteCache, NoteDomain> {
    override fun dataToDomain(i: NoteCache) = NoteDomain(
        id = i.id,
        title = i.title,
        message = i.message,
        dateCreationRaw = i.dateCreation,
        isPinned = i.isPinned,
        isHidden = i.isHidden,
        pinTime = i.pinTime,
        isMovedToTrash = i.isMovedToTrash,
        folderId = i.folderId,
        dateMovedToTrash = i.dateMovedToTrash,
        dateLastUpdateRaw = i.dateLastUpdateRaw,
        markerColorId = i.markerColorId
    )

    override fun domainToData(o: NoteDomain) = NoteCache(
        id = o.id,
        title = o.title,
        message = o.message,
        dateCreation = o.dateCreationRaw,
        isPinned = o.isPinned,
        isHidden = o.isHidden,
        pinTime = o.pinTime,
        isMovedToTrash = o.isMovedToTrash,
        folderId = o.folderId,
        dateMovedToTrash = o.dateMovedToTrash,
        dateLastUpdateRaw = o.dateLastUpdateRaw,
        markerColorId = o.markerColorId
    )
}
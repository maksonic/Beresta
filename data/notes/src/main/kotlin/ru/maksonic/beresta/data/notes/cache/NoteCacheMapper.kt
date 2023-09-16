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
        folderId = i.folderId,
        title = i.title,
        message = i.message,
        dateCreationRaw = i.dateCreation,
        dateLastUpdateRaw = i.dateLastUpdateRaw,
        pinTime = i.pinTime,
        dateMovedToTrash = i.dateMovedToTrash,
        isPinned = i.isPinned,
        isHidden = i.isHidden,
        isMovedToTrash = i.isMovedToTrash,
        markerColorId = i.markerColorId,
        wallpaperId = i.wallpaperId
    )

    override fun domainToData(o: NoteDomain) = NoteCache(
        id = o.id,
        folderId = o.folderId,
        title = o.title,
        message = o.message,
        dateCreation = o.dateCreationRaw,
        dateLastUpdateRaw = o.dateLastUpdateRaw,
        pinTime = o.pinTime,
        dateMovedToTrash = o.dateMovedToTrash,
        isPinned = o.isPinned,
        isHidden = o.isHidden,
        isMovedToTrash = o.isMovedToTrash,
        markerColorId = o.markerColorId,
        wallpaperId = o.wallpaperId
    )
}
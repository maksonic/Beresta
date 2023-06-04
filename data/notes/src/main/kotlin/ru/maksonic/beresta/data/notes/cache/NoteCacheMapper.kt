package ru.maksonic.beresta.data.notes.cache

import ru.maksonic.beresta.data.common.DataMapper
import ru.maksonic.beresta.data.database.notes.NoteCache
import ru.maksonic.beresta.feature.notes.list.api.domain.NoteDomain

/**
 * @Author maksonic on 21.02.2023
 */
class NoteCacheMapper : DataMapper<NoteCache, NoteDomain> {
    override fun dataToDomain(i: NoteCache) = NoteDomain(
        id = i.id,
        title = i.title,
        message = i.message,
        dateCreation = i.dateCreation,
        isPinned = i.isPinned,
        pinTime = i.pinTime,
        isMovedToTrash = i.isMovedToTrash,
        folderId = i.folderId,
        dateMovedToTrash = i.dateMovedToTrash
    )

    override fun domainToData(o: NoteDomain) = NoteCache(
        id = o.id,
        title = o.title,
        message = o.message,
        dateCreation = o.dateCreation,
        isPinned = o.isPinned,
        pinTime = o.pinTime,
        isMovedToTrash = o.isMovedToTrash,
        folderId = o.folderId,
        dateMovedToTrash = o.dateMovedToTrash
    )
}
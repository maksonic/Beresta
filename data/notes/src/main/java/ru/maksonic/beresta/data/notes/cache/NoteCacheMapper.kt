package ru.maksonic.beresta.data.notes.cache

import ru.maksonic.beresta.data.common.DataMapper
import ru.maksonic.beresta.data.database.NoteCache
import ru.maksonic.beresta.feature.notes_list.api.domain.NoteDomain

/**
 * @Author maksonic on 21.02.2023
 */
class NoteCacheMapper : DataMapper<NoteCache, NoteDomain> {
    override fun dataToDomain(i: NoteCache) = NoteDomain(
        id = i.id,
        title = i.title,
        message = i.message,
        dateCreation = i.dateCreation,
        currentFolder = i.currentFolder,
        isPinned = i.isPinned,
        isMovedToTrash = i.isMovedToTrash
    )

    override fun domainToData(o: NoteDomain) = NoteCache(
        id = o.id,
        title = o.title,
        message = o.message,
        dateCreation = o.dateCreation,
        currentFolder = o.currentFolder,
        isPinned = o.isPinned,
        isMovedToTrash = o.isMovedToTrash
    )
}
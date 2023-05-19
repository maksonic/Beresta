package ru.maksonic.beresta.data.notes_folders.cache

import ru.maksonic.beresta.data.common.DataMapper
import ru.maksonic.beresta.data.database.folders.NoteFolderCache
import ru.maksonic.beresta.feature.notes.folders.api.domain.NoteFolderDomain

/**
 * @Author maksonic on 30.03.2023
 */
class NoteFolderCacheMapper : DataMapper<NoteFolderCache, NoteFolderDomain> {
    override fun dataToDomain(i: NoteFolderCache) = NoteFolderDomain(
        id = i.id,
        title = i.title,
        isMovedToTrash = i.isMovedToTrash,
        isPinned = i.isPinned,
        isSticky = i.isSticky,
        pinTime = i.pinTime,
        dateCreation = i.dateCreation
    )

    override fun domainToData(o: NoteFolderDomain) = NoteFolderCache(
        id = o.id,
        title = o.title,
        isMovedToTrash = o.isMovedToTrash,
        isPinned = o.isPinned,
        isSticky = o.isSticky,
        pinTime = o.pinTime,
        dateCreation = o.dateCreation,
    )
}
package ru.maksonic.beresta.data.notes_folders.cache

import ru.maksonic.beresta.data.common.DataMapper
import ru.maksonic.beresta.data.database.NoteFolderCache
import ru.maksonic.beresta.feature.folders_list.api.domain.NoteFolderDomain

/**
 * @Author maksonic on 30.03.2023
 */
class NoteFolderCacheMapper : DataMapper<NoteFolderCache, NoteFolderDomain> {
    override fun dataToDomain(i: NoteFolderCache) =
        NoteFolderDomain(
            id = i.id,
            title = i.title,
            isMovedToTrash = i.isMovedToTrash,
            isPinned = i.isPinned,
            pinTime = i.pinTime,
            isSticky = i.isSticky
        )

    override fun domainToData(o: NoteFolderDomain) =
        NoteFolderCache(
            id = o.id,
            title = o.title,
            isMovedToTrash = o.isMovedToTrash,
            isPinned = o.isPinned,
            pinTime = o.pinTime,
            isSticky = o.isSticky
        )
}
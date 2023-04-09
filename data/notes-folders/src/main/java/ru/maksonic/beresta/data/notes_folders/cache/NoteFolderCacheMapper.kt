package ru.maksonic.beresta.data.notes_folders.cache

import ru.maksonic.beresta.data.common.DataMapper
import ru.maksonic.beresta.data.database.NoteFolderCache
import ru.maksonic.beresta.feature.folders_list.api.domain.NoteFolderDomain

/**
 * @Author maksonic on 30.03.2023
 */
class NoteFolderCacheMapper : DataMapper<NoteFolderCache, NoteFolderDomain> {
    override fun dataToDomain(i: NoteFolderCache) =
        NoteFolderDomain(i.id, i.title, i.isMovedToTrash)

    override fun domainToData(o: NoteFolderDomain) =
        NoteFolderCache(o.id, o.title, o.isMovedToTrash)
}
package ru.maksonic.beresta.feature.folders_list.data.local

import ru.maksonic.beresta.common.core.Mapper
import ru.maksonic.beresta.database.folders.FolderCache
import ru.maksonic.beresta.feature.folders_list.domain.FolderDomain

/**
 * @Author maksonic on 21.02.2023
 */
class FolderCacheMapper : Mapper<FolderCache, FolderDomain> {
    override fun mapTo(i: FolderCache) = FolderDomain(
        id = i.id,
        title = i.title,
        isMovedToTrash = i.isMovedToTrash,
        isPinned = i.isPinned,
        isSticky = i.isSticky,
        pinTime = i.pinTime,
        dateCreation = i.dateCreation,
        dateLastUpdateRaw = i.dateLastUpdateRaw,
        isSelectable = i.isSelectable,
        isStickyToStart = i.isStickyToStart,
        isStickyToEnd = i.isStickyToEnd,
        dateMovedToTrashRaw = i.dateMovedToTrash
    )

    override fun mapFrom(o: FolderDomain) = FolderCache(
        id = o.id,
        title = o.title,
        isMovedToTrash = o.isMovedToTrash,
        isPinned = o.isPinned,
        isSticky = o.isSticky,
        pinTime = o.pinTime,
        dateCreation = o.dateCreation,
        dateLastUpdateRaw = o.dateLastUpdateRaw,
        isSelectable = o.isSelectable,
        isStickyToStart = o.isStickyToStart,
        isStickyToEnd = o.isStickyToEnd,
        dateMovedToTrash = o.dateMovedToTrashRaw
    )
}
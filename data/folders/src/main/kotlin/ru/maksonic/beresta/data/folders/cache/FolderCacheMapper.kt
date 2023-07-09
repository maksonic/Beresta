package ru.maksonic.beresta.data.folders.cache

import ru.maksonic.beresta.data.common.DataMapper
import ru.maksonic.beresta.data.database.folders.FolderCache
import ru.maksonic.beresta.feature.folders_chips.api.domain.FolderDomain

/**
 * @Author maksonic on 30.03.2023
 */
class FolderCacheMapper : DataMapper<FolderCache, FolderDomain> {
    override fun dataToDomain(i: FolderCache) = FolderDomain(
        id = i.id,
        title = i.title,
        isSelected = i.isSelected,
        isMovedToTrash = i.isMovedToTrash,
        isPinned = i.isPinned,
        isSticky = i.isSticky,
        pinTime = i.pinTime,
        dateCreation = i.dateCreation,
        isSelectable = i.isSelectable,
        isStickyToStart = i.isStickyToStart,
        isStickyToEnd = i.isStickyToEnd,
        dateMovedToTrash = i.dateMovedToTrash
    )

    override fun domainToData(o: FolderDomain) = FolderCache(
        id = o.id,
        title = o.title,
        isSelected = o.isSelected,
        isMovedToTrash = o.isMovedToTrash,
        isPinned = o.isPinned,
        isSticky = o.isSticky,
        pinTime = o.pinTime,
        dateCreation = o.dateCreation,
        isSelectable = o.isSelectable,
        isStickyToStart = o.isStickyToStart,
        isStickyToEnd = o.isStickyToEnd,
        dateMovedToTrash = o.dateMovedToTrash
    )
}
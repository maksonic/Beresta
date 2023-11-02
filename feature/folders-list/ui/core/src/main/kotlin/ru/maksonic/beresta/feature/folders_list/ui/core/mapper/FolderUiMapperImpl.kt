package ru.maksonic.beresta.feature.folders_list.ui.core.mapper

import ru.maksonic.beresta.feature.folders_list.domain.FolderDomain
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUiMapper

/**
 * @Author maksonic on 17.10.2023
 */
class FolderUiMapperImpl : FolderUiMapper {
    override fun mapTo(i: FolderDomain) = FolderUi(
        id = i.id,
        title = i.title,
        isMovedToTrash = i.isMovedToTrash,
        isPinned = i.isPinned,
        pinTime = i.pinTime,
        dateCreationRaw = i.dateCreation,
        dateLastUpdateRaw = i.dateLastUpdateRaw,
        isSelectable = i.isSelectable,
        isStickyToStart = i.isStickyToStart,
        isStickyToEnd = i.isStickyToEnd,
        dateMovedToTrashRaw = i.dateMovedToTrashRaw,
        dateMovedToTrash = i.dateMovedToTrash
    )

    override fun mapFrom(o: FolderUi) = FolderDomain(
        id = o.id,
        title = o.title,
        isMovedToTrash = o.isMovedToTrash,
        isPinned = o.isPinned,
        pinTime = o.pinTime,
        dateCreation = o.dateCreationRaw,
        dateLastUpdateRaw = o.dateLastUpdateRaw,
        isSelectable = o.isSelectable,
        isStickyToStart = o.isStickyToStart,
        isStickyToEnd = o.isStickyToEnd,
        dateMovedToTrashRaw = o.dateMovedToTrashRaw
    )
}
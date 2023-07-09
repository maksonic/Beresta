package ru.maksonic.beresta.feature.folders_chips.api.ui

import ru.maksonic.beresta.core.Mapper
import ru.maksonic.beresta.feature.folders_chips.api.domain.FolderDomain

/**
 * @Author maksonic on 30.03.2023
 */
class FolderToUiMapper : Mapper<FolderDomain, FolderUi> {
    override fun mapTo(i: FolderDomain) = FolderUi(
        id = i.id,
        title = i.title,
        isSelected = i.isSelected,
        isMovedToTrash = i.isMovedToTrash,
        isPinned = i.isPinned,
        pinTime = i.pinTime,
        dateCreationRaw = i.dateCreation,
        isSelectable = i.isSelectable,
        isStickyToStart = i.isStickyToStart,
        isStickyToEnd = i.isStickyToEnd,
        dateMovedToTrashRaw = i.dateMovedToTrash
    )

    override fun mapFrom(o: FolderUi) = FolderDomain(
        id = o.id,
        title = o.title,
        isSelected = o.isSelected,
        isMovedToTrash = o.isMovedToTrash,
        isPinned = o.isPinned,
        pinTime = o.pinTime,
        dateCreation = o.dateCreationRaw,
        isSelectable = o.isSelectable,
        isStickyToStart = o.isStickyToStart,
        isStickyToEnd = o.isStickyToEnd,
        dateMovedToTrash = o.dateMovedToTrashRaw
    )
}
package ru.maksonic.beresta.feature.notes.folders.api.ui

import ru.maksonic.beresta.core.Mapper
import ru.maksonic.beresta.feature.notes.folders.api.domain.NoteFolderDomain

/**
 * @Author maksonic on 30.03.2023
 */
class NoteFolderToUiMapper : Mapper<NoteFolderDomain, NoteFolderUi> {
    override fun mapTo(i: NoteFolderDomain) = NoteFolderUi(
        id = i.id,
        title = i.title,
        isMovedToTrash = i.isMovedToTrash,
        isPinned = i.isPinned,
        pinTime = i.pinTime,
        dateCreationRaw = i.dateCreation,
        isSelectable = i.isSelectable,
        isStickyToStart = i.isStickyToStart,
        isStickyToEnd = i.isStickyToEnd,
        dateMovedToTrashRaw = i.dateMovedToTrash
    )

    override fun mapFrom(o: NoteFolderUi) = NoteFolderDomain(
        id = o.id,
        title = o.title,
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
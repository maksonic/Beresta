package ru.maksonic.beresta.feature.notes.folders.api.ui

import ru.maksonic.beresta.core.Mapper
import ru.maksonic.beresta.feature.notes.folders.api.domain.NoteFolderDomain
import java.time.Instant

/**
 * @Author maksonic on 30.03.2023
 */
class NoteFolderToUiMapper : Mapper<NoteFolderDomain, NoteFolderUi> {
    override fun mapTo(i: NoteFolderDomain) = NoteFolderUi(
        id = i.id,
        title = i.title,
        isMovedToTrash = i.isMovedToTrash,
        isPinned = i.isPinned,
        isSticky = i.isSticky,
        pinTime = i.pinTime,
        dateCreationRaw = i.dateCreation,
    )

    override fun mapFrom(o: NoteFolderUi) = NoteFolderDomain(
        id = o.id,
        title = o.title,
        isMovedToTrash = o.isMovedToTrash,
        isPinned = o.isPinned,
        isSticky = o.isSticky,
        pinTime = o.pinTime,
        dateCreation = o.dateCreationRaw,
    )
}
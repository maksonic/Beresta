package ru.maksonic.beresta.feature.folders_list.api.ui

import ru.maksonic.beresta.core.Mapper
import ru.maksonic.beresta.feature.folders_list.api.domain.NoteFolderDomain
import java.time.Instant

/**
 * @Author maksonic on 30.03.2023
 */
class NoteFolderToChipMapper : Mapper<NoteFolderDomain, FilterChipUi> {
    override fun mapTo(i: NoteFolderDomain) =
        FilterChipUi(
            id = i.id,
            title = i.title,
            isMovedToTrash = i.isMovedToTrash,
            isPinned = i.isPinned,
            pinTime = i.pinTime,
            isSticky = i.isSticky
        )

    override fun mapFrom(o: FilterChipUi) =
        NoteFolderDomain(
            id = o.id,
            title = o.title,
            isMovedToTrash = o.isMovedToTrash,
            isPinned = o.isPinned,
            pinTime = Instant.now(),
            isSticky = o.isSticky
        )
}
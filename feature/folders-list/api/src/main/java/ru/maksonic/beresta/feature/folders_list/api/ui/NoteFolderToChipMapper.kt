package ru.maksonic.beresta.feature.folders_list.api.ui

import ru.maksonic.beresta.core.Mapper
import ru.maksonic.beresta.feature.folders_list.api.domain.NoteFolderDomain

/**
 * @Author maksonic on 30.03.2023
 */
class NoteFolderToChipMapper : Mapper<NoteFolderDomain, FilterChipUi> {
    override fun mapTo(i: NoteFolderDomain) = FilterChipUi(i.id, i.title)
    override fun mapFrom(o: FilterChipUi) = NoteFolderDomain(o.id, o.title)
}
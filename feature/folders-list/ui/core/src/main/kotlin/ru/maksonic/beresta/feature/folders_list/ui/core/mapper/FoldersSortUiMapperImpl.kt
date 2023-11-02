package ru.maksonic.beresta.feature.folders_list.ui.core.mapper

import ru.maksonic.beresta.feature.sorting_sheet.domain.sort.FoldersSortDomain
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.FoldersSortUi
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.FoldersSortUiMapper

/**
 * @Author maksonic on 17.10.2023
 */
class FoldersSortUiMapperImpl : FoldersSortUiMapper {
    override fun mapTo(i: FoldersSortDomain) = FoldersSortUi(
        order = i.order,
        sort = i.sort,
        isSortPinned = i.isSortPinned,
    )

    override fun mapFrom(o: FoldersSortUi) = FoldersSortDomain(
        order = o.order,
        sort = o.sort,
        isSortPinned = o.isSortPinned,
    )
}
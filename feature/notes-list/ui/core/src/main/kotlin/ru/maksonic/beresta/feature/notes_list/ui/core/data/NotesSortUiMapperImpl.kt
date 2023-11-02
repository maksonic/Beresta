package ru.maksonic.beresta.feature.notes_list.ui.core.data

import ru.maksonic.beresta.feature.sorting_sheet.domain.sort.NotesSortDomain
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.NotesSortUi
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.NotesSortUiMapper

/**
 * @Author maksonic on 17.10.2023
 */
class NotesSortUiMapperImpl : NotesSortUiMapper {
    override fun mapTo(i: NotesSortDomain) = NotesSortUi(
        order = i.order,
        sort = i.sort,
        isSortPinned = i.isSortPinned,
        gridCount = i.gridCount
    )

    override fun mapFrom(o: NotesSortUi) = NotesSortDomain(
        order = o.order,
        sort = o.sort,
        isSortPinned = o.isSortPinned,
        gridCount = o.gridCount
    )
}
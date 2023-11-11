package ru.maksonic.beresta.feature.tags_list.ui.core

import ru.maksonic.beresta.feature.tags_list.domain.NoteDefaultTagDomain
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi
import ru.maksonic.beresta.feature.tags_list.ui.api.TagUiMapper

/**
 * @Author maksonic on 05.11.2023
 */
class TagUiMapperImpl : TagUiMapper {
    override fun mapTo(i: NoteDefaultTagDomain) = NoteTagUi(i.id, i.title, isSelected = false)

    override fun mapFrom(o: NoteTagUi) = NoteDefaultTagDomain(o.id, o.title, isDefault = false)
}
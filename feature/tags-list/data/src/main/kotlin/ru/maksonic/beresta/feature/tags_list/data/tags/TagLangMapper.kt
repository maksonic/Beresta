package ru.maksonic.beresta.feature.tags_list.data.tags

import ru.maksonic.beresta.feature.tags_list.domain.NoteDefaultTagDomain

/**
 * @Author maksonic on 05.11.2023
 */
internal fun List<TagItemModel>.toDomain() =
    this.map { NoteDefaultTagDomain(id = it.id, title = it.title, isDefault = true) }
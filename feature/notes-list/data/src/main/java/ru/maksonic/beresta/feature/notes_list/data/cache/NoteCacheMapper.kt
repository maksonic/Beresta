package ru.maksonic.beresta.feature.notes_list.data.cache

import ru.maksonic.beresta.data.common.DataMapper
import ru.maksonic.beresta.feature.notes_list.domain.NoteDomain

/**
 * @Author maksonic on 19.12.2022
 */
class NoteCacheMapper : DataMapper<NoteCache, NoteDomain> {
    override fun dataToDomain(i: NoteCache) = NoteDomain(
        id = i.id,
        title = i.title,
        message = i.message,
        dateCreation = i.dateCreation
    )

    override fun domainToData(o: NoteDomain) = NoteCache(
        id = o.id,
        title = o.title,
        message = o.message,
        dateCreation = o.dateCreation
    )

}
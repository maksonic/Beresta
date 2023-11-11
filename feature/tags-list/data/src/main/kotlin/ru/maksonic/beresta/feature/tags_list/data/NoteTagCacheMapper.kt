package ru.maksonic.beresta.feature.tags_list.data

import ru.maksonic.beresta.common.core.Mapper
import ru.maksonic.beresta.database.tags.NoteTagCache
import ru.maksonic.beresta.feature.tags_list.domain.NoteDefaultTagDomain

/**
 * @Author maksonic on 05.11.2023
 */
class NoteTagCacheMapper : Mapper<NoteTagCache, NoteDefaultTagDomain> {
    override fun mapTo(i: NoteTagCache) = NoteDefaultTagDomain(i.id, i.title, false)

    override fun mapFrom(o: NoteDefaultTagDomain) = NoteTagCache(o.id, o.title)
}
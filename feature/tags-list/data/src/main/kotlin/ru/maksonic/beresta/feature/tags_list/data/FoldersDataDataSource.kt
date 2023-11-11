package ru.maksonic.beresta.feature.tags_list.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.beresta.common.data.BaseDataSource
import ru.maksonic.beresta.database.tags.NoteTagCache
import ru.maksonic.beresta.database.tags.NoteTagCacheItem
import ru.maksonic.beresta.database.tags.NoteTagDao
import ru.maksonic.beresta.database.tags.NoteTagsCacheList

/**
 * @Author maksonic on 05.11.2023
 */
class NoteTagsDataDataSource(
    private val noteTagDao: NoteTagDao,
    private val ioDispatcher: CoroutineDispatcher,
) : BaseDataSource<NoteTagCache>(noteTagDao, ioDispatcher) {

    fun fetchCacheNoteTagsList(): NoteTagsCacheList = noteTagDao.fetchList().flowOn(ioDispatcher)

    fun fetchCacheOneItemById(id: Long): NoteTagCacheItem = noteTagDao.fetchItemById(id)
        .flowOn(ioDispatcher)
}
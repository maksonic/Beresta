package ru.maksonic.beresta.feature.notes_list.data.cache

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.maksonic.beresta.data.common.BaseCacheSource

/**
 * @Author maksonic on 19.12.2022
 */
class NotesCacheSource(
    private val noteDao: NoteDao,
    private val dispatcher: CoroutineDispatcher,
) : BaseCacheSource<NoteCache>(noteDao, dispatcher) {
    fun fetchCachedNotes(): NotesCacheList = noteDao.fetchCacheItemsList().flowOn(dispatcher)

    fun fetchItemById(itemId: Long): NoteCacheItem =
        noteDao.fetchCacheOneItemById(itemId).flowOn(dispatcher)

    suspend fun isNoteIsExist(itemId: Long): Boolean =
        withContext(dispatcher) { noteDao.isNoteIsExist(itemId) }
}
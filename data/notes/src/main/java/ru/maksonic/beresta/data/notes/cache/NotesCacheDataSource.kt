package ru.maksonic.beresta.data.notes.cache

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import ru.maksonic.beresta.data.common.BaseCacheSource
import ru.maksonic.beresta.data.database.NoteCache
import ru.maksonic.beresta.data.database.NoteCacheItem
import ru.maksonic.beresta.data.database.NoteDao
import ru.maksonic.beresta.data.database.NotesCacheList

/**
 * @Author maksonic on 21.02.2023
 */
class NotesCacheDataSource(
    private val noteDao: NoteDao,
    private val dispatcher: CoroutineDispatcher,
) : BaseCacheSource<NoteCache>(noteDao, dispatcher) {
    fun fetchCachedNotes(): NotesCacheList = noteDao.fetchCacheItemsList().flowOn(dispatcher)

    fun fetchCachedTrashNotes(): NotesCacheList = noteDao.fetchCacheItemsList()
        .transform { notes ->
            val trashNotes = notes.filter { note -> note.isMovedToTrash }
            emit(trashNotes)
        }.flowOn(dispatcher)

    fun fetchItemById(itemId: Long): NoteCacheItem =
        noteDao.fetchCacheOneItemById(itemId).flowOn(dispatcher)

    suspend fun isNoteIsExist(itemId: Long): Boolean =
        withContext(dispatcher) { noteDao.isNoteIsExist(itemId) }
}
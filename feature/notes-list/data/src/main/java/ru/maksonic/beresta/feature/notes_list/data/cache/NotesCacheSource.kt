package ru.maksonic.beresta.feature.notes_list.data.cache

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import ru.maksonic.beresta.data.common.BaseCacheSource

/**
 * @Author maksonic on 19.12.2022
 */
class NotesCacheSource(
    private val noteDao: NoteDao,
    private val dispatcher: CoroutineDispatcher,
) : BaseCacheSource<NoteCache>(noteDao, dispatcher) {
    fun fetchCachedNotes(): NotesCacheList =
        noteDao.fetchCacheItemsList()
            .transform { notes ->
                val isNotTrash = notes.filter { note -> !note.isMovedToTrash }
                emit(isNotTrash)
            }.flowOn(dispatcher)

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
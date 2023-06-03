package ru.maksonic.beresta.data.notes.cache

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.maksonic.beresta.data.common.BaseCacheSource
import ru.maksonic.beresta.data.database.notes.NoteCache
import ru.maksonic.beresta.data.database.notes.NoteCacheItem
import ru.maksonic.beresta.data.database.notes.NoteDao
import ru.maksonic.beresta.data.database.notes.NotesCacheList

/**
 * @Author maksonic on 21.02.2023
 */
class NotesCacheDataSource(
    private val noteDao: NoteDao,
    private val dispatcher: CoroutineDispatcher,
) : BaseCacheSource<NoteCache>(noteDao, dispatcher) {

    fun fetchCacheNotesList(): NotesCacheList = noteDao.fetchNotesList().flowOn(dispatcher)

    fun fetchCacheNotesWithoutFolderTrashList(): NotesCacheList =
        noteDao.fetchNotesWithoutFolderTrashList().flowOn(dispatcher)

    fun fetchCacheNotesByFolderTrashList(): NotesCacheList =
        noteDao.fetchNotesByFolderTrashList().flowOn(dispatcher)

    fun fetchCacheOneItemById(itemId: Long): NoteCacheItem =
        noteDao.fetchCacheOneItemById(itemId).flowOn(dispatcher)

    suspend fun isNoteIsExist(itemId: Long): Boolean =
        withContext(dispatcher) { noteDao.isNoteIsExist(itemId) }
}
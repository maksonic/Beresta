package ru.maksonic.beresta.data.notes.cache

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.beresta.data.common.BaseDataSource
import ru.maksonic.beresta.data.database.notes.NoteCache
import ru.maksonic.beresta.data.database.notes.NoteCacheItem
import ru.maksonic.beresta.data.database.notes.NoteDao
import ru.maksonic.beresta.data.database.notes.NotesCacheList

/**
 * @Author maksonic on 21.02.2023
 */
class NotesDataDataSource(
    private val noteDao: NoteDao,
    private val dispatcher: CoroutineDispatcher,
) : BaseDataSource<NoteCache>(noteDao, dispatcher) {

    fun fetchCacheNotesList(): NotesCacheList = noteDao.fetchList().flowOn(dispatcher)

    fun fetchCacheNotesWithoutFolderTrashList(): NotesCacheList =
        noteDao.fetchWithoutFolderTrashList().flowOn(dispatcher)

    fun fetchCacheNotesByFolderTrashList(): NotesCacheList =
        noteDao.fetchTrashListByFolder().flowOn(dispatcher)

    fun fetchCacheOneItemById(itemId: Long): NoteCacheItem =
        noteDao.fetchItemById(itemId).flowOn(dispatcher)

    fun fetchCacheHiddenNotesList(): NotesCacheList = noteDao.fetchHiddenList().flowOn(dispatcher)
}
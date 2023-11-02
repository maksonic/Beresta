package ru.maksonic.beresta.feature.notes_list.data.list.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.beresta.common.data.BaseDataSource
import ru.maksonic.beresta.database.notes.NoteCache
import ru.maksonic.beresta.database.notes.NoteCacheItem
import ru.maksonic.beresta.database.notes.NoteDao
import ru.maksonic.beresta.database.notes.NotesCacheList

/**
 * @Author maksonic on 21.02.2023
 */
class NotesDataDataSource(
    private val noteDao: NoteDao,
    private val ioDispatcher: CoroutineDispatcher,
) : BaseDataSource<NoteCache>(noteDao, ioDispatcher) {

    fun fetchCacheNotesList(): NotesCacheList = noteDao.fetchList().flowOn(ioDispatcher)

    fun fetchCacheNotesWithoutFolderTrashList(): NotesCacheList =
        noteDao.fetchWithoutFolderTrashList().flowOn(ioDispatcher)

    fun fetchCacheNotesByFolderTrashList(): NotesCacheList =
        noteDao.fetchTrashListByFolder().flowOn(ioDispatcher)

    fun fetchCacheOneItemById(itemId: Long): NoteCacheItem =
        noteDao.fetchItemById(itemId).flowOn(ioDispatcher)

    fun fetchCacheHiddenNotesList(): NotesCacheList = noteDao.fetchHiddenList().flowOn(ioDispatcher)

    suspend fun deleteCacheHiddenNotesList() = noteDao.deleteHiddenList()
}
package ru.maksonic.beresta.data.notes

import kotlinx.coroutines.flow.transform
import ru.maksonic.beresta.data.common.BaseRepository
import ru.maksonic.beresta.data.database.notes.NoteCache
import ru.maksonic.beresta.data.notes.cache.NoteCacheMapper
import ru.maksonic.beresta.data.notes.cache.NotesDataDataSource
import ru.maksonic.beresta.feature.notes.api.domain.NoteDomain
import ru.maksonic.beresta.feature.notes.api.domain.NoteDomainItem
import ru.maksonic.beresta.feature.notes.api.domain.NotesDomainList
import ru.maksonic.beresta.feature.notes.api.domain.NotesRepository

/**
 * @Author maksonic on 21.02.2023
 */

class NotesRepositoryImpl(
    private val cache: NotesDataDataSource,
    private val mapper: NoteCacheMapper,
) : BaseRepository<NoteCache, NoteDomain>(cache, mapper), NotesRepository {

    override fun fetchById(itemId: Long): NoteDomainItem =
        cache.fetchCacheOneItemById(itemId).transform { emit(mapper.dataToDomain(it)) }

    override fun fetchList(): NotesDomainList =
        cache.fetchCacheNotesList().transform { emit(mapper.listDataToDomain(it)) }

    override suspend fun fetchNotesWithoutFolderTrashList(): NotesDomainList = cache
        .fetchCacheNotesWithoutFolderTrashList()
        .transform { emit(mapper.listDataToDomain(it)) }

    override suspend fun fetchNotesByFolderTrashList(): NotesDomainList =
        cache.fetchCacheNotesByFolderTrashList().transform { emit(mapper.listDataToDomain(it)) }

    override suspend fun fetchHiddenNotes(): NotesDomainList = cache.fetchCacheHiddenNotesList()
        .transform { cacheNoteItem -> emit(mapper.listDataToDomain(cacheNoteItem)) }

    override suspend fun deleteHiddenNotes(): Unit = cache.deleteCacheHiddenNotesList()
}
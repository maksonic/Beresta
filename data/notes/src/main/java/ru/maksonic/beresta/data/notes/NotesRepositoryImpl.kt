package ru.maksonic.beresta.data.notes

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.transform
import ru.maksonic.beresta.data.notes.cache.NoteCacheMapper
import ru.maksonic.beresta.data.notes.cache.NotesCacheDataSource
import ru.maksonic.beresta.feature.notes_list.api.domain.NoteDomain
import ru.maksonic.beresta.feature.notes_list.api.domain.NoteDomainItem
import ru.maksonic.beresta.feature.notes_list.api.domain.NotesDomainList
import ru.maksonic.beresta.feature.notes_list.api.domain.NotesRepository

/**
 * @Author maksonic on 21.02.2023
 */
class NotesRepositoryImpl(
    private val cache: NotesCacheDataSource,
    private val mapper: NoteCacheMapper,
) : NotesRepository {

    override fun fetchItemsList(): NotesDomainList = cache.fetchCacheNotesList()
        .transform { cacheNotesList ->
            val notes = mapper.listDataToDomain(cacheNotesList)
            delay(500)
            emit(notes)
        }

    override fun fetchItem(itemId: Long): NoteDomainItem = cache.fetchCacheOneItemById(itemId)
        .transform { cacheNoteItem -> emit(mapper.dataToDomain(cacheNoteItem)) }

    override suspend fun fetchTrashNotes(): NotesDomainList = cache.fetchCacheNotesList()
        .transform { cacheNotesList ->
            val notes = mapper.listDataToDomain(cacheNotesList)
            emit(notes)
        }

    override suspend fun addNewItem(item: NoteDomain) {
        if (item.title.isNotBlank() || item.message.isNotBlank()) {
            val cacheNote = mapper.domainToData(item)
            cache.insertItem(cacheNote)
        }
    }

    override suspend fun updateItem(item: NoteDomain) {
        val cacheNoteItem = mapper.domainToData(item)
        cache.updateItem(cacheNoteItem)
    }

    override suspend fun updateAllItems(items: List<NoteDomain>) {
        val list = mapper.listDomainToData(items)
        cache.updateAll(list)
    }

    override suspend fun removeEmptyItem(item: NoteDomain) {
        with(item) {
            if (title.isBlank() && message.isBlank()) {
                removeItem(item)
            }
        }
    }

    override suspend fun removeItem(item: NoteDomain) {
        val cacheNote = mapper.domainToData(item)
        cache.removeItem(cacheNote)
    }

    override suspend fun clearItemsList(items: List<NoteDomain>) {
        val cacheList = mapper.listDomainToData(items)
        cache.clearCache(cacheList)
    }
}
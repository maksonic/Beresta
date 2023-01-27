package ru.maksonic.beresta.feature.notes_list.data

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import ru.maksonic.beresta.feature.notes_list.data.cache.NoteCacheMapper
import ru.maksonic.beresta.feature.notes_list.data.cache.NotesCacheSource
import ru.maksonic.beresta.feature.notes_list.domain.NoteDomain
import ru.maksonic.beresta.feature.notes_list.domain.NoteDomainItem
import ru.maksonic.beresta.feature.notes_list.domain.NotesDomainList
import ru.maksonic.beresta.feature.notes_list.domain.NotesRepository

/**
 * @Author maksonic on 20.12.2022
 */
class NotesRepositoryImpl(
    private val cache: NotesCacheSource,
    private val mapper: NoteCacheMapper,
) : NotesRepository {

    override fun fetchItemsList(): NotesDomainList = cache.fetchCachedNotes()
        .transform { cacheNotesList ->
            val notes = mapper.listDataToDomain(cacheNotesList)
            emit(notes)
        }

    override fun fetchItem(itemId: Long): NoteDomainItem = cache.fetchItemById(itemId)
        .transform { cacheNoteItem -> emit(mapper.dataToDomain(cacheNoteItem)) }

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

    override fun fetchNotesMovedToTrash(): NotesDomainList = flow { emit(emptyList()) }
}
package ru.maksonic.beresta.feature.notes_list.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import ru.maksonic.beresta.feature.notes_list.data.cache.NoteCacheMapper
import ru.maksonic.beresta.feature.notes_list.data.cache.NotesCacheSource
import ru.maksonic.beresta.feature.notes_list.domain.NoteDomain
import ru.maksonic.beresta.feature.notes_list.domain.NoteDomainItem
import ru.maksonic.beresta.feature.notes_list.domain.NotesDomainList
import ru.maksonic.beresta.feature.notes_list.domain.NotesRepository
import java.util.*
import kotlin.random.Random

/**
 * @Author maksonic on 20.12.2022
 */
class NotesRepositoryImpl(
    private val cache: NotesCacheSource,
    private val mapper: NoteCacheMapper,
) : NotesRepository {

    /*  override fun fetchItemsList(): NotesDomainList = cache.fetchCachedNotes()
          .transform { cacheNotesList ->
              val notes = mapper.listDataToDomain(cacheNotesList)
              emit(notes)
          }*/

    override fun fetchNotesMovedToTrash() = fetchItemsList().transform { notes ->
        val filtered = notes.filter { it.isMovedToTrash }
        emit(filtered)
    }

    override fun fetchItemsList(): NotesDomainList = flow {
        val date = Calendar.getInstance()
        val fakeData = buildList {
            repeat(15) {
                add(
                    NoteDomain(
                        id = it.toLong(),
                        title = "Title note $it",
                        message = "Message note $it of random generation",
                        dateCreation = date,
                        isMovedToTrash = Random.nextBoolean()
                    )
                )
            }
        }
        delay(1000)
        emit(fakeData)
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
}
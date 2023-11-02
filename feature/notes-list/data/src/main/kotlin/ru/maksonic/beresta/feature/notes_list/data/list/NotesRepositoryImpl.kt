package ru.maksonic.beresta.feature.notes_list.data.list

import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.transform
import ru.maksonic.beresta.common.data.BaseRepository
import ru.maksonic.beresta.database.notes.NoteCache
import ru.maksonic.beresta.feature.app_lang.domain.AppLangRepository
import ru.maksonic.beresta.feature.app_lang.domain.formatter.DateFormatter
import ru.maksonic.beresta.feature.notes_list.data.list.local.NoteCacheMapper
import ru.maksonic.beresta.feature.notes_list.data.list.local.NotesDataDataSource
import ru.maksonic.beresta.feature.notes_list.domain.NoteDomain
import ru.maksonic.beresta.feature.notes_list.domain.NoteDomainItem
import ru.maksonic.beresta.feature.notes_list.domain.NotesDomainList
import ru.maksonic.beresta.feature.notes_list.domain.list.NotesRepository

/**
 * @Author maksonic on 13.10.2023
 */
class NotesRepositoryImpl(
    private val notesCacheDataSource: NotesDataDataSource,
    private val mapper: NoteCacheMapper,
    private val appLangRepository: AppLangRepository,
    private val dateFormatter: DateFormatter,
) : BaseRepository<NoteCache, NoteDomain>(notesCacheDataSource, mapper), NotesRepository {

    override fun fetchById(itemId: Long): NoteDomainItem = notesCacheDataSource
        .fetchCacheOneItemById(itemId).transform { emit(mapper.mapTo(it)) }

    override fun fetchList(): NotesDomainList = combine(
        notesCacheDataSource.fetchCacheNotesList(),
        appLangRepository.fetchAppCurrentLang()
    ) { notes, lang ->
        mapper.mapListTo(notes).map {
            it.copy(dateCreation = dateFormatter.fetchDateByLang(it.dateCreationRaw, lang))
        }
    }

    override  fun fetchNotesWithoutFolderTrashList(): NotesDomainList = combine(
        notesCacheDataSource.fetchCacheNotesWithoutFolderTrashList(),
        appLangRepository.fetchAppCurrentLang()
    ) { notes, lang ->
        mapper.mapListTo(notes).map { note ->
            val dateMovedToTrash = note.dateMovedToTrashRaw?.let { date ->
                dateFormatter.fetchDateByLang(date, lang)
            }

            note.copy(dateMovedToTrash = dateMovedToTrash)
        }
    }

    override  fun fetchNotesByFolderTrashList(): NotesDomainList = notesCacheDataSource
        .fetchCacheNotesByFolderTrashList().transform { emit(mapper.mapListTo(it)) }

    override  fun fetchHiddenNotes(): NotesDomainList = combine(
        notesCacheDataSource.fetchCacheHiddenNotesList(),
        appLangRepository.fetchAppCurrentLang()
    ) { notes, lang ->
        mapper.mapListTo(notes).map {
            it.copy(dateCreation = dateFormatter.fetchDateByLang(it.dateCreationRaw, lang))
        }
    }

    override suspend fun deleteHiddenNotes(): Unit =
        notesCacheDataSource.deleteCacheHiddenNotesList()
}
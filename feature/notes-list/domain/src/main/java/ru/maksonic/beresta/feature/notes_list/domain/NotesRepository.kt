package ru.maksonic.beresta.feature.notes_list.domain

import ru.maksonic.beresta.base_domain.BaseRepository

/**
 * @Author maksonic on 20.12.2022
 */
interface NotesRepository : BaseRepository<NoteDomain> {
    fun fetchNotesMovedToTrash(): NotesDomainList
}
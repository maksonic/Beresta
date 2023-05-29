package ru.maksonic.beresta.feature.notes.list.core

import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi

/**
 * @Author maksonic on 26.05.2023
 */
class NotesFilterUpdater(list: List<NoteUi>, private val currentFolderId: Long) {
    companion object {
        private const val STICKY_START_FOLDER_ID = 1L
        private const val STICKY_END_FOLDER_ID = 2L
        private const val DEFAULT_NOTE_FOLDER_ID = 2L
    }

    private val update: List<NoteUi> = list.filter { note ->
        when (currentFolderId) {
            // When ID == 1L - Showed all notes.
            STICKY_START_FOLDER_ID -> note.id == note.id
            // When ID == 2L - Showed notes without folder (category).
            STICKY_END_FOLDER_ID -> note.folderId == DEFAULT_NOTE_FOLDER_ID
            else -> note.folderId == currentFolderId
        }
    }

    val filteredNotes = update
    val isEmptyFilterList = update.isEmpty()
}
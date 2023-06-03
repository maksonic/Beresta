package ru.maksonic.beresta.screen.trash_list.notes.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes.list.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.list.api.domain.usecase.FetchNotesWithoutFolderTrashListUseCase
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUiMapper

/**
 * @Author maksonic on 30.05.2023
 */
class NotesTrashProgram(
    private val fetchRemovedNotes: FetchNotesWithoutFolderTrashListUseCase,
    private val notesInteractor: NotesInteractor,
    private val notesMapper: NoteUiMapper,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchRemovedData -> fetchData(consumer)
            is Cmd.DeleteOrRestoreNotes -> deleteOrRestoreNotes(cmd.isRestore, cmd.notes)
        }
    }

    private suspend fun fetchData(consumer: (Msg) -> Unit) {
        runCatching {
            fetchRemovedNotes().collect { notesDomain ->
                val notes = notesMapper.mapListTo(notesDomain)
                consumer(Msg.Inner.FetchedRemovedNotesResult(notes))
            }
        }
    }

    private suspend fun deleteOrRestoreNotes(isRestore: Boolean, notes: List<NoteUi>) {
        runCatching {
            val notesDomain = notesMapper.mapListFrom(notes)
            notesInteractor.also {
                if (isRestore) {
                    val restored = notesDomain.map { note -> note.copy(isMovedToTrash = false) }
                    it.updateAll(restored)
                } else
                    it.deleteAll(notesDomain)
            }
        }
    }
}

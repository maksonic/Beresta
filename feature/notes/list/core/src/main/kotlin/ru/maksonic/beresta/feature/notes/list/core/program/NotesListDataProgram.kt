package ru.maksonic.beresta.feature.notes.list.core.program

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes.list.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.list.api.domain.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUiMapper
import ru.maksonic.beresta.feature.notes.list.core.Cmd
import ru.maksonic.beresta.feature.notes.list.core.Msg
import java.time.LocalDateTime

/**
 * @Author maksonic on 06.06.2023
 */
class NotesListDataProgram(
    private val notesInteractor: NotesInteractor,
    private val fetchNotesUseCase: FetchNotesUseCase,
    private val mapper: NoteUiMapper,
    private val ioDispatcher: CoroutineDispatcher,
) : ElmProgram<Msg, Cmd> {

    private companion object {
        private const val SNACK_BAR_VISIBILITY_TIME = 5000L
    }

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchNotesList -> fetchNotesList(consumer)
            is Cmd.RemoveSelected -> moveSelectedNotesToTrash(cmd.notes, consumer)
            is Cmd.UpdatePinnedNotesInCache -> updatePinnedNotes(cmd.pinned)
            is Cmd.UndoRemoveNotes -> undoRemovedFromTrash(cmd.notes, consumer)
            else -> {}
        }
    }

    private suspend fun fetchNotesList(consumer: (Msg) -> Unit) = withContext(ioDispatcher) {
        runCatching {
            fetchNotesUseCase().collect { notesDomain ->
                val notes = mapper.mapListTo(notesDomain)
                consumer(Msg.Inner.FetchedResultData(NoteUi.Collection(notes)))
            }
        }.onFailure { throwable ->
            consumer(Msg.Inner.FetchedResultError(throwable.localizedMessage.toString()))
        }
    }

    private suspend fun moveSelectedNotesToTrash(
        notes: List<NoteUi>,
        consumer: (Msg) -> Unit
    ) {
        val notesUi = notes.map { note ->
            note.copy(
                isMovedToTrash = true,
                folderId = 2L,
                dateMovedToTrashRaw = LocalDateTime.now()
            )
        }
        val notesDomain = mapper.mapListFrom(notesUi)
        notesInteractor.updateAll(notesDomain)
        delay(SNACK_BAR_VISIBILITY_TIME)
        consumer(Msg.Inner.HideRemovedNotesSnackBar)
    }

    private suspend fun undoRemovedFromTrash(
        notes: List<NoteUi>,
        consumer: (Msg) -> Unit
    ) {
        val notesDomain = mapper.mapListFrom(notes.map { it.copy(dateMovedToTrashRaw = null) })
        notesInteractor.updateAll(notesDomain)
        consumer(Msg.Inner.HideRemovedNotesSnackBar)
    }

    private suspend fun updatePinnedNotes(notes: Set<NoteUi>) {
        val currentDate = LocalDateTime.now()
        val isSelectedContainsUnpinned = notes.map { !it.isPinned }.contains(true)
        val selected = notes.map { note ->
            note.copy(
                isPinned = isSelectedContainsUnpinned,
                pinTime = if (isSelectedContainsUnpinned) currentDate else null,
                dateCreationRaw = note.dateCreationRaw
            )
        }
        val notesDomain = mapper.mapListFrom(selected)
        notesInteractor.updateAll(notesDomain)
    }
}
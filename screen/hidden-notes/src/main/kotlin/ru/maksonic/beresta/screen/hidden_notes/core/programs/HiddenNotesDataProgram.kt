package ru.maksonic.beresta.screen.hidden_notes.core.programs

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import ru.maksonic.beresta.core.DateFormatter
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.notes.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.api.domain.usecase.FetchHiddenNotesUseCase
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.NoteUiMapper
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi
import ru.maksonic.beresta.screen.hidden_notes.core.Cmd
import ru.maksonic.beresta.screen.hidden_notes.core.Msg
import java.time.LocalDateTime

/**
 * @Author maksonic on 21.07.2023
 */
class HiddenNotesDataProgram(
    private val notesInteractor: NotesInteractor,
    private val fetchHiddenNotesUseCase: FetchHiddenNotesUseCase,
    private val mapper: NoteUiMapper,
    private val appLanguageEngineApi: LanguageEngineApi,
    private val dateFormatter: DateFormatter,
    private val ioDispatcher: CoroutineDispatcher,
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchNotesData -> fetchNotesList(consumer)
            is Cmd.RemoveSelectedNotes -> moveSelectedNotesToTrash(cmd.notes)
            is Cmd.UpdatePinnedNotesInCache -> updatePinnedNotes(cmd.pinned)
            is Cmd.UndoRemoveNotes -> undoRemovedFromTrash(cmd.notes)
            is Cmd.UnhideSelectedNotes -> unhideNotes(cmd.notes, consumer)
            else -> {}
        }
    }

    private suspend fun fetchNotesList(consumer: (Msg) -> Unit) = withContext(ioDispatcher) {
        runCatching {
            combine(fetchHiddenNotesUseCase(), appLanguageEngineApi.current) { notesDomain, lang ->
                val notes = mapper.mapListTo(notesDomain).map { note ->
                    note.copy(
                        dateCreation = dateFormatter.fetchFormattedUiDate(
                            note.dateCreationRaw, lang
                        )
                    )
                }
                consumer(Msg.Inner.FetchedNotesData(NoteUi.Collection(notes)))
            }.collect()

        }.onFailure { throwable ->
            consumer(Msg.Inner.FetchedNotesError(throwable.localizedMessage ?: "Error"))
        }
    }

    private suspend fun moveSelectedNotesToTrash(notes: List<NoteUi>) = withContext(ioDispatcher) {
        val notesUi = notes.map { note ->
            note.copy(
                isMovedToTrash = true,
                folderId = 2L,
                dateMovedToTrashRaw = LocalDateTime.now()
            )
        }
        val notesDomain = mapper.mapListFrom(notesUi)
        notesInteractor.updateList(notesDomain)
    }

    private suspend fun undoRemovedFromTrash(notes: List<NoteUi>) {
        val restored = mapper.mapListFrom(notes.map {
            it.copy(
                isHidden = true,
                isMovedToTrash = false,
                dateMovedToTrashRaw = null
            )
        })
        notesInteractor.updateList(restored)
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
        notesInteractor.updateList(notesDomain)
    }

    private suspend fun unhideNotes(notes: Set<NoteUi>, consumer: (Msg) -> Unit) {
        val unhidden = mapper.mapListFrom(notes.toList())
            .map { it.copy(isPinned = false, pinTime = null, isHidden = false) }

        notesInteractor.updateList(unhidden)
        consumer(Msg.Inner.HiddenLoadingPlaceholder)
    }
}
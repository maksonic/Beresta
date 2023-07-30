package ru.maksonic.beresta.screen.hidden_notes.core.programs

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
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
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
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
    private val navigator: AbstractNavigator,
    private val ioDispatcher: CoroutineDispatcher,
) : ElmProgram<Msg, Cmd> {

    private companion object {
        private const val SNACK_BAR_VISIBILITY_TIME = 5000L
    }

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchNotesData -> fetchNotesList(consumer)
            is Cmd.FetchMovedForHideNotesData -> fetchMovedForHideNotes(consumer)
            is Cmd.RemoveSelectedNotes -> moveSelectedNotesToTrash(cmd.notes, consumer)
            is Cmd.UpdatePinnedNotesInCache -> updatePinnedNotes(cmd.pinned)
            is Cmd.UndoRemoveNotes -> undoRemovedFromTrash(cmd.notes, consumer)
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

    private suspend fun fetchMovedForHideNotes(consumer: (Msg) -> Unit) =
        withContext(ioDispatcher) {
            val passedNotesIds = navigator.getListLongFromString(Destination.HiddenNotes.passedKey)

            if (passedNotesIds.isNotEmpty()) {
                runCatching {
                    notesInteractor.fetchList().collect { notesDomain ->
                        val notes = notesDomain
                            .filter { note -> passedNotesIds.any { noteId -> note.id == noteId } }
                            .map { it.copy(isHidden = true, isPinned = false, pinTime = null) }

                        notesInteractor.updateList(notes)
                    }
                }.onFailure { throwable ->
                    consumer(Msg.Inner.FetchedNotesError(throwable.localizedMessage ?: "Error"))
                }
            }
        }

    private suspend fun moveSelectedNotesToTrash(notes: List<NoteUi>, consumer: (Msg) -> Unit) =
        withContext(ioDispatcher) {
            val notesUi = notes.map { note ->
                note.copy(
                    isMovedToTrash = true,
                    folderId = 2L,
                    dateMovedToTrashRaw = LocalDateTime.now()
                )
            }
            val notesDomain = mapper.mapListFrom(notesUi)
            notesInteractor.updateList(notesDomain)
            delay(SNACK_BAR_VISIBILITY_TIME)
            consumer(Msg.Inner.HiddenRemovedNotesSnackBar)
        }

    private suspend fun undoRemovedFromTrash(notes: List<NoteUi>, consumer: (Msg) -> Unit) {
        val restored = mapper.mapListFrom(notes.map {
            it.copy(
                isHidden = true,
                isMovedToTrash = false,
                dateMovedToTrashRaw = null
            )
        })
        notesInteractor.updateList(restored)
        consumer(Msg.Inner.HiddenRemovedNotesSnackBar)
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

    private suspend fun unhideNotes(notes: List<NoteUi>, consumer: (Msg) -> Unit) {
        val unhidden = mapper.mapListFrom(notes)
            .map { it.copy(isPinned = false, pinTime = null, isHidden = false) }

        notesInteractor.updateList(unhidden)
        consumer(Msg.Inner.HiddenLoadingPlaceholder)
    }
}
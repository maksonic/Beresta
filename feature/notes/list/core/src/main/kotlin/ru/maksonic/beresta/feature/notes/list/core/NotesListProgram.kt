package ru.maksonic.beresta.feature.notes.list.core

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes.folders.api.domain.FetchFoldersListUseCase
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.notes.list.api.domain.RefactorNoteInteractor
import ru.maksonic.beresta.feature.notes.list.api.domain.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUiMapper
import ru.maksonic.beresta.feature.notes.list.api.ui.sortDescendingByPinTimeThenByDate
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi
import java.time.LocalDateTime

/**
 * @Author maksonic on 24.04.2023
 */
class NotesListProgram(
    private val notesInteractor: RefactorNoteInteractor,
    private val fetchNotesUseCase: FetchNotesUseCase,
    private val mapper: NoteUiMapper,
    private val appLanguageEngineApi: LanguageEngineApi
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
            is Cmd.FetchCurrentLangForNotesDatestamp -> readLanguageFromDatastore(consumer)
        }
    }

    private suspend fun fetchNotesList(consumer: (Msg) -> Unit) {
        runCatching {
            fetchNotesUseCase().collect { notesDomain ->
                val notes = mapper.mapListTo(notesDomain).sortDescendingByPinTimeThenByDate()
                consumer(Msg.Inner.FetchedResultData(NoteUi.Collection(notes)))
            }
        }.onFailure { throwable ->
            consumer(Msg.Inner.FetchedResultError(throwable.localizedMessage.toString()))
        }
    }

    private suspend fun moveSelectedNotesToTrash(notes: List<NoteUi>, consumer: (Msg) -> Unit) {
        consumer(Msg.Inner.ShowRemovedNotesSnackBar)
        val notesDomain = mapper.mapListFrom(notes)
        notesInteractor.updateAll(notesDomain)
        delay(SNACK_BAR_VISIBILITY_TIME)
        consumer(Msg.Inner.HideRemovedNotesSnackBar)
    }

    private suspend fun undoRemovedFromTrash(notes: List<NoteUi>, consumer: (Msg) -> Unit) {
        val notesDomain = mapper.mapListFrom(notes)
        notesInteractor.updateAll(notesDomain)
        consumer(Msg.Inner.HideRemovedNotesSnackBar)
    }

    private suspend fun updatePinnedNotes(notes: Set<NoteUi>) {
        val currentDate = LocalDateTime.now()
        val isSelectedContainsUnpinned = notes.map { !it.isPinned }.contains(true)
        val selected = notes.map { note ->
            return@map note.copy(
                isPinned = isSelectedContainsUnpinned,
                pinTime = if (isSelectedContainsUnpinned) currentDate else null,
                dateCreationRaw = note.dateCreationRaw
            )
        }

        val notesDomain = mapper.mapListFrom(selected)
        notesInteractor.updateAll(notesDomain)
    }

    private suspend fun readLanguageFromDatastore(consumer: (Msg) -> Unit) {
        appLanguageEngineApi.current.collectLatest { savedAppLanguage ->
            consumer(Msg.Inner.FetchedCurrentAppLang(savedAppLanguage))
        }
    }
}
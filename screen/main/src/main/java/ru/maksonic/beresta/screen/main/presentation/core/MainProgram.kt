package ru.maksonic.beresta.screen.main.presentation.core

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.edit_note.api.domain.RefactorNoteInteractor
import ru.maksonic.beresta.feature.folders_list.api.domain.FetchFoldersListUseCase
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.folders_list.api.ui.NoteFolderToChipMapper
import ru.maksonic.beresta.feature.notes_list.api.domain.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUiMapper

/**
 * @Author maksonic on 21.02.2023
 */
class MainProgram(
    private val fetchingUseCase: FetchNotesUseCase,
    private val foldersListUseCase: FetchFoldersListUseCase,
    private val notesInteractor: RefactorNoteInteractor,
    private val notesMapper: NoteUiMapper,
    private val foldersMapper: NoteFolderToChipMapper
) : ElmProgram<Msg, Cmd> {
    private companion object {
        private const val SNACK_BAR_VISIBILITY_TIME = 5000L
    }

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.RunFetchingNotesCollection -> fetchNotes(consumer)
            is Cmd.RemoveSelected -> moveSelectedToTrash(cmd.notes, consumer)
            is Cmd.UpdatePinnedNotesInCache -> updatePinned(cmd.pinned)
            is Cmd.UndoRemoved -> undoRemovedNotesFromTrash(cmd.notes)
        }
    }

    private suspend fun fetchNotes(consumer: (Msg) -> Unit) {

        runCatching {
            combine(fetchingUseCase(), foldersListUseCase()) { notesDomain, folders ->
                val chips = foldersMapper.mapListTo(folders).sortedByDescending { it.id }
                val pinnedChip = FilterChipUi(title = "All", isSelected = true, isPinned = true)
                val pinnedChipList = mutableListOf(pinnedChip).also { it.addAll(chips) }
                val notes = notesMapper.mapListTo(notesDomain)
                val sorted = notes.sortedWith(comparator = compareByDescending<NoteUi> { note ->
                    note.isPinned
                }.thenBy { it.id })

                val sortedChips = pinnedChipList
                    .sortedWith(comparator = compareByDescending { chip -> chip.isPinned })

                consumer(
                    Msg.Inner.FetchedDataResult(
                        notes = NoteUi.Collection(sorted),
                        folders = FilterChipUi.Collection(sortedChips)
                    )
                )
            }.collect()

        }.onFailure { fail ->
            //  val message =
            //       fail.localizedMessage ?: resourceProvider.getString(R.string.error_fetching_notes)
            consumer(Msg.Inner.FetchedError("Error"))
        }
    }

    private suspend fun updatePinned(notes: List<NoteUi>) {
        val notesDomain = notesMapper.mapListFrom(notes)
        notesInteractor.updateAll(notesDomain)
    }

    private suspend fun moveSelectedToTrash(notes: List<NoteUi>, consumer: (Msg) -> Unit) {
        val remove = notes.filter { it.isMovedToTrash }
        val notesDomain = notesMapper.mapListFrom(remove)
        notesInteractor.updateAll(notesDomain)

        delay(SNACK_BAR_VISIBILITY_TIME)
        consumer(Msg.Inner.HideRemovedNotesSnack)
    }

    private suspend fun undoRemovedNotesFromTrash(notes: List<NoteUi>) {
        val notesDomain = notesMapper.mapListFrom(notes)
        notesInteractor.updateAll(notesDomain)
    }
}
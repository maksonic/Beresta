package ru.maksonic.beresta.screen.main.presentation.core

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.edit_note.api.domain.RefactorNoteInteractor
import ru.maksonic.beresta.feature.folders_list.api.domain.FetchFoldersListUseCase
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.folders_list.api.ui.NoteFolderToChipMapper
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi
import ru.maksonic.beresta.feature.notes_list.api.domain.NoteDateFormatter
import ru.maksonic.beresta.feature.notes_list.api.domain.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.api.ui.sortDescendingPinnedByTimeThenByDate
import java.time.Instant
import java.time.LocalDateTime

/**
 * @Author maksonic on 21.02.2023
 */
class MainProgram(
    private val fetchNotesUseCase: FetchNotesUseCase,
    private val fetchFoldersUseCase: FetchFoldersListUseCase,
    private val notesInteractor: RefactorNoteInteractor,
    private val notesMapper: NoteUiMapper,
    private val foldersMapper: NoteFolderToChipMapper,
    private val languageSelector: LanguageSelectorApi.Lang,
    private val dateFormatter: NoteDateFormatter,
) : ElmProgram<Msg, Cmd> {

    private companion object {
        private const val SNACK_BAR_VISIBILITY_TIME = 5000L
    }

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.RunFetchingNotesCollection -> fetchNotes(consumer)
            is Cmd.RemoveSelected -> moveSelectedNotesToTrash(cmd.notes, consumer)
            is Cmd.UpdatePinnedNotesInCache -> updatePinnedNotes(cmd.pinned)
            is Cmd.UndoRemoved -> undoRemovedNotesFromTrash(cmd.notes)
            is Cmd.SetNoteFormattedDateCreationStamp -> {}
        }
    }

    private suspend fun fetchNotes(consumer: (Msg) -> Unit) {
        runCatching {
            combine(
                fetchNotesUseCase(),
                fetchFoldersUseCase(),
                languageSelector.currentLanguage
            ) { notesDomain, folders, language ->
                val notes = notesMapper.mapListTo(notesDomain)
                    .mapIndexed { index, noteUi ->
                        noteUi.copy(
                            dateCreation = dateFormatter.fetchFormattedUiDate(
                                notesDomain[index].dateCreation, language
                            )
                        )
                    }.sortDescendingPinnedByTimeThenByDate()

                Log.e("AAA", "${notes.count()}")
                notes.forEachIndexed { index, it ->
                    Log.e("AAA", "$index {} ${it.dateCreation} - ${it.dateCreationRaw}")
                }

                val chips = foldersMapper.mapListTo(folders).toMutableList().also {
                    val pinnedChip = FilterChipUi.InitialSelected
                    it.add(pinnedChip)
                }.toList()

                val pinnedFolders = chips.filter { it.isPinned }
                    .sortedWith(compareBy<FilterChipUi> { it.id }.thenByDescending { it.pinTime })
                val otherFolders = chips.filter { !it.isPinned }.sortedByDescending { it.id }
                val resultFolders = pinnedFolders + otherFolders
                consumer(
                    Msg.Inner.FetchedDataResult(
                        notes = NoteUi.Collection(notes),
                        chips = FilterChipUi.Collection(resultFolders)
                    )
                )
            }.collect()

        }.onFailure { fail ->
            // val message =
            //      fail.localizedMessage ?: resourceProvider.getString(R.string.error_fetching_notes)
            consumer(Msg.Inner.FetchedError("Error"))
        }
    }

    private suspend fun updatePinnedNotes(notes: List<NoteUi>) {
        val currentDate = LocalDateTime.now()
        val notesDomain = notesMapper.mapListFrom(notes.map { note ->
            val pinTime = if (note.isPinned) currentDate else null
            return@map note.copy(pinTime = pinTime)
        })
        notesInteractor.updateAll(notesDomain)
    }

    private suspend fun moveSelectedNotesToTrash(notes: List<NoteUi>, consumer: (Msg) -> Unit) {
        val notesDomain = notesMapper.mapListFrom(notes)
        notesInteractor.updateAll(notesDomain)

        delay(SNACK_BAR_VISIBILITY_TIME)
        consumer(Msg.Inner.HideRemovedNotesSnack)
    }

    private suspend fun undoRemovedNotesFromTrash(notes: List<NoteUi>) {
        val notesDomain = notesMapper.mapListFrom(notes)
        notesInteractor.updateAll(notesDomain)

    }
}


/*combine(fetchNotesUseCase(), fetchFoldersUseCase()) { notesDomain, folders ->
           val notes = notesMapper.mapListTo(notesDomain)
           val pinned = notes.filter { it.isPinned }
           val other = notes.filter { !it.isPinned }.sortedByDescending { it.id }
           val result = other.toMutableList().also { it -> it.addAll(0, pinned.sortedByDescending { it.pinTime }) }

           val chips = foldersMapper.mapListTo(folders).toMutableList().also {
               val pinnedChip = FilterChipUi.InitialSelected
               it.add(pinnedChip)
           }.toList()

           val pinnedFolders = chips.filter { it.isPinned }
               .sortedWith(compareBy<FilterChipUi> { it.id }.thenByDescending { it.pinTime })
           val otherFolders = chips.filter { !it.isPinned }.sortedByDescending { it.id }
           val resultFolders = pinnedFolders + otherFolders
           consumer(
               Msg.Inner.FetchedDataResult(
                   notes = NoteUi.Collection(result),
                   chips = FilterChipUi.Collection(resultFolders)
               )
           )
       }.collect()*/
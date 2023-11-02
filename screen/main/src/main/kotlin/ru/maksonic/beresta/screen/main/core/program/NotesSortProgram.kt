package ru.maksonic.beresta.screen.main.core.program

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import ru.maksonic.beresta.feature.notes_list.domain.card.FetchNoteCardStateUseCase
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NotesCardUiApi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardStateMapper
import ru.maksonic.beresta.feature.sorting_sheet.domain.GridCountKey
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortInteractor
import ru.maksonic.beresta.feature.sorting_sheet.domain.usecase.FetchNotesSortUseCase
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.NotesSortUiMapper
import ru.maksonic.beresta.platform.elm.core.ElmProgram
import ru.maksonic.beresta.screen.main.core.Cmd
import ru.maksonic.beresta.screen.main.core.Msg

/**
 * @Author maksonic on 16.10.2023
 */
class NotesSortProgram(
    private val fetchNotesSortUseCase: FetchNotesSortUseCase,
    private val fetchNoteCardStateUseCase: FetchNoteCardStateUseCase,
    private val notesCardStateStoreUiApi: NotesCardUiApi.CardState,
    private val notesCardStateMapper: NoteCardStateMapper,
    private val sortInteractor: SortInteractor,
    private val mapper: NotesSortUiMapper,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchNotesSortState -> fetchNotesSortState(consumer)
            is Cmd.UpdateNotesGrid -> updateGridViewState(cmd.count)
            else -> {}
        }
    }

    private suspend fun fetchNotesSortState(consumer: (Msg) -> Unit) = combine(
        fetchNotesSortUseCase(), fetchNoteCardStateUseCase()
    ) { sort, cardState ->
        notesCardStateStoreUiApi.update(notesCardStateMapper.mapTo(cardState))
        consumer(Msg.Inner.FetchedNotesSort(mapper.mapTo(sort)))
    }.collect()

    private suspend fun updateGridViewState(count: Int) =
        sortInteractor.setGridCount(Pair(GridCountKey.NOTES, count))
}
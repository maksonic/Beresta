package ru.maksonic.beresta.screen.hidden_notes.core.programs

import ru.maksonic.beresta.feature.sorting_sheet.domain.GridCountKey
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortInteractor
import ru.maksonic.beresta.feature.sorting_sheet.domain.usecase.FetchHiddenNotesSortUseCase
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.NotesSortUiMapper
import ru.maksonic.beresta.platform.elm.core.ElmProgram
import ru.maksonic.beresta.screen.hidden_notes.core.Cmd
import ru.maksonic.beresta.screen.hidden_notes.core.Msg

/**
 * @Author maksonic on 21.07.2023
 */
class HiddenNotesSortProgram(
    private val fetchHiddenNotesSortUseCase: FetchHiddenNotesSortUseCase,
    private val sortInteractor: SortInteractor,
    private val sortMapper: NotesSortUiMapper,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchNotesSortState -> fetchNotesSortState(consumer)
            is Cmd.UpdateNotesGrid -> updateGridViewState(cmd.count)
            else -> {}
        }
    }

    private suspend fun fetchNotesSortState(consumer: (Msg) -> Unit) =
        fetchHiddenNotesSortUseCase().collect {
            consumer(Msg.Inner.FetchedNotesSort(sortMapper.mapTo(it)))

        }

    private suspend fun updateGridViewState(count: Int) =
        sortInteractor.setGridCount(Pair(GridCountKey.HIDDEN_NOTES, count))
}
package ru.maksonic.beresta.screen.trash_list.folders.core

import kotlinx.coroutines.delay
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes.list.api.domain.usecase.FetchRemovedNotesUseCase
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUiMapper

/**
 * @Author maksonic on 30.05.2023
 */
class FoldersTrashProgram(
    private val fetchRemovedNotes : FetchRemovedNotesUseCase,
    private val notesMapper: NoteUiMapper
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchRemovedData -> fetchData(consumer)
        }
    }

    private suspend fun fetchData(consumer: (Msg) -> Unit) {
        delay(5000)
        runCatching {
            fetchRemovedNotes().collect { notesDomain ->
                val notes = notesMapper.mapListTo(notesDomain)
                consumer(Msg.Inner.FetchedRemovedNotesResult(emptyList()))
            }
        }
    }
}
package ru.maksonic.beresta.feature.trash_list.ui.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes_list.api.NoteUiMapper
import ru.maksonic.beresta.feature.trash_list.domain.FetchTrashNotesUseCase

/**
 * @Author maksonic on 23.01.2023
 */
class TrashProgram(
    private val useCase: FetchTrashNotesUseCase,
    private val mapper: NoteUiMapper
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchRemovedNotes -> fetchRemovedNotes(consumer)
        }
    }

    private suspend fun fetchRemovedNotes(consumer: (Msg) -> Unit) {
        runCatching {
            useCase().collect { domainNotes ->
                val trashList = mapper.mapListTo(domainNotes)
                consumer(Msg.Inner.FetchingResult(trashList))
            }
        }.onFailure {
            consumer(Msg.Inner.FetchingResult(emptyList()))
        }
    }
}
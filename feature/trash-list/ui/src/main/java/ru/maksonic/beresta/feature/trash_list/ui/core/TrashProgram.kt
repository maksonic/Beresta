package ru.maksonic.beresta.feature.trash_list.ui.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes_list.api.NoteUiMapper
import ru.maksonic.beresta.feature.trash_list.domain.FetchMovedToTrashNotesUseCase

/**
 * @Author maksonic on 23.01.2023
 */
class TrashProgram(
    private val useCase: FetchMovedToTrashNotesUseCase,
    private val mapper: NoteUiMapper
) : ElmProgram<Feature.Msg, Feature.Cmd> {

    override suspend fun executeProgram(cmd: Feature.Cmd, consumer: (Feature.Msg) -> Unit) {
        when (cmd) {
            is Feature.Cmd.FetchRemovedNotes -> fetchRemovedNotes(consumer)
        }
    }

    suspend fun fetchRemovedNotes(consumer: (Feature.Msg) -> Unit) {
        runCatching {
            useCase().collect { domainNotes ->
                val removed = mapper.mapListTo(domainNotes)
                consumer(Feature.Msg.Inner.FetchingResult(removed))
            }
        }.onFailure {
            consumer(Feature.Msg.Inner.FetchingResult(emptyList()))
        }
    }
}
package ru.maksonic.beresta.feature.notes_list.data.card

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.feature.notes_list.domain.card.FetchNoteCardStateUseCase
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardDomainState
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardStateRepository

/**
 * @Author maksonic on 23.10.2023
 */
class FetchNoteCardStateUseCaseImpl(
    private val repository: NoteCardStateRepository
) : FetchNoteCardStateUseCase {
    override fun invoke(): Flow<NoteCardDomainState> = repository.fetchNoteCardState()
}
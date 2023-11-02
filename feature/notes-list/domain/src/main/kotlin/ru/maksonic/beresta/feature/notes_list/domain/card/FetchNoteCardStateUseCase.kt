package ru.maksonic.beresta.feature.notes_list.domain.card

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.common.domain.UseCase

/**
 * @Author maksonic on 13.10.2023
 */
interface FetchNoteCardStateUseCase: UseCase.Default<Flow<NoteCardDomainState>>
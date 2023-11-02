package ru.maksonic.beresta.feature.sorting_sheet.data

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortRepository
import ru.maksonic.beresta.feature.sorting_sheet.domain.sort.NotesSortDomain
import ru.maksonic.beresta.feature.sorting_sheet.domain.usecase.FetchHiddenNotesSortUseCase

/**
 * @Author maksonic on 21.10.2023
 */
class FetchHiddenNotesSortUseCaseImpl(
    private val repository: SortRepository
): FetchHiddenNotesSortUseCase {
    override fun invoke(): Flow<NotesSortDomain> = repository.fetchHiddenNotesSort()
}
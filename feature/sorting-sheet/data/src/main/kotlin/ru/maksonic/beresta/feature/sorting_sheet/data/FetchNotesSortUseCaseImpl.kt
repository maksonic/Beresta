package ru.maksonic.beresta.feature.sorting_sheet.data

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortRepository
import ru.maksonic.beresta.feature.sorting_sheet.domain.sort.NotesSortDomain
import ru.maksonic.beresta.feature.sorting_sheet.domain.usecase.FetchNotesSortUseCase

/**
 * @Author maksonic on 16.10.2023
 */
class FetchNotesSortUseCaseImpl(private val repository: SortRepository): FetchNotesSortUseCase {
    override fun invoke(): Flow<NotesSortDomain> = repository.fetchNotesSort()
}
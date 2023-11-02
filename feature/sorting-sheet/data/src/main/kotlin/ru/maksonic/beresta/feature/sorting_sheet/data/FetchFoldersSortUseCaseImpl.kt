package ru.maksonic.beresta.feature.sorting_sheet.data

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortRepository
import ru.maksonic.beresta.feature.sorting_sheet.domain.sort.FoldersSortDomain
import ru.maksonic.beresta.feature.sorting_sheet.domain.usecase.FetchFoldersSortUseCase

/**
 * @Author maksonic on 16.10.2023
 */
class FetchFoldersSortUseCaseImpl(
    private val repository: SortRepository
) : FetchFoldersSortUseCase {
    override fun invoke(): Flow<FoldersSortDomain> = repository.fetchFoldersSort()
}
package ru.maksonic.beresta.feature.folders_list.api.domain

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.core.domain.BaseUseCase

/**
 * @Author maksonic on 30.03.2023
 */
class FetchFoldersListUseCase(
    private val repository: NotesFoldersRepository
) : BaseUseCase.Default<Flow<List<NoteFolderDomain>>> {
    override suspend fun invoke(): Flow<List<NoteFolderDomain>> = repository.fetchItemsList()
}
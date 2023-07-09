package ru.maksonic.beresta.feature.folders_chips.api.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.core.domain.BaseUseCase
import ru.maksonic.beresta.feature.folders_chips.api.domain.FolderDomain
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersRepository

/**
 * @Author maksonic on 30.03.2023
 */
class FetchFoldersListUseCase(
    private val repository: FoldersRepository
) : BaseUseCase.Default<Flow<List<FolderDomain>>> {
    override suspend fun invoke(): Flow<List<FolderDomain>> = repository.fetchItemsList()
}
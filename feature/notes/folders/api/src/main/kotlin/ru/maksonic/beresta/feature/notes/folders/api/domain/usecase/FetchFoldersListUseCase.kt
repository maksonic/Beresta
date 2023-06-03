package ru.maksonic.beresta.feature.notes.folders.api.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.core.domain.BaseUseCase
import ru.maksonic.beresta.feature.notes.folders.api.domain.NoteFolderDomain
import ru.maksonic.beresta.feature.notes.folders.api.domain.NotesFoldersRepository

/**
 * @Author maksonic on 30.03.2023
 */
class FetchFoldersListUseCase(
    private val repository: NotesFoldersRepository
) : BaseUseCase.Default<Flow<List<NoteFolderDomain>>> {
    override suspend fun invoke(): Flow<List<NoteFolderDomain>> = repository.fetchItemsList()
}
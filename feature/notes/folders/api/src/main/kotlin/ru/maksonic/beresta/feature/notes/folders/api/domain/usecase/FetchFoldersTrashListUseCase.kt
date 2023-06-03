package ru.maksonic.beresta.feature.notes.folders.api.domain.usecase

import ru.maksonic.beresta.core.domain.BaseUseCase
import ru.maksonic.beresta.feature.notes.folders.api.domain.NotesFoldersDomainList
import ru.maksonic.beresta.feature.notes.folders.api.domain.NotesFoldersRepository

/**
 * @Author maksonic on 03.06.2023
 */
class FetchFoldersTrashListUseCase(
    private val repository: NotesFoldersRepository,
) : BaseUseCase.Default<NotesFoldersDomainList> {
    override suspend fun invoke(): NotesFoldersDomainList = repository.fetchTrashFolders()
}
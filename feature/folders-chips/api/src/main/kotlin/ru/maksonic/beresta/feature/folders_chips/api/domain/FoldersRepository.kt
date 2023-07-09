package ru.maksonic.beresta.feature.folders_chips.api.domain

import ru.maksonic.beresta.core.domain.BaseRepository

/**
 * @Author maksonic on 30.03.2023
 */
interface FoldersRepository : BaseRepository<FolderDomain> {
    suspend fun fetchTrashFolders(): FoldersDomainList
}
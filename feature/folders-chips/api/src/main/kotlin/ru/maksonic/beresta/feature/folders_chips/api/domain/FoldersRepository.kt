package ru.maksonic.beresta.feature.folders_chips.api.domain

import ru.maksonic.beresta.core.domain.Repository

/**
 * @Author maksonic on 30.03.2023
 */
interface FoldersRepository : Repository<FolderDomain> {
    suspend fun fetchTrashFolders(): FoldersDomainList
}